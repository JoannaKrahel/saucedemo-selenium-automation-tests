package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = "button.cart_button")
    private List<WebElement> removeItemButtons;
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getItemsNumberInCart() {
        return cartItems.size();
    }

    public void removeItemFromCart() {
        removeItemButtons.get(0).click();
    }
}
