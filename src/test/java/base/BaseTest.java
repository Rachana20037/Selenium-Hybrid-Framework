package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static Properties prop = new Properties();

	public static FileReader fr;

	
	@BeforeMethod

	public void setUp() throws IOException {
		if(driver==null)
		{
			 
			fr = new FileReader(System.getProperty("user.dir")+ "\\src\\test\\resources\\configfiles\\config.properties");
			prop.load(fr);

		}
		
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();

		}
		
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();

		}
		else if(prop.getProperty("browser").equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			
		}
		driver.get(prop.getProperty("testUrl")); //URL
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void BrowserClose() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
		System.out.println("Browser is closed");
		
	}
	
}
