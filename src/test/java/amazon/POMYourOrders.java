package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMYourOrders {
	WebDriver driver;
	
	@FindBy(id="time-filter") WebElement timeDD;
	@FindBy(xpath="//*[@id=\"a-page\"]/section/div[1]/div[3]/ul/li[2]/a") WebElement buyAgain;
	@FindBy(xpath="//*[@id=\"a-page\"]/section/div[1]/div[3]/ul/li[3]/a") WebElement notShipped;
	@FindBy(xpath="//*[@id=\"a-page\"]/section/div[1]/div[3]/ul/li[4]/a") WebElement cancelledOrders;
	@FindBy(id="searchOrdersInput") WebElement searchOrderInput;
	@FindBy(id="a-autoid-0") WebElement searchOrdersBTN;
	
	public POMYourOrders(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
