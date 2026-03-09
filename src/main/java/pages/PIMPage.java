package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.concurrent.ThreadLocalRandom;

public class PIMPage extends BasePage {

    private final By addEmployeeButton = By.xpath("//button[normalize-space()='Add']");
    private final By firstNameInput = By.name("firstName");
    private final By lastNameInput = By.name("lastName");
    private final By employeeIdInput = By.xpath("//label[text()='Employee Id']/../following-sibling::div//input");
    private final By photoInput = By.cssSelector("input.oxd-file-input");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By personalDetailsTitle = By.xpath("//h6[@class='oxd-text oxd-text--h6 --strong']");
    private final By employeeFullNameHeader = By.xpath("//h6[contains(@class,'--strong') and normalize-space()!='Personal Details']");

    public void clickAddEmployee() {
        click(addEmployeeButton);
    }

    public void fillEmployeeName(String firstName, String lastName) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
    }

    public void setEmployeeId(String employeeId) throws InterruptedException {
        WebElement element = waitForVisibility(employeeIdInput);
        Thread.sleep(2000);
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(employeeId);
    }

    public void uploadPhoto(String photoPath) {
        waitForPresence(photoInput).sendKeys(photoPath);
    }

    public void saveEmployee() {
        click(saveButton);
    }

    public void waitForEmployeeSaved() {
        waitForTextNotEmpty(personalDetailsTitle);
        waitForVisibility(employeeFullNameHeader);
    }

    public String getSavedEmployeeFullName() {
        return getText(employeeFullNameHeader).trim();
    }

    public String generateEmployeeId() {
        int random = ThreadLocalRandom.current().nextInt(100000, 999999);
        return "EMP" + random; // 9 caracteres
    }

}