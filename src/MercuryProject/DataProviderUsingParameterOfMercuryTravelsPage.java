package MercuryProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class DataProviderUsingParameterOfMercuryTravelsPage 
{
	WebDriver driver;
	
//	@BeforeSuite
//	public void setUp() 
//	{
//			System.setProperty("webdriver.chrome.driver", "E:\\Selenium All Driver\\chrome\\chromedriver_win32\\chromedriver.exe");
//			driver=new ChromeDriver();				
//	}
	
	
	
	
	@Parameters({"browser"})
	@BeforeSuite
	public void setUp(String browserName) 
	{
		
		if(browserName.equalsIgnoreCase("firebox"))
		{
			System.setProperty("webdriver.geckodriver.driver", "E:\\Selenium All Driver\\firfox\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			driver=new FirefoxDriver();
			
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "E:\\Selenium All Driver\\chrome\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else 
		{
			System.setProperty("webdriver.ie.driver", "E:\\Selenium All Driver\\IEDriver\\IEDriverServer_x64_3.150.1\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
	}
	
	@Parameters({"url"})
	@BeforeTest
	public void enterUrl(String urlName) 
	{
		driver.get(urlName);
	}

	@BeforeClass
	public void managePages() 
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(55, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(55, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void manageCookies() 
	{
		driver.findElement(By.linkText("REGISTER")).click();
		
		Set<Cookie> cook=driver.manage().getCookies();
		int count=cook.size();
		
		for(Cookie c:cook)
		{
			System.out.println(c.getName());
		}
	}

	
	@Parameters({ "firstName", "lastName", "phoneNumber" , "email", "address1", 
		"address2" , "city" , "state" , "postalCode", "cntry", "userName", "password"})
	@Test(priority=1)
	public void f(String firstName, String lastName, String phoneNumber, String email, String address1, 
	String address2,String city, String state, String postalCode,String cntry, String userName, String password) 
	{
		driver.findElement(By.xpath("//input[@name=\"firstName\"]")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name=\"lastName\"]")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name=\"phone\"]")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@id=\"userName\"]")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name=\"address1\"]")).sendKeys(address1);
		driver.findElement(By.xpath("//input[@name=\"address2\"]")).sendKeys(address2);
		driver.findElement(By.xpath("//input[@name=\"city\"]")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name=\"state\"]")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name=\"postalCode\"]")).sendKeys(postalCode);
		Select country=new Select(driver.findElement(By.xpath("//select[@name=\"country\"]")));
		country.selectByValue(cntry);
		driver.findElement(By.xpath("//input[@name=\"email\"]")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name=\"confirmPassword\"]")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name=\"register\"]")).click();
	}

	@Parameters({ "firstName1", "lastName1", "phoneNumber1" , "email1", "address11", 
		"address21" , "city1" , "state1" , "postalCode1", "cntry1", "userName1", "passwordChanges"})
	@Test
	public void f1(String firstName, String lastName, String phoneNumber, String email, String address1, 
	String address2,String city, String state, String postalCode,String cntry, String userName, String passwordChanges) 
	{
		driver.findElement(By.xpath("//input[@name=\"firstName\"]")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name=\"lastName\"]")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name=\"phone\"]")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@id=\"userName\"]")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name=\"address1\"]")).sendKeys(address1);
		driver.findElement(By.xpath("//input[@name=\"address2\"]")).sendKeys(address2);
		driver.findElement(By.xpath("//input[@name=\"city\"]")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name=\"state\"]")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name=\"postalCode\"]")).sendKeys(postalCode);
		Select country=new Select(driver.findElement(By.xpath("//select[@name=\"country\"]")));
		country.selectByValue(cntry);
		driver.findElement(By.xpath("//input[@name=\"email\"]")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(passwordChanges);
		driver.findElement(By.xpath("//input[@name=\"confirmPassword\"]")).sendKeys(passwordChanges);
		driver.findElement(By.xpath("//input[@name=\"register\"]")).click();
	}

	
	@Parameters({ "path"})
	@AfterMethod
	public void getScreenSHot(String path) throws Exception 
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	
		FileUtils.copyFileToDirectory(src, new File(path));
		
	}

	@AfterClass
	public void deleteCookies() 
	{
		driver.manage().deleteAllCookies();
	}

	@AfterTest
	public void closeResources() 
	{
		System.out.println("close Database Connections");
	}

	@AfterSuite
	public void afterSuite() 
	{
		driver.quit();
		System.out.println("Successfully Perform all the Steps");
	}

}
