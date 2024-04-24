package PageObjects;

import Utilities.ActionMap;
import Utilities.BaseClass;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends BaseClass {
    private WebDriver driver;
     // Create an instance of JavascriptExecutor
    JavascriptExecutor js = (JavascriptExecutor) driver;
    ActionMap actionMap = new ActionMap();
    
    // Locators for the login page elements
    private By usernameLocator = By.xpath("//*[@id='userName']");
    private By passwordLocator = By.xpath("//*[@id='password']");
    private By loginButtonLocator = By.xpath("//input[@type='Login']");

    // Constructor to initialize the WebDriver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Method to enter username
    public void enterUsername(String username) {
        WebElement usernameField = driver.findElement(usernameLocator);
        if (usernameField.isDisplayed()) {
            usernameField.sendKeys(username);
        }
    }

    // Method to enter password
    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(passwordLocator);
        if (passwordField.isDisplayed()) {
            passwordField.sendKeys(password);
        }
    }

    // Method to click on the login button
    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonLocator);
        if (loginButton.isDisplayed() && loginButton.isEnabled()) {
            loginButton.click();
        }
    }


    By loginPage = By.xpath("//*/span[text()='Login']");
    
    // Method to perform login
    public void loginAs(String username, String password) {
       // openBrowser();
        scrollDown();
        WebElement element = driver.findElement(By.tagName("h5"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        scrollDowntoElement("xpath", "//*[text()='Book Store Application']");
        clickonElement("xpath", "//*[text()='Book Store Application']");
        scrollDown();
        waitForElementToBeClickable(loginPage, 30);
        clickonElement("xpath","//*[@id='login']");
        scrollDown();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        enterUsername(username);
        scrollDown();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        enterPassword(password);
       // clickLoginButton();
    }
}
