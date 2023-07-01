package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMLogin {
	WebDriver driver;
	
	@FindBy(id="ap_email") WebElement email;
	@FindBy(id="ap_password") WebElement password;
	@FindBy(id="continue") WebElement continueBTN;
	@FindBy(name="rememberMe") WebElement rememberMeCheckbox;
	@FindBy(id="signInSubmit") WebElement signInBTN;
	@FindBy(id="nav-link-accountList-nav-line-1") WebElement helloUser;
	
	public POMLogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
