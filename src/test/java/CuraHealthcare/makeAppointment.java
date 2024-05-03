package CuraHealthcare;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class makeAppointment {

	public static void main(String[] args) throws InterruptedException {
		
		//Launch Website and maximize window.
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://katalon-demo-cura.herokuapp.com/");
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		
		//Login
		driver.findElement(By.id("btn-make-appointment")).click();
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		Thread.sleep(3000);
		driver.findElement(By.id("btn-login")).click();
		
		
		//[Create Appointment]
		
		//select Facility
		WebElement dropDown = driver.findElement(By.id("combo_facility"));
		dropDown.click();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		Select select = new  Select(dropDown);
		select.selectByVisibleText("Hongkong CURA Healthcare Center");
		
		//Select HealthCare Programme
		WebElement button = driver.findElement(By.id("radio_program_medicaid"));
		button.click();
		Thread.sleep(3000);
		
		//Select Visit Date
		driver.findElement(By.id("txt_visit_date")).click();
		
		while(!driver.findElement(By.className("datepicker-switch")).getText().contains("December")) {
			
			driver.findElement(By.className("next")).click();
			
		}
		
		List<WebElement> days = driver.findElements(By.xpath("//td[@class='active day' or @class='day']"));
		
		for(int i=0;i<days.size();i++) {
			
			if(days.get(i).getText().equals("15")) {
				
				days.get(i).click();
				break;
				
			}	
		}
		
		
		//Giving Comment
		WebElement comment = driver.findElement(By.id("txt_comment"));
		comment.sendKeys("This is Automated System making Appointment for Testing Purpose");
		Thread.sleep(5000);
		driver.findElement(By.id("btn-book-appointment")).click();
		
		//Get Confirmation
		String text = driver.findElement(By.xpath("//div[@class='col-xs-12 text-center']")).getText();
		System.out.println(text);
		/*
		 * String text1 = driver.findElement(By.xpath("//h2']")).getText();
		 * System.out.println(text1); String text2 =
		 * driver.findElement(By.className("lead")).getText();
		 * System.out.println(text2);
		 */
		Thread.sleep(5000);
		
		
		//Logging Out
		driver.findElement(By.id("menu-toggle")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//a[text()='Logout'])")).click();
		
		//Closing Browser
		driver.quit();

	}

}
