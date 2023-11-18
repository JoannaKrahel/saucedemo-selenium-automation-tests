package util;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final String FILE_SCREENSHOTS_DIR = "src\\test\\screenshots\\";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS");

    public static void captureAndSaveScreenshots(TakesScreenshot driver, String testName) {
        byte[] screenshotBytes = captureScreenshotBytes(driver);
        saveScreenshotToFile(screenshotBytes, testName);
        saveScreenshotToAllure(screenshotBytes, testName);
    }

    private static byte[] captureScreenshotBytes(TakesScreenshot driver) {
        return driver.getScreenshotAs(OutputType.BYTES);
    }

    private static void saveScreenshotToFile(byte[] screenshotBytes, String testName) {
        try {
            String timestamp = LocalDateTime.now().format(FORMATTER);
            String filName = FILE_SCREENSHOTS_DIR + testName + "_" + timestamp + ".png";
            Files.write(Paths.get(filName), screenshotBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveScreenshotToAllure(byte[] screenshotBytes, String testName) {
        Allure.addAttachment(testName + " - Screenshot", new ByteArrayInputStream(screenshotBytes));
    }
}
