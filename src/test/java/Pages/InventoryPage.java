package Pages;

import Utilties.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage {
    public InventoryPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    public List<WebElement> products;

    @FindBy(className = "product_sort_container")
    public WebElement dropdown;

    @FindBy(xpath = "//button[@class='btn_primary btn_inventory']")
    public List<WebElement> addToCArtButtons;

    @FindBy(xpath = "//div[@class='header_container']//div//a")
    public WebElement shoppingCartButton;




}
