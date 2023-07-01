package amazon;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestYourOrders {
	
	WebDriver driver;
	POMYourOrders orders;
	POMYourAccount account;
	POMLogin log;
	
	String email="amazonautomationtesting@gmail.com";
	String password="amazontesting";
	
	@BeforeSuite
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}
	
	@BeforeTest
	public void setBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		log = new POMLogin(driver);
		account = new POMYourAccount(driver);
		orders = new POMYourOrders(driver);
		
		driver.get("https://www.amazon.ca/");
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList"))).build().perform();
		driver.findElement(By.className("nav-action-inner")).click();
		
		log.email.sendKeys(email);
		log.continueBTN.click();
		log.password.sendKeys(password);
		log.rememberMeCheckbox.isSelected();
		log.signInBTN.click();
		
		log.helloUser.click();
		account.orders.click();
	}
	
	@Test(priority=1)
	public void timeDropDown() throws InterruptedException {
		Select obj= new Select(orders.timeDD);
		obj.selectByIndex(2);
	}
	
	@Test(priority=2)
	public void selectBuyAgain() {
		orders.buyAgain.click();
	}
	
	@Test(priority=3)
	public void selectNotYetShipped() {
		orders.notShipped.click();
		WebElement msg =driver.findElement(By.id("ordersContainer"));
		Boolean isDisplayed = msg.isDisplayed();
		System.out.println(isDisplayed);
		System.out.println(msg.getText());
	}
	
	@Test(priority=4)
	public void selectCancelledOrders() {
		orders.cancelledOrders.click();
	}
	
	@Test(priority=5)
	public void searchOrderedProduct() {
		orders.searchOrderInput.sendKeys("tripod");
		orders.searchOrdersBTN.click();
	}
	
	@AfterMethod
	public void back() {
		driver.navigate().back();
	}
	
	@AfterSuite
	public void closeBrowser() {
		driver.close();
	}
	
}
