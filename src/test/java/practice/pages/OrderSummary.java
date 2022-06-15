package practice.pages;

import practice.driver.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderSummary extends BasePage {

    @FindBy(css = "#cart_summary")
    private WebElement orderTable;

    public String getTheItemPrice() {
        waitForWebElement(orderTable);
        return orderTable.findElement(By.xpath("//tfoot/tr/td[3]")).getText().replace("$", "");
    }
}
