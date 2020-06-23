package Pages;

import Utilties.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    public CheckoutPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id="first-name")
    public WebElement firsName;

    @FindBy(id="last-name")
    public WebElement lastName;

    @FindBy(id="postal-code")
    public WebElement zipCode;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement continueButton;

    @FindBy(xpath = "//a[@class='cart_cancel_link btn_secondary']")
    public WebElement cancelButton;
}
