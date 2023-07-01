package amazon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestProductSearch {
	WebDriver driver;
	POMProductSearch ps;
	
	@BeforeSuite
	public void initialSetup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	}
	
	@BeforeTest
	public void setBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ps = new POMProductSearch(driver);
	}
	
	@BeforeMethod
	public void setURL() {
		driver.get("https://www.amazon.ca/");
	}
	
	//Test to check valid search item.
	@Test(priority=1)
	public void searchValidProductName() throws InterruptedException {
		ps.searchProduct("iPhone 13");
		ps.searchButton.click();
		Thread.sleep(2000);
	}
	
	//Test to check invalid search item.
	@Test(priority=2)
	public void searchInvalidProductName() throws InterruptedException {
		ps.searchProduct("iphane 11");
		ps.searchButton.click();
		Thread.sleep(2000);
	}
	
	//Test to search with partial product name.
	@Test(priority=3)
	public void searchWithPartialProductName() throws InterruptedException {
		ps.searchProduct("ipho");
		ps.searchButton.click();
		Thread.sleep(2000);
	}
	
	//Test to check item based on description.
	@Test(priority=4)
	public void searchProductWithDescription() throws InterruptedException {
		ps.searchProduct("iphone 32gb");
		ps.searchButton.click();
		Thread.sleep(2000);
	}
	
	//Test to check item based on item number.
	@Test(priority=5)
	public void searchProductWithItemNo() throws InterruptedException {
		ps.searchProduct("‎MPXT3LL/A");
		ps.searchButton.click();
		Thread.sleep(2000);
	}
	
	//Test to check result based on partial item number.
	@Test(priority=6)
	public void searchProductWithPartialItemNo() throws InterruptedException {
		ps.searchProduct("‎MPXT3L/A");
		ps.searchButton.click();
		
		Boolean isDisplayed = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[1]/div/div/div/div[1]/span[2]")).isDisplayed();
		System.out.println(isDisplayed);
		
		String actualResult = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[1]")).getText();
		System.out.println(actualResult);
	}
	
	//Test to check relevant products are displayed.
	@Test(priority=7)
	public void checkRelevantProductIsDisplayed() {
		ps.searchProduct("iPhone 13");
		ps.searchButton.click();
		
		//photo is displayed or not
		ps.relevantProducts.isDisplayed();
	}
	
	//Test to check product information is displayed.
	@Test(priority=8)
	public void checkProductInformationIsDisplayed() {
		ps.searchProduct("iPhone 13");
		ps.searchButton.click();
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[8]/div/div/div/div/div[2]/div[1]/h2/a/span")).click();
		
		//Name is displayed or not.
		ps.productName.isDisplayed();
		System.out.println(ps.productName.getText());
		
		//Photo is displayed or not
		ps.productPhoto.isDisplayed();
		
		//Description is displayed or not
		ps.productDescription.isDisplayed();
		System.out.println(ps.productDescription.getText());
		
		//Price is displayed or not
		ps.productPrice.isDisplayed();
		System.out.println(ps.productPrice.getText());
		
		//Customer revies id displayed or not
		ps.custReviews.isDisplayed();
		System.out.println(ps.custReviews.getText());
		
		//Customer revies id displayed or not
		ps.custRatings.isDisplayed();
		System.out.println(ps.custRatings.getText());
	}
	
	//Test to check next page click works.
	@Test(priority=9)
	public void nextPageCheck() throws InterruptedException {
		ps.searchProduct("iPhone 13");
		ps.searchButton.click();
		
		ps.nextPage.click();
		Thread.sleep(2000);
	}
	
	//Test to check sort by drop down.
	@Test(priority=10)
	public void sortByDropDown() {
		ps.searchProduct("iPhone 13");
		ps.searchButton.click();
		
		Select obj= new Select(ps.sortByDropDown);
		obj.selectByIndex(1);
	}
	
	//Test to check filtration.
	@Test(priority=11)
	public void filterProducts() throws InterruptedException {
		ps.searchProduct("phone case");
		ps.searchButton.click();
		
		driver.findElement(By.id("p_72/11192170011")).click();
		
		driver.findElement(By.xpath("//*[@id=\"p_89/Otterbox\"]/span/a/div/label/i")).click();
		driver.findElement(By.xpath("//*[@id=\"p_89/Otterbox\"]/span/a/div/label/i")).isSelected();
		
		driver.findElement(By.xpath("//*[@id=\"p_89/Spigen\"]/span/a/div/label/i")).click();
		driver.findElement(By.xpath("//*[@id=\"p_89/Spigen\"]/span/a/div/label/i")).isSelected();
		
		driver.findElement(By.xpath("//*[@id=\"p_36/12035760011\"]/span/a/span")).click();
		
		driver.findElement(By.xpath("//*[@id=\"p_n_availability/12035748011\"]/span/a/div/label/i")).click();
		driver.findElement(By.xpath("//*[@id=\"p_n_availability/12035748011\"]/span/a/div/label/i")).isSelected();
		
		Thread.sleep(2000);
	}
	
	//Test to check filtation and sorting.
	@Test(priority=12)
	public void filterSortProducts() throws InterruptedException {
		ps.searchProduct("phone case");
		ps.searchButton.click();
		
		driver.findElement(By.id("p_72/11192170011")).click();
		
		driver.findElement(By.xpath("//*[@id=\"p_89/Otterbox\"]/span/a/div/label/i")).click();
		driver.findElement(By.xpath("//*[@id=\"p_89/Otterbox\"]/span/a/div/label/i")).isSelected();
		
		driver.findElement(By.xpath("//*[@id=\"p_89/Spigen\"]/span/a/div/label/i")).click();
		driver.findElement(By.xpath("//*[@id=\"p_89/Spigen\"]/span/a/div/label/i")).isSelected();
		
		driver.findElement(By.xpath("//*[@id=\"p_36/12035760011\"]/span/a/span")).click();
		
		driver.findElement(By.xpath("//*[@id=\"p_n_availability/12035748011\"]/span/a/div/label/i")).click();
		driver.findElement(By.xpath("//*[@id=\"p_n_availability/12035748011\"]/span/a/div/label/i")).isSelected();
		
		Select obj= new Select(ps.sortByDropDown);
		obj.selectByIndex(1);
		
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
