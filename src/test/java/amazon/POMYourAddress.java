package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMYourAddress {

	WebDriver driver;
	
	@FindBy(id="ya-myab-address-add-link") WebElement addAddressLink;
	@FindBy(id="address-ui-widgets-countryCode-dropdown-nativeId") WebElement countryDropDown;
	@FindBy(id="address-ui-widgets-enterAddressFullName") WebElement fullName;
	@FindBy(id="address-ui-widgets-enterAddressPhoneNumber") WebElement phone;
	@FindBy(id="address-ui-widgets-enterAddressLine1") WebElement addressLine;
	@FindBy(id="address-ui-widgets-enterAddressCity") WebElement city;
	@FindBy(id="address-ui-widgets-enterAddressStateOrRegion-dropdown-nativeId") WebElement province;
	@FindBy(id="address-ui-widgets-enterAddressPostalCode") WebElement postalcode;
	@FindBy(id="address-ui-widgets-use-as-my-default") WebElement defaultAddressCheckbox;
	@FindBy(xpath="//*[@id=\"address-ui-widgets-enterAddressFormContainer\"]/div[4]/a/span/span") WebElement additionalInstructions;
	@FindBy(xpath="//*[@id=\"address-ui-widgets-delivery-instructions-desktop-widget-html-container\"]/div/div/div[1]/div[2]/span/div/span[1]/span/button") WebElement property;
	@FindBy(xpath="//*[@id=\"address-ui-widgets-delivery-instructions-desktop-widget-html-container\"]/div/div/div[1]/div[3]/div[1]/div/div/div/span/div[1]/label/span") WebElement whereToLeave;
	@FindBy(xpath="//*[@id=\"address-ui-widgets-delivery-instructions-desktop-widget-html-container\"]/div/div/div[1]/div[6]/div[1]/div/div/div/span/div[1]/label/input") WebElement dropLocation;
	@FindBy(className="a-declarative") WebElement securityCode;
	//@FindBy(xpath="") WebElement se;
	@FindBy(id="address-ui-widgets-form-submit-button") WebElement submitBTN;
	
	public POMYourAddress(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
