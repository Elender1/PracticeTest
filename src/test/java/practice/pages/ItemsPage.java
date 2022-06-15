package practice.pages;

import practice.driver.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemsPage extends BasePage {

    @FindBy(css = "div.right-block")
    private List<WebElement> items;

    @FindBy(css = "div.left-block")
    private List<WebElement> images;

    @FindBy(xpath = "//a[@title='Add to cart']")
    private List<WebElement> addToCarts;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    WebElement proceedToCheckOut;

    public void addCostlyItemToCart() {
        int counter = 0;
        String maxPrice = getHighestCost();
        for (WebElement item : items) {
            String temp = item.getText().split("\\R")[1].replace("$", "");
            if (temp.contains(maxPrice)) {
                Actions action = new Actions(driver);
                action.moveToElement(images.get(counter));

                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", addToCarts.get(counter));
                break;
            }
            counter++;
        }
    }

    public String getHighestCost() {
        List<Double> itemCosts = new ArrayList<>();
        for (WebElement item : items) {
            String itemCost = item.getText().split("\\R")[1].replace("$", "");
            if (itemCost.contains("%")) {
                itemCost = itemCost.split(" ")[0];
            }
            itemCosts.add(Double.parseDouble(itemCost));
        }

        Collections.sort(itemCosts);
        Collections.reverse(itemCosts);
        return String.valueOf(itemCosts.get(0));
    }

    public void clickOnProceedToCheckout() {
        waitForWebElement(proceedToCheckOut);

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", proceedToCheckOut);
    }
}
