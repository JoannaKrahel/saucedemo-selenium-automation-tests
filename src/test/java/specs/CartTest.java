package specs;

import core.NavigationUtils;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPage;

public class CartTest extends BaseTest {

    @DataProvider(name = "cartOperations")
    public Object[][] cartOperations() {
        return new Object[][]{
                {1},
                {3},
                {0}
        };
    }

    @Test(dataProvider = "cartOperations")
    @Description("Add a specific quantity of items to the cart and verify the cart's content")
    public void testAddItemsFromCart(int quantity) {
        ProductsPage productsPage = NavigationUtils.navigateToProductsPage(driver);
        productsPage.addItemsToCart(quantity);
        CartPage cartPage = NavigationUtils.navigateToCartPage(driver);
        Assert.assertEquals(cartPage.getItemsNumberInCart(), quantity, "Invalid number of items in the cart after adding!");
    }

    @Test
    @Description("Remove an item from the cart and verify the cart is empty")
    public void removeItemFromCart() {
        ProductsPage productsPage = NavigationUtils.navigateToProductsPage(driver);
        productsPage.addItemToCart();
        CartPage cartPage = NavigationUtils.navigateToCartPage(driver);
        cartPage.removeItemFromCart();
        Assert.assertEquals(cartPage.getItemsNumberInCart(), 0, "Invalid number of items in a cart!");
    }

}
