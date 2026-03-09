package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String executionMode = System.getProperty("execution.mode", "local");

            if ("browserstack".equalsIgnoreCase(executionMode)) {
                driver = createBrowserStackDriver();
            } else {
                driver = createLocalDriver();
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    private static WebDriver createLocalDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }

    private static WebDriver createBrowserStackDriver() {
        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        if (username == null || accessKey == null) {
            throw new RuntimeException(
                    "Faltan BROWSERSTACK_USERNAME o BROWSERSTACK_ACCESS_KEY en variables de entorno."
            );
        }

        String browserName = System.getProperty("browser", "Chrome");
        String browserVersion = System.getProperty("browserVersion", "latest");
        String os = System.getProperty("os", "Windows");
        String osVersion = System.getProperty("osVersion", "11");

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);

        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", os);
        bstackOptions.put("osVersion", osVersion);
        bstackOptions.put("projectName", "OrangeHRM Automation");
        bstackOptions.put("buildName", "Cucumber Selenium Build");
        bstackOptions.put("sessionName", "Add employee flow - " + browserName);
        bstackOptions.put("resolution", "1920x1080");
        bstackOptions.put("debug", true);
        bstackOptions.put("networkLogs", true);

        capabilities.setCapability("bstack:options", bstackOptions);

        String browserstackUrl = "https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub";

        try {
            RemoteWebDriver remoteDriver = new RemoteWebDriver(new URL(browserstackUrl), capabilities);
            remoteDriver.setFileDetector(new LocalFileDetector());
            remoteDriver.manage().window().setSize(new Dimension(1920, 1080));
            return remoteDriver;
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL de BrowserStack inválida", e);
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}