package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMHomePage {
	WebDriver driver;
	
	@FindBy(id="nav-logo-sprites") WebElement logo;
	@FindBy(id="nav-link-accountList-nav-line-1") WebElement helloUser;
	@FindBy(id="glow-ingress-block") WebElement helloAddress;
	@FindBy(id="twotabsearchtextbox") WebElement search;
	@FindBy(id="nav-search-submit-button") WebElement searchButton;
	@FindBy(id="searchDropdownBox") WebElement searchDropDown;
	@FindBy(id="nav-cart-count-container") WebElement cartIcon;
	@FindBy(id="nav-cart-count") WebElement cartCount;
	
	public POMHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
}
