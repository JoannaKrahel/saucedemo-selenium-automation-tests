package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import util.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    @FindBy(className = "bm-burger-button")
    private WebElement sideBarButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(className = "inventory_item")
    private List<WebElement> itemList;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNameList;

    @FindBy(css = "button.btn_inventory")
    private List<WebElement> addItemToCartButtonList;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCart;

    @FindBy(className = "product_sort_container")
    private WebElement productSortContainer;
    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemsPrices;

    private final WaitUtils wait;

    private final WebDriver driver;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void openSidebar() {

        wait.waitForElementToBeClickable(sideBarButton);
        sideBarButton.click();
    }

    public void clickLogoutButton() {
        openSidebar();
        wait.waitForElementToBeVisible(logoutButton);
        logoutButton.click();
    }

    public void addItemToCart() {
        addItemToCartButtonList.get(0).click();
    }

    public void addItemsToCart(Integer quantity) {
        for (int i = 0; i <= quantity - 1; i++) {
            addItemToCartButtonList.get(i).click();
        }
    }

    public CartPage goToCart() {
        shoppingCart.click();
        return new CartPage(driver);
    }

    public void selectOptionByText(String option) {
        productSortContainer.click();
        Select select = new Select(productSortContainer);
        select.selectByVisibleText(option);
    }

    public List<Double> getProductsPrices() {

        List<Double> itemsPrices = new ArrayList<>();

        for (WebElement itemPrice : this.itemsPrices) {
            String priceText = itemPrice.getText().replace("$", "");
            itemsPrices.add(Double.valueOf(priceText));
        }
        return itemsPrices;
    }

}
