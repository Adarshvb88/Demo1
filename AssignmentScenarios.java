package com.assignmentpkg;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AssignmentScenarios {

	public String url="http://automationpractice.com/index.php";
	public WebDriver driver;

	@BeforeTest
	public void launchBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Chetan Biradar\\Downloads\\chromedriver_win32 (7)\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}

	@Test(priority=2)
	public void scenario1()
	{
		driver.findElement(By.id("search_query_top")).sendKeys("Blouse");
		driver.findElement(By.name("submit_search")).click();

		WebElement element = driver.findElement(By.xpath("//img[@title='Blouse']"));

		boolean result=element.isDisplayed();


		try {
			Assert.assertTrue(result);
			System.out.println("Searched product is displayed");
		} 
		catch (Exception e) {
			System.out.println("Searched Product is not displayed");			
		}
	}

	@Test(priority=3)
	public void scenario2()
	{

		WebElement element = driver.findElement(By.xpath("(//img[@title='Blouse'])[2]"));

		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		js.executeScript("arguments[0].click();", element);

		driver.findElement(By.xpath("//span[text()='Add to cart']")).click();

		WebElement element1 = driver.findElement(By.xpath("//i[@class='icon-ok']"));
		boolean result1=element1.isDisplayed();

		try {
			Assert.assertTrue(result1);
			System.out.println("Product is successfully added to cart");
		} 
		catch (Exception e) {
			System.out.println("Product is not added to cart");			
		}

	}
	
	@Test(priority=1)
	public void scenario4()
	{

		driver.findElement(By.xpath("//a[@class='login']")).click();
		driver.findElement(By.id("email")).sendKeys("adarshvb88@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("password");
		driver.findElement(By.id("SubmitLogin")).click();
		
		String text1=driver.findElement(By.xpath("//li[text()='Authentication failed.']")).getText();
		System.out.println(text1);
		
		if(text1.contains("Authentication failed."))
		{
			System.out.println("Login is failed");
		}

		else
		{
			System.out.println("Login is successfull");
		}

	}
	

	@Test(priority=4)
	public void scenario3()
	{

		WebElement element =driver.findElement(By.xpath("(//img[@title='Blouse'])[2]"));

		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

		WebElement element2 =driver.findElement(By.xpath("(//span[text()='More'])[2]")); 
		Actions a=new Actions(driver); 
		a.moveToElement(element).perform();

		if (driver.findElement(By.xpath(".//*[@id='product_reference']")).getText().isEmpty())
		{
			System.out.println("product reference is empty");
		}
		if(driver.findElement(By.xpath(".//*[@id='product_condition']")).getText().isEmpty()){
			System.out.println("product condition is empty");
		}
		if(driver.findElement(By.xpath(".//*[@id='short_description_content']/p")).getText().isEmpty()){
			System.out.println("short description is empty"); 
		}
		String count1=driver.findElement(By.xpath(".//*[@id='quantity_wanted']")).getAttribute("value");
		driver.findElement(By.xpath(".//*[@id='quantity_wanted_p']/a[2]/span/i")).click();
		String count2=driver.findElement(By.xpath(".//*[@id='quantity_wanted']")).getAttribute("value");
		int c1=Integer.parseInt(count1);
		int c2=Integer.parseInt(count2);
		if(c2-c1!=1) {
			System.out.println("the product is not incremented ");
		}
		driver.findElement(By.xpath(".//*[@id='quantity_wanted_p']/a[1]/span/i")).click();
		Select s = new Select(driver.findElement(By.xpath(".//*[@id='group_1']")));
		s.selectByVisibleText("M");

		driver.findElement(By.xpath(".//*[@id='add_to_cart']/button")).click();
		String text= driver.findElement(By.xpath(".//*[@id='layer_cart_product_attributes']")).getText();
		System.out.println(text);
		if(text.contains("M")==false) {
			System.out.println("size is not selected");
		}
	}

	@AfterTest
	public void end()
	{
		driver.close();
	}




}
