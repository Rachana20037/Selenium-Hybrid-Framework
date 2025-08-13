package listeners;

import base.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());
        takeScreenshot(result, "FAIL");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getName());
        takeScreenshot(result, "PASS");
    }

    public void takeScreenshot(ITestResult result, String status) {
        WebDriver driver = BaseTest.driver;
        if (driver == null) {
            System.out.println("⚠️ WebDriver is NULL. Cannot take screenshot.");
            return;
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = result.getName() + "_" + status + "_" + timestamp + ".png";
        String destDir = System.getProperty("user.dir") + "/screenshots/";

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(destDir + screenshotName);
        dest.getParentFile().mkdirs();  // Create folder if not exists
        try {
            org.apache.commons.io.FileUtils.copyFile(file, dest);
            System.out.println("📸 Screenshot saved to: " + dest.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
