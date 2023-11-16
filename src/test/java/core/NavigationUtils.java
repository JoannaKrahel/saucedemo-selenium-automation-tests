package core;

import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class NavigationUtils {
    public static LoginPage navigateToLoginPage(WebDriver driver) {
        driver.get(ConfigReader.BASE_URL);
        return new LoginPage(driver);
    }

    public static ProductsPage navigateToProductsPage(WebDriver driver) {
        LoginPage loginPage = navigateToLoginPage(driver);
        return loginPage.loginWithUsername("standard_user", "secret_sauce");
    }

    public static CartPage navigateToCartPage(WebDriver driver) {
        ProductsPage productsPage = navigateToProductsPage(driver);
        return productsPage.goToCart();
    }
}
