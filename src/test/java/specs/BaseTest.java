package specs;

import core.WebDriverFactory;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.ScreenshotUtil;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        switch (browser.toLowerCase()) {
            case "chrome" -> driver = WebDriverFactory.getChromeDriver();
            case "firefox" -> driver = WebDriverFactory.getFirefoxDriver();
            case "edge" -> driver = WebDriverFactory.getEdgeDriver();
            default -> throw new IllegalArgumentException("Invalid browser specified: " + browser);
        }
    }

    @AfterMethod
    public void afterTest(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.captureAndSaveScreenshots((TakesScreenshot) driver, result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
