package amazon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestLoginNSecurity {

	WebDriver driver;
	POMLoginNSecurity security;
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
		security = new POMLoginNSecurity(driver);
		
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
	}
	
	@BeforeMethod
	public void selectLoginNSecurity(){
		account.security.click();
	}
	
	//Test to check edit name
	@Test(priority=1)
	public void editName() {
		System.out.println(driver.findElement(By.xpath("//*[@id=\"cnep_1a_name_form\"]/div/div/div[1]/div[2]")).getText());
		driver.findElement(By.id("auth-cnep-edit-name-button")).click();
		driver.findElement(By.id("ap_customer_name")).clear();
		driver.findElement(By.id("ap_customer_name")).sendKeys("Ravneetinder Singh Shergill");
		driver.findElement(By.id("cnep_1C_submit_button")).click();
		System.out.println(driver.findElement(By.xpath("//*[@id=\"cnep_1a_name_form\"]/div/div/div[1]/div[2]")).getText());
	}
	
	//Test to check edit password
	@Test
	public void editPassword() throws InterruptedException {
		//click edit
		driver.findElement(By.id("auth-cnep-edit-password-button")).click();
		//enter old password
		driver.findElement(By.id("ap_password")).sendKeys(password);
		//enter new password
		driver.findElement(By.id("ap_password_new")).sendKeys("ravneet");
		//confirm new password
		driver.findElement(By.id("ap_password_new_check")).sendKeys("ravneet");
		//submit button to submit the password
		//driver.findElement(By.id("cnep_1D_submit_button")).click();
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void closBrowser() {
		driver.close();
	}
}
