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

public class TestRegistration {
	WebDriver driver;
	POMRegistration reg;
	
	String name = "ravneeet";
	String email = "ravneet@email.com";
	String password = "Ravneet.1234";
	
	@BeforeSuite
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}
	
	@BeforeTest
	public void setBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		reg = new POMRegistration(driver);
	}
	
	@BeforeMethod
	public void setURL() {
		driver.get("https://www.amazon.ca/");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList"))).build().perform();
		driver.findElement(By.linkText("Start here.")).click();
	}
	
	//Test to check empty name field.
	@Test(priority=1) 
	public void noName() { 
		reg.name.sendKeys("");
		reg.emailORmobile.sendKeys(email); 
		reg.password.sendKeys(password);
		reg.passwordAgain.sendKeys(password); 
		reg.verifyEmailBTN.isDisplayed();
		reg.continueBTN.click();
		WebElement error = driver.findElement(By.id("auth-customerName-missing-alert"));
		error.isDisplayed();
		String errorMessage = error.getText();
		System.out.println(errorMessage);
	}
	
	//Test to check empty email/mobile field.
	@Test(priority=2) 
	public void noEmailorMobile() { 
		reg.name.sendKeys(name);
		reg.emailORmobile.sendKeys(""); 
		reg.password.sendKeys(password);
		reg.passwordAgain.sendKeys(password); 
		reg.continueBTN.click();
		WebElement error = driver.findElement(By.id("auth-email-missing-alert"));
		error.isDisplayed();
		String errorMessage = error.getText();
		System.out.println(errorMessage);
	}
	
	//Test to check partially filled email.
	@Test(priority=3) 
	public void partialEmail() { 
		reg.name.sendKeys(name);
		reg.emailORmobile.sendKeys("ravneet.com"); 
		reg.password.sendKeys(password);
		reg.passwordAgain.sendKeys(password); 
		reg.verifyEmailBTN.isDisplayed();
		reg.continueBTN.click();
		WebElement error = driver.findElement(By.id("auth-email-invalid-claim-alert"));
		error.isDisplayed();
		String errorMessage = error.getText();
		System.out.println(errorMessage);
	}
	
	
	//Test to check partially filled mobile number.
	@Test(priority=4) 
	public void partialMobile() { 
		reg.name.sendKeys(name);
		reg.emailORmobile.sendKeys("437"); 
		reg.password.sendKeys(password);
		reg.passwordAgain.sendKeys(password); 
		reg.verifyMolbileBTN.isDisplayed();
		driver.findElement(By.className("country-display-text")).isDisplayed();
		reg.continueBTN.click(); 
		WebElement error = driver.findElement(By.cssSelector("#auth-error-message-box > div > div > ul > li > span"));
		error.isDisplayed();
		String errorMessage = error.getText();
		System.out.println(errorMessage);
	}
	 
	//Test to verify already existing account.
	@Test(priority=5)
	public void existingEmail() {
		reg.name.sendKeys(name);
		reg.emailORmobile.sendKeys("ravneetsshergill@gmail.com"); 
		reg.password.sendKeys(password);
		reg.passwordAgain.sendKeys(password); 
		reg.verifyEmailBTN.isDisplayed();
		reg.continueBTN.click(); 
		WebElement act = driver.findElement(By.xpath("//*[@id=\"authportal-main-section\"]/div[2]/div/div[2]/h4[1]"));
		act.isDisplayed();
		String actual = act.getText();
		System.out.println(actual);
	}
	
	//Test to check empty password field.
	@Test(priority=6) 
	public void noPassword() { 
		reg.name.sendKeys(name);
		reg.emailORmobile.sendKeys(email); 
		reg.password.sendKeys("");
		reg.passwordAgain.sendKeys(password); 
		reg.verifyEmailBTN.isDisplayed();
		reg.continueBTN.click();
		String actual = driver.findElement(By.id("auth-password-missing-alert")).getText();
		System.out.println(actual);
		Assert.assertEquals(actual, "Minimum 6 characters required");
	}
	
	//Test to check password less than 6 characters.
	@Test(priority=7)
	public void invalidPassword() {
		reg.name.sendKeys(name);
		reg.emailORmobile.sendKeys(email);
		reg.password.sendKeys("rav");
		reg.passwordAgain.sendKeys("rav");
		reg.verifyEmailBTN.isDisplayed();
		reg.continueBTN.click();
		String actual = driver.findElement(By.id("auth-password-invalid-password-alert")).getText();
		System.out.println(actual);
		Assert.assertEquals(actual, "Minimum 6 characters required");
	}
	
	//Test to check empty password again field.
	@Test(priority=8)
	public void noPasswordAgain() {
		reg.name.sendKeys(name);
		reg.emailORmobile.sendKeys(email);
		reg.password.sendKeys(password);
		reg.passwordAgain.sendKeys("");
		reg.verifyEmailBTN.isDisplayed();
		reg.continueBTN.click();
		WebElement error = driver.findElement(By.id("auth-passwordCheck-missing-alert"));
		error.isDisplayed();
		String errorMessage = error.getText();
		System.out.println(errorMessage);
	}
	
	//Test to check password again not same as password.
	@Test(priority=9)
	public void invalidPasswordAgain() {
		reg.name.sendKeys(name);
		reg.emailORmobile.sendKeys(email);
		reg.password.sendKeys(password);
		reg.passwordAgain.sendKeys("ravneet.1234");
		reg.verifyEmailBTN.isDisplayed();
		reg.continueBTN.click();
		String actual = driver.findElement(By.id("auth-password-mismatch-alert")).getText();
		System.out.println(actual);
		Assert.assertEquals(actual, "Passwords do not match");
	}
	
	//Test to check valid fields.
	@Test(priority=10)
	public void validCredentials() {
		reg.name.sendKeys(name);
		reg.emailORmobile.sendKeys("rv@gmail.com");
		reg.password.sendKeys(password);
		reg.passwordAgain.sendKeys(password);
		reg.verifyEmailBTN.isDisplayed();
		reg.continueBTN.click();
	}
	
	@AfterTest 
	public void closeBrowser() { 
		driver.close(); 
	} 
}
