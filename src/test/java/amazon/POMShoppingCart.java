package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMShoppingCart {
	
	WebDriver driver;
	
	@FindBy(id="nav-cart") WebElement cartBTN;
	@FindBy(id="quantity") WebElement quantityDD;
	@FindBy(id="add-to-cart-button") WebElement addToCartBTN;
	@FindBy(id="nav-cart-count") WebElement itemCount;
	@FindBy(xpath="//*[@id=\"sc-subtotal-amount-activecart\"]/span") WebElement price;
	@FindBy(xpath="/html/body/div[1]/div[2]/div[3]/div[5]/div/div[2]/div[1]/div/form/div[2]/div[3]/div[4]/div/div[2]/div[1]/span[3]/span/input") WebElement saveLater;
	@FindBy(name="proceedToRetailCheckout") WebElement proceedToChekout;
	
	public POMShoppingCart(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
