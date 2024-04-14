package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseClass {
    protected static WebDriver driver;
    protected static PropertyReader propertyReader;

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
}
