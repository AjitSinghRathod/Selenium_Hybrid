package PageObjects;

import Utilities.ActionMap;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
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
    public WebElement findElementByXPath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }


    public WebElement findElement(String locatorType, String locatorValue) {
        WebElement element = null;
        switch (locatorType.toLowerCase()) {
            case "id":
                element = driver.findElement(By.id(locatorValue));
                break;
            case "name":
                element = driver.findElement(By.name(locatorValue));
                break;
            case "xpath":
                element = driver.findElement(By.xpath(locatorValue));
                break;
            case "css":
            case "cssselector":
                element = driver.findElement(By.cssSelector(locatorValue));
                break;
            case "class":
            case "classname":
                element = driver.findElement(By.className(locatorValue));
                break;
            case "linktext":
                element = driver.findElement(By.linkText(locatorValue));
                break;
            case "partiallinktext":
                element = driver.findElement(By.partialLinkText(locatorValue));
                break;
            case "tagname":
                element = driver.findElement(By.tagName(locatorValue));
                break;
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
        return element;
    }
    public void scrollDowntoElement(String elementType,String elementData) { 
        LoginPage finder = new LoginPage(driver);
    try{      
        WebElement element = finder.findElement(elementType, elementData);
        js.executeScript("arguments[0].scrollIntoView(true);", element);}
    catch(Exception e){
        System.err.println("Exception occurred while scrolling down: " + e.getMessage());
        e.printStackTrace();
    }   
    

       // js.executeScript("window.scrollBy(0,1000)");
    }
    public void scrollDown() {
        try {
            js.executeScript("window.scrollBy(0,1000)");
        } catch (Exception e) {
            System.err.println("Exception occurred while scrolling down: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void clickonElement(String elementType,String elementData) {
    LoginPage finder = new LoginPage(driver);
       // WebDriverWait wait = new WebDriverWait(driver, 10);
       // wait.until(ExpectedConditions.elementToBeClickable()));
        WebElement element = finder.findElement(elementType, elementData);
        if (element.isDisplayed()) {
            element.click();
        }
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

    public void clickButton(){
        actionMap.performAction("click on button");

    }
    public void openBrowser(){
        actionMap.performAction("open browser");

    }
    By loginPage = By.xpath("//*/span[text()='Login']");
    
    // Method to perform login
    public void loginAs(String username, String password) {
        openBrowser();
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
