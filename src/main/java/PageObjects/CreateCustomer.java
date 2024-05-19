package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.BaseClass;
import Utilities.RandomValue;


public class CreateCustomer extends BaseClass {
    private WebDriver driver;
    public CreateCustomer(WebDriver driver) {
        this.driver = driver;
    }
    RandomValue randomValue = new RandomValue();
   // Actions actions = new Actions(driver);
    public  void createcustomer() {
        
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // Navigate to the Customer section
        
        WebElement customerMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='appnavigator']")));
        customerMenu.click();

        // Click on the option to create a new customer
        WebElement salesNavigatioElement = driver.findElement(By.xpath("//*[contains(text(), 'SALES')]"));
        salesNavigatioElement.click();

       // actions.moveToElement(salesNavigatioElement).perform();
       // WebElement customerNavigatioElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("menubar_quickCreate_Accounts")));
        //WebElement customerNavigatioElement = driver.findElement(By.xpath("//*[contains(text(), 'Customers')]"));
        //actions.moveToElement(customerNavigatioElement).perform();
        //customerNavigatioElement.click();
        // Fill in the required information for the new customer
        String customerTitle = driver.getTitle();
        Assert.assertEquals(customerTitle, "Customers", "The user is not redirected to the Customer page after login.");
        WebElement addCustomerElement = driver.findElement(By.id("Accounts_listView_basicAction_LBL_ADD_RECORD"));
        addCustomerElement.click();
        String customername = randomValue.generateRandomName(10);
        WebElement customerSegment = driver.findElement(By.id("select2-chosen-2"));
        //selectFromDropdown(customerSegment,"Residential");
        customerSegment.click();
        WebElement customerSegmentInput = driver.findElement(By.id("s2id_autogen2_search"));
        customerSegmentInput.sendKeys("Residential");
        driver.findElement(By.xpath("//*[@class='select2-match']")).click();
        
        WebElement customerFNameElement = driver.findElement(By.id("Accounts_editView_fieldName_accountname"));
        customerFNameElement.sendKeys(customername);
        
        WebElement customerLNameElement = driver.findElement(By.id("Accounts_editView_fieldName_lastname"));
        customerLNameElement.sendKeys(customername);

        WebElement customerCategory = driver.findElement(By.id("select2-chosen-6"));
        customerCategory.click();  

        WebElement customerCategoryInput = driver.findElement(By.id("s2id_autogen6_search"));
        customerCategoryInput.sendKeys("Foreigner");
        driver.findElement(By.className("select2-match")).click();

        WebElement customerGender = driver.findElement(By.id("select2-chosen-8"));
        customerGender.click();
        WebElement customerGenderInput = driver.findElement(By.id("s2id_autogen8_search"));
        customerGenderInput.sendKeys("Male");
        driver.findElement(By.className("select2-match")).click();        
        
        WebElement customerDOB = driver.findElement(By.id("Accounts_editView_fieldName_dob"));
        customerDOB.click();
        customerDOB.sendKeys("01-01-1989");

        WebElement commModeElement = driver.findElement(By.id("select2-chosen-14"));
        commModeElement.click();
        WebElement commModeInput = driver.findElement(By.id("s2id_autogen14_search"));
        commModeInput.sendKeys("Both");
        driver.findElement(By.className("select2-match")).click();

        WebElement invLanguageElement = driver.findElement(By.id("select2-chosen-16"));
        invLanguageElement.click();
        WebElement invLanguageInput = driver.findElement(By.id("s2id_autogen16_search"));
        invLanguageInput.sendKeys("English");
        driver.findElement(By.className("select2-match")).click();
        
        WebElement commChannelElement = driver.findElement(By.name("consent_channel"));
        commChannelElement.click();
        //WebElement commChannelInput = driver.findElement(By.id("s2id_autogen16_search"));
        commChannelElement.sendKeys("SMS");
        driver.findElement(By.className("select2-match")).click();
        
        
        WebElement parentAccount = driver.findElement(By.id("parent_account"));
        parentAccount.sendKeys("Parent Account Name");

        // Additional fields
        WebElement industry = driver.findElement(By.id("industry"));
        industry.sendKeys("Industry Type");

        WebElement contactFixedPhone = driver.findElement(By.id("contact_fixed_phone"));
        contactFixedPhone.sendKeys("1234567890");

        WebElement profession = driver.findElement(By.id("profession"));
        profession.sendKeys("Profession");

        WebElement incomeSegment = driver.findElement(By.id("income_segment"));
        incomeSegment.sendKeys("Income Segment");

        WebElement contactPhone = driver.findElement(By.id("contact_phone"));
        contactPhone.sendKeys("9876543210");

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("john.doe@example.com");

        // Save the new customer
        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(), 'Save')]"));
        saveButton.click();

        // Verify that the new customer was created successfully
        WebElement confirmationMessage = driver.findElement(By.xpath("//div[contains(text(), 'Customer created successfully')]"));
        assert confirmationMessage.isDisplayed();

        // Close the browser
        driver.quit();
    }
}
