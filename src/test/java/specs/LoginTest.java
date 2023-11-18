package specs;

import core.ConfigReader;
import core.NavigationUtils;
import data.LoginData;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    @Description("Verify successful login to saucedemo website")
    public void loginToSaucedemo() {
        LoginPage loginPage = NavigationUtils.navigateToLoginPage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        WebElement productsLabel = driver.findElement(By.className("title"));
        Assert.assertEquals(productsLabel.getText(), "Products");
    }


    @Test(dataProvider = "invalidLoginData", dataProviderClass = LoginData.class)
    @Description("Attempt login with invalid credentials and validate error message and URL")
    public void testInvalidLogin(String username, String password, String error) {
        LoginPage loginPage = NavigationUtils.navigateToLoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, error);
        Assert.assertEquals(driver.getCurrentUrl(), ConfigReader.BASE_URL, "Incorrect URL after login!");

    }
}
