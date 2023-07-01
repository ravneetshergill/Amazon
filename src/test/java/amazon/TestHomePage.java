package amazon;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestHomePage {

	WebDriver driver;
	POMHomePage home;
	
	@BeforeSuite
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}
	
	@BeforeTest
	public void setBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		home = new POMHomePage(driver);
	}
	
	@BeforeMethod
	public void setURL() {
		driver.get("https://www.amazon.ca/");
	}
	
	//Test to check if amazon logo is displayed.
	@Test(priority=1)
	public void checkLogo() {
		if(home.logo.isDisplayed()==true) {
			System.out.println("Displayed");
		}
		else {
			System.out.println("Not Displayed");
		}
	}
	
	//Test to check if Hello, Username is displayed and mouser hover is working.
	@Test(priority=2)
	public void checkHelloUser() {
		home.helloUser.isDisplayed();
		Actions action = new Actions(driver);
		action.moveToElement(home.helloUser).build().perform();
	}
	
	//Test to check if Hello, address is displayed and mouse hover is working.
	@Test(priority=3)
	public void checkSelectAddresss() {
		home.helloAddress.isDisplayed();
		Actions action = new Actions(driver);
		action.moveToElement(home.helloAddress).build().perform();
	}
	
	//Test to check if the search bar is displayed
	@Test(priority=4)
	public void searchBar() {
		home.search.isDisplayed();
	}
	
	//Test to check if search button is displayed.
	@Test(priority=5)
	public void searchBtn() {
		home.searchButton.isDisplayed();
	}
	
	//Test to check if the search drop down is displayed and working.
	@Test(priority=6)
	public void checkSearchDropDown() {
		Select obj= new Select(home.searchDropDown);
		obj.selectByIndex(2);
	}
	
	//Test to check if shoppingCart is displayed and cart count.
	@Test(priority=7)
	public void shoppingCart() {
		home.cartIcon.isDisplayed();
		String count = home.cartCount.getText();
		System.out.println(count);
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
	
