package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMYourPayment {
	WebDriver driver;

	@FindBy(xpath="/html/body/div[1]/div[4]/div/div[2]/div/div/div[2]/div[3]/div/div[1]/div[3]/div/div/div/div[2]/a") WebElement addPaymentOption;
	@FindBy(xpath="/html/body/div[1]/div[4]/div/div[2]/div/div/div[2]/div[3]/div/div[2]/div/div/div/div/div[2]/div/div[2]/span/span[1]/span/input") WebElement addCreditOrDebitOption;
	@FindBy(name="addCreditCardNumber") WebElement cardNo;
	@FindBy(name="ppw-accountHolderName") WebElement nameOnCard;
	@FindBy(name="ppw-expirationDate_month") WebElement month;
	@FindBy(name="ppw-expirationDate_year") WebElement year;
	@FindBy(name="ppw-widgetEvent:AddCreditCardEvent") WebElement addCardBTN;
	@FindBy(xpath="ppw-widgetEvent:CancelAddCreditCardEvent") WebElement cancelBTN;
	@FindBy(name="ppw-widgetEvent:AddCreditCardEvent") WebElement saveBTN;
	
	public POMYourPayment(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	
	public void typeCardNo(String cardNo) {
		this.cardNo.sendKeys(cardNo);
	}
	
	public void typeCardName(String name) {
	nameOnCard.sendKeys(name);
	}
	
}
