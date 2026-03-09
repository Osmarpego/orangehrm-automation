package pages;

import org.openqa.selenium.By;

public class DashboardPage extends BasePage {

    private final By pimMenu = By.xpath("//span[text()='PIM']");
    private final By directoryMenu = By.xpath("//span[text()='Directory']");

    public void goToPIM() {
        click(pimMenu);
    }

    public void goToDirectory() {
        click(directoryMenu);
    }
}