package amazon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestYourAccount{
	
	WebDriver driver;
	POMLogin log;
	POMYourAccount account;
	
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
		driver.get("https://www.amazon.ca/");
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList"))).build().perform();
		driver.findElement(By.className("nav-action-inner")).click();
		
		log.email.sendKeys(email);
		log.continueBTN.click();
		log.password.sendKeys(password);
		log.rememberMeCheckbox.isSelected();
		log.signInBTN.click();
	}
	
	@BeforeMethod
	public void clickHelloUser() {
		driver.findElement(By.id("nav-link-accountList")).click();
	}
	
	@Test(priority=1)
	public void checkYourOrdersOption() {
		Boolean isDisplayed = account.orders.isDisplayed();
		System.out.println(isDisplayed);
		account.orders.click();
	}
	
	@Test(priority=2)
	public void loginAndSecurity() {
		Boolean isDisplayed = account.security.isDisplayed();
		System.out.println(isDisplayed);
		account.security.click();
	}
	
	@Test(priority=3)
	public void selectYourAddresses() {
		Boolean isDisplayed = account.addresses.isDisplayed();
		System.out.println(isDisplayed);
		account.addresses.click();
	}
	
	@Test(priority=4)
	public void selectYourPayments() {
		Boolean isDisplayed = account.payments.isDisplayed();
		System.out.println(isDisplayed);
		account.payments.click();
	}
	
	@AfterTest
	public void goBack() {
		driver.navigate().back();
	}
	
	@AfterSuite 
	public void closeBrowser() { 
		driver.close(); 
	}
}
