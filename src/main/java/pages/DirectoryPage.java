package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DirectoryPage extends BasePage {

    private final By employeeNameInput = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By resultCardName = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-directory-card-header --break-words']");
    private final By autocompleteDropdown = By.cssSelector("div[role='listbox']");
    private final By autocompleteOption = By.cssSelector("div[role='option']");

    public void searchEmployee(String firstname) throws InterruptedException {
        WebElement input = waitForVisibility(employeeNameInput);
        input.sendKeys(firstname);
        waitForVisibility(autocompleteDropdown);
        waitForAutocompleteOptionWithText(firstname);
        //wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(autocompleteOption, 0));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(autocompleteOption));
        //input.sendKeys(" ");
        //Thread.sleep(2000);
        input.sendKeys(Keys.ARROW_DOWN);
        input.sendKeys(Keys.ENTER);
        click(searchButton);
    }


    public String getResultEmployeeName() {
        return getText(resultCardName);
    }
}