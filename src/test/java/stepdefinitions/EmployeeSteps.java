package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.DashboardPage;
import pages.DirectoryPage;
import pages.LoginPage;
import pages.PIMPage;

import java.nio.file.Paths;

public class EmployeeSteps {

    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    PIMPage pimPage = new PIMPage();
    DirectoryPage directoryPage = new DirectoryPage();

    String firstName;
    String lastName;
    String fullName;
    String employeeId;

    @Given("the user opens OrangeHRM login page")
    public void theUserOpensOrangeHRMLoginPage() {
        loginPage.openLoginPage();
    }

    @When("the user logs in with admin credentials")
    public void theUserLogsInWithAdminCredentials() {
        loginPage.login("Admin", "admin123");
    }

    @And("the user navigates to the PIM module")
    public void theUserNavigatesToThePIMModule() {
        dashboardPage.goToPIM();
    }

    @And("the user adds a new employee with first name {string} and last name {string}")
    public void theUserAddsANewEmployeeWithFirstNameAndLastName(String first, String last) throws InterruptedException {
        firstName = first + System.currentTimeMillis();;
        lastName = last;
        fullName = firstName + " " + lastName;
        employeeId = pimPage.generateEmployeeId();

        pimPage.clickAddEmployee();
        pimPage.fillEmployeeName(firstName, lastName);
        pimPage.setEmployeeId(employeeId);
    }

    @And("the user uploads a profile photo")
    public void theUserUploadsAProfilePhoto() {
        String photoPath = Paths.get("src/test/resources/testdata/employee-photo.png").toAbsolutePath().toString();
        pimPage.uploadPhoto(photoPath);
    }

    @And("the user saves the employee information")
    public void theUserSavesTheEmployeeInformation() {
        pimPage.saveEmployee();
    }

    @And("the saved employee information should be displayed correctly")
    public void theSavedEmployeeInformationShouldBeDisplayedCorrectly() {
        pimPage.waitForEmployeeSaved();

        String actualFullName = pimPage.getSavedEmployeeFullName();
        String expectedFullName = firstName + " " + lastName;

        Assert.assertEquals("El nombre completo guardado no coincide", expectedFullName, actualFullName);
    }

    @And("the user navigates to the Directory module")
    public void theUserNavigatesToTheDirectoryModule() {
        dashboardPage.goToDirectory();
    }

    @And("the user searches the employee by full name")
    public void theUserSearchesTheEmployeeByFullName() throws InterruptedException {
        directoryPage.searchEmployee(firstName);
    }

    @Then("the employee information should be displayed correctly in Directory")
    public void theEmployeeInformationShouldBeDisplayedCorrectlyInDirectory() {
        String actualName = directoryPage.getResultEmployeeName();
        Assert.assertTrue("El empleado no fue encontrado en Directory", actualName.contains(firstName));
    }
}