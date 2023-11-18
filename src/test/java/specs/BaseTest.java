package specs;

import core.WebDriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public void tearDown(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshotOnTestFailure(result);
        }

        if (driver != null) {
            driver.quit();
        }
    }

    public void takeScreenshotOnTestFailure(ITestResult result) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File source = takesScreenshot.getScreenshotAs(OutputType.FILE);

            String screenshotsDir = "src\\test\\screenshots\\";

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyMMdd_HHmm");
            String timestamp = now.format(formatter);

            String screenshotName = result.getName() + "_" + timestamp + ".png";
            File destination = new File(screenshotsDir + screenshotName);

            Files.copy(source.toPath(), destination.toPath());
            System.out.println("Screenshot taken: " + screenshotName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
