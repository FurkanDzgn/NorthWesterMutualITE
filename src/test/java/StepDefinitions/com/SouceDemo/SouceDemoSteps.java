package StepDefinitions.com.SouceDemo;

import Pages.*;
import Utilties.BrowserUtils;
import Utilties.ConfigReader;
import Utilties.Driver;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakerIDN;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Do;
import io.cucumber.java.sl.In;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SouceDemoSteps {
    WebDriver driver= Driver.getDriver();
    LoginPage loginPage=new LoginPage();
    ShoppingCartPage shoppingCartPage=new ShoppingCartPage();
    CheckoutPage checkoutPage=new CheckoutPage();
    InventoryPage inventoryPage=new InventoryPage();
    OverviewPage overviewPage=new OverviewPage();
    FinishPage finishPage=new FinishPage();
    List<String> actualAddedProducts=new ArrayList<>();



    @Given("the user navigate to page url")
    public void the_user_navigate_to_page() {

        driver.get(ConfigReader.getProperty("urlSoucedemo"));
        loginPage.userName.sendKeys(ConfigReader.getProperty("userName"));
        loginPage.password.sendKeys(ConfigReader.getProperty("passWord"));
        loginPage.loginButton.click();

    }

    @When("the user validate title {string} and url {string}")
    public void the_user_validate_title_and_url(String expectedTitle, String expectedUrl) {
        Assert.assertTrue(driver.getTitle().contains(expectedTitle));
        Assert.assertEquals(expectedUrl,driver.getCurrentUrl());
    }

    @When("the user sort items name A to Z {string}")
    public void the_user_sort_items_name_A_to_Z(String select) {
        BrowserUtils.selectByVisibleText(inventoryPage.dropdown,select);
        List<String> expectedProducts=new ArrayList<>();
        List<String> actualProducts=new ArrayList<>();

        for (WebElement product:inventoryPage.products){
            expectedProducts.add(product.getText());
            actualProducts.add(product.getText());
        }
        Collections.sort(expectedProducts);
        Assert.assertEquals(expectedProducts,actualProducts);

    }

    @Then("the user add {int} items to the shopping cart")
    public void the_user_add_items_to_the_shopping_cart(Integer int1) throws InterruptedException {
       int length=int1;
        for(int i=0;i<length;i++){
            Thread.sleep(500);
            inventoryPage.addToCArtButtons.get(0).click();
            actualAddedProducts.add(inventoryPage.products.get(i).getText());
    }



    }
    @Then("the user click shopping cart")
    public void the_user_click_shopping_cart() {
        Actions actions=new Actions(driver);
        actions.moveToElement( inventoryPage.shoppingCartButton).click().perform();
    }



    @Then("Validate the items that you added")
    public void validate_the_items_that_you_added() {

        List<String> expectedAddedProducts=new ArrayList<>();

        for (WebElement product:shoppingCartPage.addedProducts){
            expectedAddedProducts.add(product.getText());
        }
        Assert.assertEquals(expectedAddedProducts,actualAddedProducts);

    }

    @Then("the user remove one item and continue shopping")
    public void the_user_remove_one_item_and_continue_shopping() {
        shoppingCartPage.removeButtons.get(0).click();
        actualAddedProducts.remove(0);
        System.out.println(actualAddedProducts);
        shoppingCartPage.continueShoppingButton.click();

    }

    @Then("add another item")
    public void add_another_item() {
       inventoryPage.addToCArtButtons.get(1).click();
       actualAddedProducts.add(inventoryPage.products.get(3).getText());
    }

    @Then("the user click checkout and continue with correct information")
    public void the_user_click_checkout_and_continue_with_correct_information() {
        Faker faker=new Faker();

       shoppingCartPage.checkoutButton.click();
       checkoutPage.firsName.sendKeys(faker.name().firstName());
       checkoutPage.lastName.sendKeys(faker.name().lastName());
       checkoutPage.zipCode.sendKeys(faker.address().zipCode());
       checkoutPage.continueButton.click();

    }

    @Then("the user Assert you are purchasing the correct items")
    public void the_user_Assert_you_are_purchasing_the_correct_items() {

        List<String> expectedAddedProducts=new ArrayList<>();

        for (WebElement product:overviewPage.addedProducts){
            expectedAddedProducts.add(product.getText());
        }
        Assert.assertEquals(expectedAddedProducts,actualAddedProducts);

    }

    @Then("the user Assert the total price")
    public void the_user_Assert_the_total_price() {
        List<Double> expectedAddedProductsPrices=new ArrayList<>();
        Double sumOfPrices=0.0;

        for(WebElement price:overviewPage.addedProductsPrices){
            Double temp=Double.parseDouble(price.getText().trim().substring(1));
            expectedAddedProductsPrices.add(temp);
            sumOfPrices+=temp;
        }

        String total=overviewPage.itemTotalLabel.getText();
        Double itemTotal=0.0;
        for(int i=0;i<total.length();i++){
            if(Character.isDigit(total.charAt(i))){
                itemTotal=Double.parseDouble(total.substring(i));
                break;
            }
        }
        Assert.assertEquals(sumOfPrices,itemTotal);
        overviewPage.finishButton.click();


    }

    @Then("the user validate final message {string}")
    public void the_user_validate_final_message(String expectedMessage) {
        String actualMessage=finishPage.validateMessage.getText();
        Assert.assertEquals(expectedMessage,actualMessage);
    }

}
