package specs;

import core.ConfigReader;
import core.NavigationUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LogOutTest extends BaseTest {

    @Test
    public void testLogout() {
        LoginPage loginPage = NavigationUtils.navigateToLoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.clickLogoutButton();

        Assert.assertEquals(driver.getCurrentUrl(), ConfigReader.BASE_URL, "Incorrect url after logout!");
    }
}
