package practice.steps;

import practice.driver.BasePage;
import practice.pages.ItemsPage;
import practice.pages.LandingPage;
import practice.pages.OrderSummary;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class PittalaTestSteps {

    LandingPage landingPage = new LandingPage();
    ItemsPage itemsPage = new ItemsPage();
    OrderSummary orderSummary = new OrderSummary();
    String highestCost = "";

    @After
    public void quitDriver() {
        landingPage.quitDriver();
    }

    @Given("^the automation practice site launch$")
    public void openHomePage() {
        landingPage.openSampleHost(BasePage.getProperty("sampleTestUrl"));
    }

    @Then("^verify the page title to be ([^\"]*)$")
    public void assertPageTitle(String title) {
        Assert.assertEquals("Page name not matched", title, landingPage.getPageTitle());
    }

    @When("^I click on the ([^\"]*) tab$")
    public void clickOnGivenMenu(String menu) {
        landingPage.clickOnDressTab(menu);
    }

    @Then("^i add costliest item to cart$")
    public void addCostlyItemToBasket() {
        highestCost = itemsPage.getHighestCost();
        itemsPage.addCostlyItemToCart();
    }

    @Then("^I proceed to checkout$")
    public void clickOnProceedToCheckout() {
        itemsPage.clickOnProceedToCheckout();
    }

    @Then("^check the total price in the order summary page$")
    public void validateItemPriceInOrderSummaryPage() {
        System.out.println("Costly Item Price: " + highestCost);
        Assert.assertEquals("Wrong item added to cart", highestCost, orderSummary.getTheItemPrice());
    }

}
