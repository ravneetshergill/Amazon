package amazon;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestYourPayment {

	WebDriver driver;
	POMYourPayment payment;
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
		payment = new POMYourPayment(driver);
		
		driver.get("https://www.amazon.ca/");
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("nav-link-accountList"))).build().perform();
		driver.findElement(By.className("nav-action-inner")).click();
		
		log.email.sendKeys(email);
		log.continueBTN.click();
		log.password.sendKeys(password);
		log.rememberMeCheckbox.click();
		log.signInBTN.click();
		
		log.helloUser.click();
	}
	
	@BeforeMethod
	public void selectLoginNSecurity(){
		account.payments.click();
	}
	
	//Test to add new card
	@Test(priority=1)
	public void addCard() throws InterruptedException {
		//select add new payment
		payment.addPaymentOption.click();
		
		//select add new credit/debit cars
		payment.addCreditOrDebitOption.click();
	
		//enter card no
		payment.cardNo.sendKeys("4987654321098769");
		
		//enter card name
		payment.typeCardName("ravneet");
		
		//select month
		Select obj1= new Select(payment.month);
		obj1.selectByIndex(4);
		
		//select year
		Select obj2= new Select(payment.year);
		obj2.selectByIndex(1);
		
		//add card
		payment.saveBTN.click();
	}
	
	//Test to edit card details
	@Test(priority=2)
	public void editCard() throws InterruptedException {
		//select card
		driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[2]/div/div/div[2]/div[3]/div/div[1]/div[2]/div/div/div[2]/div/div/div[1]/div/img")).click();
		//select edit
		driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[2]/div/div/div[2]/div[3]/div/div[2]/div/div/div/div/div[2]/div[3]/div/div[1]/div/a")).click();
		//change name
		driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div/form[2]/div[2]/div/div[1]/div[1]/div/input")).sendKeys("ravneet inder");
		//click save button
		driver.findElement(By.xpath("/html/body/div[11]/div/div/div/div/form[2]/div[3]/div[2]/span[2]/span/input")).click();
	}
	
	//Test to check deleting a card
	@Test(priority=3)
	public void deleteCard() throws InterruptedException {
		//choose card
		driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[2]/div/div/div[2]/div[3]/div/div[1]/div[2]/div/div/div[2]/div/div/div[1]/div/img")).click();
		//click edit
		driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[2]/div/div/div[2]/div[3]/div/div[2]/div/div/div/div/div[2]/div[3]/div/div[1]/div/a")).click();
		//select remove from wallet
		driver.findElement(By.xpath("/html/body/div[10]/div/div/div/div/form[2]/div[3]/div[1]/span/input")).click();
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
