package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMYourAccount {
	WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"a-page\"]/div[2]/div/div[2]/div[1]") WebElement orders;
	@FindBy(xpath="//*[@id=\"a-page\"]/div[2]/div/div[2]/div[2]") WebElement security;
	@FindBy(xpath="//*[@id=\"a-page\"]/div[2]/div/div[3]/div[1]") WebElement addresses;
	@FindBy(xpath="//*[@id=\"a-page\"]/div[2]/div/div[3]/div[2]") WebElement payments;
	
	public POMYourAccount(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
}
