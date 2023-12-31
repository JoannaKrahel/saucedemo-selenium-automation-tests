package specs;

import core.NavigationUtils;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsTest extends BaseTest {

    @Test
    @Description("Verify product sorting in descending order by price")
    public void testProductSortingDescByPrice() {
        ProductsPage productsPage = NavigationUtils.navigateToProductsPage(driver);
        productsPage.selectOptionByText("Price (high to low)");
        List<Double> sortedProductPrices = productsPage.getProductsPrices();
        List<Double> expectedSortedPrices = new ArrayList<>(sortedProductPrices);
        expectedSortedPrices.sort(Collections.reverseOrder());
        Assert.assertEquals(sortedProductPrices, expectedSortedPrices);
    }
}
