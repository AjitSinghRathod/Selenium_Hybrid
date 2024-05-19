package TestCases;

import Utilities.BaseClass;
import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utilities.ExcelUtils;
import Utilities.PropertyReader;
import PageObjects.CreateCustomer;


public class LoginTest extends BaseClass {

    @Test(priority = 1)
    public void testLoginFunctionality() throws Exception {
        // Initialize the LoginPage object
        LoginPage loginPage = new LoginPage(driver);

        // Read test data from Excel
        ExcelUtils excelUtils = new ExcelUtils();
        String testDataPath = PropertyReader.getProperty("testDataPath");
        excelUtils.setExcelFile(testDataPath, "test");
        String username = "admin";
        String password = "s!t@cgn22";

        // Perform login
        
        loginPage.loginAs(username, password);

        // Assert the login success by checking if the URL is as expected
        String expectedUrl = "https://crmsit.one.al/index.php";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "The user is not redirected to the dashboard after login.");
    }


    @Test(priority = 2)
    public void testCreateCustomer() throws Exception {
        // Test method for creating a customer
        CreateCustomer createCustomer = new CreateCustomer(driver);
        createCustomer.createcustomer();
        // Add your test steps here
    }

}
