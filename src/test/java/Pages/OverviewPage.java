package Pages;

import Utilties.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OverviewPage {

    public OverviewPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//div[@class='inventory_item_name']")
    public List<WebElement> addedProducts;

    @FindBy(xpath = "(//div[@class='inventory_item_price'])")
    public List<WebElement> addedProductsPrices;

    @FindBy(xpath = "(//div[@class='summary_subtotal_label'])")
    public WebElement itemTotalLabel;

    @FindBy(xpath = "//a[text()='FINISH']")
    public WebElement finishButton;
}
