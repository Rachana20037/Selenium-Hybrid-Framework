package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver; //Instance created

	public LoginPage(WebDriver driver) { //Constructor
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initializes WebElements
	}

//    @FindBy(xpath = "//input[@type='username']") //input[@placeholder='Email / Username']  // Replace with your actual XPath
//    @FindBy(xpath = "//input[@name='username' and @placeholder='Email / Username']")  // Replace with your actual XPath
	@FindBy(xpath = "//input[@type='username']")
//    @FindBy(xpath = "//input[@placeholder='Email / Username']") 
	WebElement usernameInput;

	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordInput;

	@FindBy(xpath = "//button[.='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath = "(//div[.='Account not found.'])[1]")
	public WebElement ErrorMsg;

	public void enterUsername(String username) {
		usernameInput.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}

	public void clickLogin() {
		loginBtn.click();
	}
	public String getErrorMsg() {
		return ErrorMsg.getText();
	}
}
