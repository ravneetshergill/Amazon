package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMRegistration {
	WebDriver driver;
	
	@FindBy(id="ap_customer_name") WebElement name;
	@FindBy(id="ap_email") WebElement emailORmobile;
	@FindBy(id="ap_password") WebElement password;
	@FindBy(id="ap_password_check") WebElement passwordAgain;
	@FindBy(id="continue") WebElement continueBTN;
	@FindBy(className="email-text") WebElement verifyEmailBTN;
	@FindBy(className="phone-text") WebElement verifyMolbileBTN;
	
	public POMRegistration(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
