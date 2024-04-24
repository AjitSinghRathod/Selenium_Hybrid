package TestCases;

import Utilities.BaseClass;
import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utilities.ExcelUtils;
import Utilities.PropertyReader;


public class LoginTest extends BaseClass {

    @Test
    public void testLoginFunctionality() throws Exception {
        // Initialize the LoginPage object
        LoginPage loginPage = new LoginPage(driver);

        // Read test data from Excel
        ExcelUtils excelUtils = new ExcelUtils();
        String testDataPath = PropertyReader.getProperty("testDataPath");
        excelUtils.setExcelFile(testDataPath, "test");
        String username = excelUtils.getCellData(1, 1);
        String password = excelUtils.getCellData(1, 2);

        // Perform login
        
        loginPage.loginAs(username, password);

        // Assert the login success by checking if the URL is as expected
        String expectedUrl = PropertyReader.getProperty("baseUrl");
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "The user is not redirected to the dashboard after login.");
    }
}