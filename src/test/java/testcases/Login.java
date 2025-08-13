package testcases;

import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import pageObject.LoginPage;
import utilities.ReadXLSdata;
import utilities.ScreenshotUtils;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Login extends BaseTest {

    @Test(dataProvider = "login_testdata")
    public void loginTest(String username, String password, String data_type) throws InterruptedException {
        System.out.println("🔐 Login Test Started for user: " + username);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        Thread.sleep(2000); // Ideally use explicit waits

        if (data_type.equalsIgnoreCase("valid")) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleIs("Atlas API Manager Console"));

            String title = driver.getTitle();
            if (title.equals("Atlas API Manager Console")) {
                System.out.println("✅ Login Passed for valid credentials.");
                ScreenshotUtils.takeScreenshot(driver, "login_pass_" + username);
            } else {
                System.out.println("❌ Login Failed unexpectedly for valid credentials.");
                ScreenshotUtils.takeScreenshot(driver, "login_unexpected_fail_" + username);
            }
        } else if (data_type.equalsIgnoreCase("invalid")) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(loginPage.ErrorMsg));

            String errorMsg = loginPage.getErrorMsg();
            System.out.println("⚠️ Error Message: " + errorMsg);
            ScreenshotUtils.takeScreenshot(driver, "login_fail_" + username);
        } else {
            System.out.println("❓ Unexpected 'expected' value in test data: " + data_type);
        }

        System.out.println("🧪 Test Completed for: " + username);
    }

    @DataProvider(name = "login_testdata")
    public Object[][] getLoginTestData() {
        String sheetName = "LoginTest";
        ReadXLSdata read = new ReadXLSdata();

        int rowCount = read.getRowCount(sheetName);
        int colCount = read.getColumnCount(sheetName);
        Object[][] data = new Object[rowCount - 1][colCount]; // Skip header

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = read.getCellData(sheetName, i, j);
            }
        }

        return data;
    }
}
