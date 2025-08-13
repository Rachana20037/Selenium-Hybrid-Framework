package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtils {

//    public static void captureScreenshot(WebDriver driver, String testName, String status) {
//        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String path = System.getProperty("user.dir") + "\\screenshots\\" + testName + "_" + status + "_" + timestamp + ".png";
//        File dest = new File(path);
//        try {
//            FileHandler.copy(src, dest);
//            System.out.println("📸 Screenshot taken: " + path);
//        } catch (IOException e) {
//            System.out.println("❌ Screenshot failed: " + e.getMessage());
//        }
//    }

/*	public static void takeScreenshot(WebDriver driver, String fileName) {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);

	    try {
	        FileHandler.copy(source, new File("./screenshots/" + fileName + ".png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
*/
	
	  public static void takeScreenshot(WebDriver driver, String screenshotName) {
	        try {
	            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	            String destPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + timestamp + ".png";
	            FileUtils.copyFile(srcFile, new File(destPath));
	            System.out.println("📸 Screenshot saved at: " + destPath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
}
