package Utilities;

import PageObjects.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;





public class BaseClass {
    protected static WebDriver driver;
    protected static PropertyReader propertyReader;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    ActionMap actionMap = new ActionMap();
    @BeforeClass
    public void setup() {
        // Initialize the PropertyReader
        propertyReader = new PropertyReader();
        // Load the configuration properties
        propertyReader.loadProperties();

        // Get browser type from configuration (e.g., "chrome" or "firefox")
        String browserType = PropertyReader.getProperty("browserType");

        // Initialize the WebDriver based on browser type
        if (browserType.equalsIgnoreCase("chrome")) {
            // Set ChromeDriver path
            System.setProperty("webdriver.chrome.driver", PropertyReader.getProperty("chromeDriverPath"));
            // Initialize ChromeDriver
            driver = new ChromeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            // Set GeckoDriver path
            System.setProperty("webdriver.gecko.driver", PropertyReader.getProperty("geckoDriverPath"));
            // Initialize FirefoxDriver
            driver = new FirefoxDriver();
        } else {
            // Handle unsupported browser types
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        // Maximize the browser window
        driver.manage().window().maximize();
        // Set implicit wait time
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to the base URL from the configuration
        driver.get(PropertyReader.getProperty("baseUrl"));
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after test completion
        if (driver != null) {
            driver.quit();
        }
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

    public void clickonElement(String elementType,String elementData) {
        LoginPage finder = new LoginPage(driver);
           // WebDriverWait wait = new WebDriverWait(driver, 10);
           // wait.until(ExpectedConditions.elementToBeClickable()));
            WebElement element = finder.findElement(elementType, elementData);
            if (element.isDisplayed()) {
                element.click();
            }
        }
        public WebElement findElementByXPath(String xpath) {
        return driver.findElement(By.xpath(xpath));
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
    public void clickButton(){
        actionMap.performAction("click on button");

    }
    public void openBrowser(){
        actionMap.performAction("open browser");

    }
    public void selectFromDropdown(WebElement dropdownElement, String value) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(value);
    }
    public void selectFromCustomDropdown(WebElement dropdownElement, String value) {
        dropdownElement.click();
        WebElement option = dropdownElement.findElement(By.xpath("//div[contains(text(), '" + value + "')]"));
        option.click();
    }
    
}
