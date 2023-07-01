package amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POMProductSearch {
	WebDriver driver;
	
	@FindBy(id="twotabsearchtextbox") WebElement search;
	@FindBy(id="nav-search-submit-button") WebElement searchButton;
	@FindBy(id="searchDropdownBox") WebElement searchDropDown;
	@FindBy(id="s-result-sort-select") WebElement sortByDropDown;
	@FindBy(xpath="//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[3]") WebElement relevantProducts;
	@FindBy(id="title") WebElement productName;
	@FindBy(id="imgTagWrapperId") WebElement productPhoto;
	@FindBy(id="feature-bullets") WebElement productDescription;
	@FindBy(id="corePrice_desktop") WebElement productPrice;
	@FindBy(id="acrPopover") WebElement custReviews;
	@FindBy(id="acrCustomerReviewText") WebElement custRatings;
	@FindBy(xpath="//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[54]/div/div/span/a[3]") WebElement nextPage;
	
	public POMProductSearch(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void searchProduct(String item) {
		search.sendKeys(item);
	}
}
