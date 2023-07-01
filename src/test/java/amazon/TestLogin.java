package amazon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestLogin{
	WebDriver driver;
	POMLogin login;
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
		login =new POMLogin(driver);
		account = new POMYourAccount(driver);
		 
	}
	
	@BeforeMethod
	public void setURL() {
		driver.get("https://www.amazon.ca/");
		
		Actions action = new Actions(driver);
		action.moveToElement(login.helloUser).build().perform();
		driver.findElement(By.className("nav-action-inner")).click();
	}
	
	//Test to check no email enetered.
	@Test(priority=1)
	public void noEmail() {
		login.email.sendKeys("");
		login.continueBTN.click();
		WebElement error = driver.findElement(By.id("auth-email-missing-alert"));
		error.isDisplayed();
		String errorMessage = error.getText();
		System.out.println(errorMessage);
	}
	
	//Test to check non registered email.
	@Test(priority=2)
	public void emailNotRegistered() {
		login.email.sendKeys("abcnbjasbjda@gmail.com");
		login.continueBTN.click();
		WebElement error =driver.findElement(By.cssSelector("#auth-error-message-box > div > div > ul > li > span"));
		error.isDisplayed();
		String errorMessage = error.getText();
		System.out.println(errorMessage);
		Assert.assertEquals(errorMessage, "We cannot find an account with that e-mail address");
	}
	
	//Test to check valid email and remember me checkbox.
	@Test(priority=3)
	public void validRegsiteredEmail() {
		login.email.sendKeys(email);
		login.continueBTN.click();
		login.password.sendKeys(password);
		login.rememberMeCheckbox.click();
		login.rememberMeCheckbox.isSelected();
		login.signInBTN.click();
		
		WebElement hello = login.helloUser;
		
		//To check if user is logged in and name displayed is correct.
		String helloText = hello.getText();
		System.out.println(helloText);
	}
	
	@AfterTest 
	public void closeBrowser() { 
		driver.close(); 
	} 
}
