package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class POMLoginNSecurity {
	
	WebDriver driver;

	public POMLoginNSecurity(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
}
