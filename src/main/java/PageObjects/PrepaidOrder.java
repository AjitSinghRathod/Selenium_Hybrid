package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.BaseClass;


public class PrepaidOrder extends BaseClass {

    private WebDriver driver;
    public PrepaidOrder(WebDriver driver) {
        this.driver = driver;
    }

    public  void prepaidOrder() {
    WebDriverWait wait = new WebDriverWait(driver, 30);  

    waitAndClick(wait, By.xpath("//*[@id='appnavigator']"));
    clickOnElement("xpath","//*[contains(text(), 'SALES')]");
    
    driver.get("https://crmsit.one.al/index.php?module=Invoice&view=List&app=SALES");
    
    String customerTitle = driver.getTitle();
    Assert.assertEquals(customerTitle, "Order", "The user is not redirected to the Order Page after login.");
    clickById(driver, "Invoice_listView_basicAction_LBL_ADD_RECORD");
    clickByXpath(driver,"//*[@class='select2-container inputElement select2']/a/span[text()='Provide Order']");

    }
}