package practice.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import practice.driver.BasePage;

public class LandingPage extends BasePage {

    @FindBy(css = "#block_top_menu > ul > li:nth-child(1) > a")
    private WebElement menu_Women;

    @FindBy(css = "#block_top_menu > ul > li:nth-child(2) > a")
    private WebElement menu_Dresses;

    @FindBy(css = "#block_top_menu > ul > li:nth-child(3) > a")
    private WebElement menu_TShirts;


    public void clickOnDressTab(String menu) {
        if (menu.equalsIgnoreCase("Dresses"))
        {
            menu_Dresses.click();
        }
        else if (menu.equalsIgnoreCase("Women"))
        {
            menu_Women.click();
        }
        else if (menu.equalsIgnoreCase("TShirts"))
        {
            menu_TShirts.click();
        }
        else {
            Assert.fail("Please passed menu options");
        }
    }

}
