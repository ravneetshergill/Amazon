package amazon;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestAddress {

	WebDriver driver;
	POMYourAddress address;
	POMYourAccount account;
	POMLogin log;
	
	String email="amazonautomationtesting@gmail.com";
	String password="amazontesting";
	String name ="ravneet";
	String phone="4375564567";
	String addressLine="2a chruch street";
	String city="toronto";
	String postal="M5E0E1";
	
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
		address = new POMYourAddress(driver);
		
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
		account.addresses.click();
	}
	
	@BeforeMethod
	public void selectAddAddressLink() {
		address.addAddressLink.click();
	}
	
	@Test(priority=1)
	public void checkAddNewAddressLinkWorking() {
		Boolean isDisplayed = address.addAddressLink.isDisplayed();
		System.out.println(isDisplayed);
		address.addAddressLink.click();
	}
	
	@Test(priority=2)
	public void checkDefaultCountryDD() {
		String country = address.countryDropDown.getText();
		Assert.assertEquals(country, "Canada");
	}
	
	@Test(priority=3)
	public void emptyFullName() {
		Select obj1 = new Select(address.countryDropDown);
		obj1.selectByVisibleText("Canada");
		address.fullName.sendKeys("");
		address.phone.sendKeys(phone);
		address.addressLine.sendKeys(addressLine);
		address.city.sendKeys(city);
		Select obj2 = new Select(address.province);
		obj2.selectByVisibleText("Ontario");
		address.postalcode.sendKeys(postal);
		address.submitBTN.click();
		
		//To check if error message is displayed.
		Boolean isDisplayed = driver.findElement(By.id("address-ui-widgets-enterAddressFullName-full-validation-alerts")).isDisplayed();
		System.out.println(isDisplayed);
	}
	
	@Test(priority=4)
	public void emptyPhoneNumber() {
		Select obj1 = new Select(address.countryDropDown);
		obj1.selectByVisibleText("Canada");
		address.fullName.sendKeys(name);
		address.phone.sendKeys("");
		address.addressLine.sendKeys(addressLine);
		address.city.sendKeys(city);
		Select obj2 = new Select(address.province);
		obj2.selectByVisibleText("Ontario");
		address.postalcode.sendKeys(postal);
		address.submitBTN.click();
		
		//To check if error message is displayed.
		Boolean isDisplayed = driver.findElement(By.id("address-ui-widgets-enterAddressPhoneNumber-dynamic-validation-alerts")).isDisplayed();
		System.out.println(isDisplayed);
	}
	
	@Test(priority=5)
	public void emptyStreetAddress() {
		Select obj1 = new Select(address.countryDropDown);
		obj1.selectByVisibleText("Canada");
		address.fullName.sendKeys(name);
		address.phone.sendKeys(phone);
		address.addressLine.sendKeys("");
		address.city.sendKeys(city);
		Select obj2 = new Select(address.province);
		obj2.selectByVisibleText("Ontario");
		address.postalcode.sendKeys(postal);
		address.submitBTN.click();
		
		//To check if error message is displayed.
		Boolean isDisplayed = driver.findElement(By.id("address-ui-widgets-enterAddressLine2-full-validation-alerts")).isDisplayed();
		System.out.println(isDisplayed);
	}
	
	@Test(priority=6)
	public void emptyCity() {
		Select obj1 = new Select(address.countryDropDown);
		obj1.selectByVisibleText("Canada");
		address.fullName.sendKeys(name);
		address.phone.sendKeys(phone);
		address.addressLine.sendKeys(addressLine);
		address.city.sendKeys("");
		Select obj2 = new Select(address.province);
		obj2.selectByVisibleText("Ontario");
		address.postalcode.sendKeys(postal);
		address.submitBTN.click();
		
		//To check if error message is displayed.
		Boolean isDisplayed = driver.findElement(By.id("address-ui-widgets-enterAddressCity-full-validation-alerts")).isDisplayed();
		System.out.println(isDisplayed);
	}
	
	@Test(priority=7)
	public void noProvinceSelection() {
		Select obj1 = new Select(address.countryDropDown);
		obj1.selectByVisibleText("Canada");
		address.fullName.sendKeys(name);
		address.phone.sendKeys(phone);
		address.addressLine.sendKeys(addressLine);
		address.city.sendKeys(city);
		//Select obj2 = new Select(address.province);
		//obj2.selectByVisibleText("Ontario");
		address.postalcode.sendKeys(postal);
		address.submitBTN.click();
		
		//To check if error message is displayed.
		Boolean isDisplayed = driver.findElement(By.id("address-ui-widgets-enterAddressStateOrRegion-dropdown-nativeId-full-validation-alerts")).isDisplayed();
		System.out.println(isDisplayed);
	}
	
	@Test(priority=8)
	public void cityNotInProvince() {
		Select obj1 = new Select(address.countryDropDown);
		obj1.selectByVisibleText("Canada");
		address.fullName.sendKeys(name);
		address.phone.sendKeys(phone);
		address.addressLine.sendKeys(addressLine);
		address.city.sendKeys(city);
		Select obj2 = new Select(address.province);
		obj2.selectByVisibleText("Yukon");
		address.postalcode.sendKeys(postal);
		address.submitBTN.click();
		
		//To check if suggested address is displayed.
		Boolean isDisplayed = driver.findElement(By.id("address-ui-widgets-suggested-address-block_id")).isDisplayed();
		System.out.println(isDisplayed);
		
		//Display suggested address.
		String suggestedAddress = driver.findElement(By.id("address-ui-widgets-suggested-address-block_id")).getText();
		System.out.println(suggestedAddress);
	}
	
	@Test(priority=9)
	public void emptyPostalCode() {
		Select obj1 = new Select(address.countryDropDown);
		obj1.selectByVisibleText("Canada");
		address.fullName.sendKeys(name);
		address.phone.sendKeys(phone);
		address.addressLine.sendKeys(addressLine);
		address.city.sendKeys(city);
		Select obj2 = new Select(address.province);
		obj2.selectByVisibleText("Ontario");
		address.postalcode.sendKeys("");
		address.submitBTN.click();
		
		//To check if suggested address is displayed.
		Boolean isDisplayed = driver.findElement(By.id("address-ui-widgets-enterAddressPostalCode-full-validation-alerts")).isDisplayed();
		System.out.println(isDisplayed);
	}
	
	@Test(priority=10)
	public void checkDefaultCheckBoxWorking() {
		address.defaultAddressCheckbox.click();
		Boolean isSelected = address.defaultAddressCheckbox.isSelected();
		System.out.println(isSelected);
	}
	
	@Test(priority=11)
	public void validNewAddress() {
		Select obj1 = new Select(address.countryDropDown);
		obj1.selectByVisibleText("Canada");
		address.fullName.sendKeys(name);
		address.phone.sendKeys(phone);
		address.addressLine.sendKeys(addressLine);
		address.city.sendKeys(city);
		Select obj2 = new Select(address.province);
		obj2.selectByVisibleText("Ontario");
		address.postalcode.sendKeys(postal);
		address.defaultAddressCheckbox.click();
		address.submitBTN.click();
	}
	
	@Test(priority=12)
	public void selectAddedAddressDefault() {
		driver.navigate().back();
		driver.findElement(By.id("ya-myab-set-default-shipping-btn-1")).click();
	}
	
	@Test(priority=13)
	public void removeAddress() {
		driver.navigate().back();
		driver.findElement(By.id("ya-myab-address-delete-btn-1")).click();
		driver.findElement(By.id("deleteAddressModal-1-submit-btn"));
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
