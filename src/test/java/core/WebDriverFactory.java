package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static core.ConfigReader.HEADLESS;

public class WebDriverFactory {

    public static WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        if (HEADLESS) {
            options.addArguments("--headless");
        }

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }

    public static WebDriver getFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        if (HEADLESS) {
            options.addArguments("--headless");
        }
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(options);
    }

    public static WebDriver getEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        if (HEADLESS) {
            options.addArguments("--headless");
        }
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(options);
    }

}
