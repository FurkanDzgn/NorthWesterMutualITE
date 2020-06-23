package Pages;

import Utilties.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingCartPage {
    public ShoppingCartPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    public List<WebElement> addedProducts;

    @FindBy(xpath = "//a[contains(. ,'Continue Shopping')]")
    public WebElement continueShoppingButton;

    @FindBy(xpath = "//a[contains(. ,'CHECKOUT')]")
    public WebElement checkoutButton;

    @FindBy(xpath = "//button[@class='btn_secondary cart_button']")
    public List<WebElement> removeButtons;
}
