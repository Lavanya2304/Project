package empdetails.com;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class NewTest {
  WebDriver driver;
  
  private String username,password,firstName,middleName,lastName,uName,pw,Cpw;
  String id;
  
  @BeforeClass
  @Parameters({"browser","url"})
  
	void setup(String br,String appurl) throws InterruptedException
	{
		if(br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(br.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(appurl);
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
  @Test(priority=1)
  void read() {
	//Reading Data From Excel
	  ExcelFromExcell obj=new ExcelFromExcell();
	  obj.readexcel();
	   username=obj.LoginId;
	   password=obj.password;
	  //Thread.sleep(9000);
	   firstName=obj.Fname;
	   middleName=obj.Mname;
	   lastName=obj.Lname;
	   uName=obj.Uname;
	   pw=obj.pw;
	   Cpw=obj.Cpw;
  }
  @Test(priority=2)
  void login() {
	//username and password
	  driver.findElement(By.name("username")).sendKeys(username);
	  driver.findElement(By.name("password")).sendKeys(password);
	  driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();  
	  }
  @Test(priority=3)
  void tab() {
	  // selecting pim
	  driver.findElement(By.xpath("//span[normalize-space()='PIM']")).click();

	  // clicking on add employee page
	  driver.findElement(By.xpath("//a[normalize-space()='Add Employee']")).click();
  }
  @Test(priority=4)
  void fill() {
	//storing employee id  
	  id = driver.findElement(By.xpath("//div[@class = 'orangehrm-employee-form']/div[1]/div[2]//div[2]/input")).getAttribute("value");
	  System.out.println("Employee ID : "+id);

      try {
	  // first name
	  driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstName);
	 
	  // middle name
	  driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).sendKeys(middleName);
	 
	  // last name
	  driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);
	  Thread.sleep(5000);
	 
	  // clicking on create login details slider
	  driver.findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")).click();
	 
	  // enter username
	  driver.findElement(By.xpath("//*[@id=\'app\']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[3]/div/div[1]/div/div[2]/input")).sendKeys(uName);
	
	  // enter password
	  driver.findElement(By.xpath("//*[@id=\'app\']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[1]/div/div[2]/input")).sendKeys(pw);
	 
	  // enter confirm password
	  driver.findElement(By.xpath("//*[@id=\'app\']/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[4]/div/div[2]/div/div[2]/input")).sendKeys(Cpw);
	  Thread.sleep(5000);
	
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
   		
  }
  @Test(priority=5)
  void save() throws InterruptedException {
	// saving details
		  WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
		  wait1.until(ExpectedConditions.elementToBeClickable(
		  By.xpath("//p[@class = 'oxd-text oxd-text--p orangehrm-form-hint']/following-sibling::button[2]")));
		  driver.findElement(By.xpath("//p[@class = 'oxd-text oxd-text--p orangehrm-form-hint']/following-sibling::button[2]")).click();
		  Thread.sleep(5000);  
  }
  @Test(priority=6)
  void tabswitch() throws InterruptedException {
	
	// go to the employee list
	driver.findElement(By.xpath("//*[@id=\'app\']/div[1]/div[1]/header/div[2]/nav/ul/li[2]")).click();

	//Sending id to find Employee details
	driver.findElement(By.xpath("//div[@class = 'oxd-form-row']/div/div[2]/div/div[2]/input")).sendKeys(id);
	Thread.sleep(5000); 
	//Clicking on search button
	WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
	wait2.until(ExpectedConditions
	.elementToBeClickable(By.xpath("//button[@type = 'reset']/following-sibling::button")));
	driver.findElement(By.xpath("//button[@type = 'reset']/following-sibling::button")).click();
	Thread.sleep(5000); 
	//Clicking on edit button
	WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(30));
	wait4.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class = 'oxd-table-cell-actions']/button[2]")));
	driver.findElement(By.xpath("//div[@class = 'oxd-table-cell-actions']/button[2]")).click();
	
	 
  }
  @Test(priority=7)
	  void editdetails() throws InterruptedException {
	   
		//selecting nationality as indian
		driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[1]/div/div[2]/div/div/div[1]")).click();
		List<WebElement> countryOptions = driver.findElements(By.xpath("//div[@class=\"oxd-select-dropdown --positon-bottom\"]/div"));
		for (WebElement option : countryOptions) {
			try {
				String country = option.getText();
				if (country.equals("Indian")) {
					option.click();
					break;
				}
			}catch(org.openqa.selenium.StaleElementReferenceException e) {
				
			}
		}
		Thread.sleep(5000);
		//Selecting marital status
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[1]/div[2]/div/div[2]/div/div/div[2]")).click();
		List<WebElement> maritalOptions = driver.findElements(By.xpath("//div[@class='oxd-select-dropdown --positon-bottom']/div"));
		for (WebElement opt : maritalOptions) {
			try {
				String status = opt.getText();
				if (status.equals("Single")) {
					opt.click();
					break;
				}
			}catch(org.openqa.selenium.StaleElementReferenceException e) {
			
			}
		}
        Thread.sleep(5000);
		
		//Selecting Gender
		
		driver.findElement(By.xpath("//label[normalize-space()='Female']")).click();

     
		//Saving details
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
  }
	  	
  @Test(priority=8)
  void logout() throws InterruptedException {

	//Logout from the application
	
	driver.findElement(By.xpath("//img[@class='oxd-userdropdown-img']")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[2]/ul/li/ul/li[4]/a")).click();
	


	//Closing the browser
	driver.quit();
  }
}
