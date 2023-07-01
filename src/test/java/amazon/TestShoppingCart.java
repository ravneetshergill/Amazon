package amazon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestShoppingCart {

	WebDriver driver;
	POMShoppingCart pomcart;
	POMProductSearch ps;
	POMLogin login;
	
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
		
		pomcart = new POMShoppingCart(driver);
		ps = new POMProductSearch(driver);
		login = new POMLogin(driver);
	}
	
	@BeforeMethod
	public void setURL() {
		driver.get("https://www.amazon.ca/");
	}
	
	//Test to check is item is added to the cart
	@Test(priority=1)
	public void addItemToCart() {
		//search item
		ps.searchProduct("phone case");
		ps.searchButton.click();
		
		//select item
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[8]/div/div/div/div/div[2]/div[2]/h2/a/span")).click();
		
		//select number of items
		Select obj= new Select(pomcart.quantityDD);
		obj.selectByIndex(3);
		
		//Add item to cart.
		pomcart.addToCartBTN.click();
		
		//check item is added and check the product count
		System.out.println(pomcart.itemCount.getText());
	}
	
	//Test to add same item to cart and to check if item count is updated
	@Test(priority=2)
	public void addSameItemAgain() throws InterruptedException {
		
		//search item
		ps.searchProduct("phone case");
		ps.searchButton.click();
				
		//select item
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[8]/div/div/div/div/div[2]/div[2]/h2/a/span")).click();
				
		//select number of items
		Select obj= new Select(pomcart.quantityDD);
		obj.selectByIndex(3);
				
		//Add item to cart.
		pomcart.addToCartBTN.click();
		
		//search item
		ps.searchProduct("phone case");
		ps.searchButton.click();
				
		//select item
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[8]/div/div/div/div/div[2]/div[2]/h2/a/span")).click();
				
		//select number of items
		obj.selectByIndex(3);
				
		//Add item to cart.
		pomcart.addToCartBTN.click();
		
		//Display count of elements
		System.out.println(pomcart.itemCount.getText());
		Thread.sleep(2000);	
	}
	
	//Test to add multiple items of different types and check product count.
	@Test(priority=3)
	public void addDifferentMultipleItems() {
		//search item
		ps.searchProduct("phone case");
		ps.searchButton.click();
		
		//select item
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[8]")).click();
						
		//Add item to cart.
		pomcart.addToCartBTN.click();
		
		//search 2nd item
		ps.searchProduct("car toy");
		ps.searchButton.click();
		//select item
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[7]")).click();
						
		//Add 2nd item to cart.
		pomcart.addToCartBTN.click();
		
		//Display count of elements
		System.out.println(pomcart.itemCount.getText());
		
		//open cart
		pomcart.cartBTN.click();
	}
	
	//Test to change quantity of item in cart
	@Test(priority=4)
	public void changeQuanityInCart() {
		//search item
		ps.searchProduct("phone case");
		ps.searchButton.click();
				
		//select item
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[8]")).click();
				
		//select number of items
		Select obj= new Select(pomcart.quantityDD);
		obj.selectByIndex(4);
				
		//Add item to cart.
		pomcart.addToCartBTN.click();
		
		//open cart
		pomcart.cartBTN.click();
		
		//change quantity
		obj.selectByIndex(2);
		
		pomcart.price.isDisplayed();
	}
	
	//Test to check if an item gets deleted from cart
	@Test(priority=5)
	public void deleteItemFromCart() throws InterruptedException {
		//search item
		ps.searchProduct("phone case");
		ps.searchButton.click();
						
		//select item
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[1]/div[1]/div[8]")).click();
						
		//select number of items
		Select obj= new Select(pomcart.quantityDD);
		obj.selectByIndex(4);
						
		//Add item to cart.
		pomcart.addToCartBTN.click();
		
		//open cart
		pomcart.cartBTN.click();
		
		//Delete item from cart
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[5]/div/div[2]/div[1]/div/form/div[2]/div[3]/div[4]/div/div[2]/div[1]/span[2]/span")).click();
		
		Thread.sleep(3000);
	}
	
	//Test to check click on item in cart.
	@Test(priority=6)
	public void checkCartItem() throws InterruptedException {
		//search item
		ps.searchProduct("phone case");
		ps.searchButton.click();
								
		//select item
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[1]/div[1]/div[8]")).click();
							
		//select number of items
		Select obj= new Select(pomcart.quantityDD);
		obj.selectByIndex(4);
								
		//Add item to cart.
		pomcart.addToCartBTN.click();
				
		//open cart
		pomcart.cartBTN.click();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[5]/div/div[2]/div[1]/div/form/div[2]/div[3]/div[4]/div/div[2]/ul/li[1]/span/a/span[1]/span/span[2]")).click();
		
		Thread.sleep(2000);
	}
	
	//Test to check if item can be saved for later.
	@Test(priority=7)
	public void saveLater() throws InterruptedException {
		//search item
		ps.searchProduct("phone case");
		ps.searchButton.click();
								
		//select item
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[8]")).click();
								
		//select number of items
		Select obj= new Select(pomcart.quantityDD);
		obj.selectByIndex(4);
								
		//Add item to cart.
		pomcart.addToCartBTN.click();
				
		//open cart
		pomcart.cartBTN.click();
		
		//save for later
		pomcart.saveLater.click();
		Thread.sleep(2000);
	}
	
	
	//Test to check checkout feature
	@Test(priority=8)
	public void checkout() throws InterruptedException {
		//search item
		ps.searchProduct("phone case");
		ps.searchButton.click();
										
		//select item
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[7]/div/div/div/div/div[2]/span/a/div/img")).click();
		
		//Add item to cart.
		pomcart.addToCartBTN.click();
		
		//click proceed to checkout button
		pomcart.proceedToChekout.click();
		
		//login
		login.email.sendKeys(email);
		login.continueBTN.click();
		login.password.sendKeys(password);
		login.rememberMeCheckbox.click();
		login.rememberMeCheckbox.isSelected();
		login.signInBTN.click();
		
		//choose address
		driver.findElement(By.xpath("//*[@id=\"address-list\"]/div/div[1]/div/fieldset[1]/div[2]/span/div/label/input")).click();
		
		//click ship to address
		driver.findElement(By.xpath("//*[@id=\"shipToThisAddressButton\"]/span/input")).click();
		
		//choose payment method
		driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div[1]/div[1]/div/div[5]/div/div[3]/div/div/div[2]/div/div[2]/div/div/form/div/div[1]/div/div[1]/div[3]/div[2]/div/div/div/div/div[1]/span/div/label/input")).click();
		
		//enter any coupon if you have and select apply
		//driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div[1]/div[1]/div/div[5]/div/div[3]/div/div/div[2]/div/div[2]/div/div/form/div/div[1]/div/fieldset/div/div[2]/div[2]/input")).sendKeys("coupon code");
		//driver.findElement(By.name("ppw-claimCodeApplyPressed")).click();
		
		//click on use payment method
		driver.findElement(By.name("ppw-widgetEvent:SetPaymentPlanSelectContinueEvent")).click();
		
		//click on submit order
		driver.findElement(By.xpath("/html/body/div[5]/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/div[1]/div/span/span/input")).click();
		
		Thread.sleep(5000);
	}
	
	@AfterMethod
	public void closBrowser() {
		driver.close();
	}
}
