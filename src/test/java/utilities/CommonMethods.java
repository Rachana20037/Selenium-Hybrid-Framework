package utilities;

import pageObject.LoginPage;
import org.openqa.selenium.WebDriver;

public class CommonMethods {

    public static void login(WebDriver driver, String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(5000);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        Thread.sleep(3000);
        loginPage.clickLogin();
        Thread.sleep(3000);
    }
}
