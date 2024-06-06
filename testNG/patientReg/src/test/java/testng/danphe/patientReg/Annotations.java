package testng.danphe.patientReg;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.timeout.TimeoutException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Annotations {
	
    private WebDriver driver;
    private WebDriverWait wait;
    
  @Test
  public void f1() {
	  
//	  System.out.println("this is my precondition test case 1");
//	  
//	  //https://www.selenium.dev/
//	//span[normalize-space()='Downloads'] 
//	  driver.findElement(By.xpath("span[normalize-space()='Downloads']")).click();
//	  String title = driver.getTitle();
//	  System.out.println("my current page is : " + title);
	  
	  
	  System.out.println("This is my precondition test case 1");

	  
	  try {
		    // Waiting for the "Sign in" text to be visible on the page using the correct CSS selector for the <small> tag with class "balck"
		    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("small.balck")));
		    // Perform actions on the element or just check its presence
		    System.out.println("Element is found: " + element.getText());
		} catch (NoSuchElementException e) {
		    System.out.println("Element not found");
		}
	  
	  try {
		    // Waiting for the "DANPHE EMR" text to be visible on the page using the correct
		    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-md-4 div.left-img h1")));
		    // Perform actions on the element or just check its presence
		    System.out.println("Element is found: " + element.getText());
		} catch (NoSuchElementException e) {
		    System.out.println("Element not found");
		}
	  


//      try {
//          WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.d-1")));
//          // Perform actions on the element
//          System.out.println("Element is found: " + element.getText());
//      } catch (NoSuchElementException e) {
//          System.out.println("Element not found");
//      }
      
	    // Locating the element using the provided CSS selector
	  //  driver.findElement(By.cssSelector("body.td-section:nth-child(8) div.container-fluid.td-default.td-outer:nth-child(2) main.td-main:nth-child(1) div.td-content:nth-child(2) section.row.td-box.td-box--gradient.-bg-selenium-cyan.p-2 div.col div.row div.mx-auto.text-center.p-4 > h1.d-1:nth-child(1)")).click();
	    
	    // Getting the title of the current page
	    String title = driver.getTitle();
	    System.out.println("My current page is: " + title);
	  
  }
  
  @Test
  public void f2() {
      try {
          WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username_id")));
          WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
          username.sendKeys("admin");
          password.sendKeys("pass123");
          
    	  try {
              WebElement danpheEMRText = driver.findElement(By.cssSelector("div.col-md-4 div.left-img h1"));
              Assert.assertEquals(danpheEMRText.getText(), "DANPHE EMR", "DANPHE EMR text is not matching");
              System.out.println("DANPHE EMR text is present and correct.");
          } catch (Exception e) {
              Assert.fail("DANPHE EMR text is not present on the page: " + e.getMessage());
          }

          // Corrected the locator for the login button using the ID provided
          WebElement loginButton = driver.findElement(By.id("login"));
          loginButton.click();
//
//          // Assuming the dashboard has a unique h1 with the text "Danphe EMR" - validate the page load
//          WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Danphe EMR')]")));
//          Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header 'DANPHE EMR' is visible, indicating successful login.");

          try {
              // Wait and check for the presence of the "Registered Patient" text on the dashboard
              WebElement registeredPatient = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[normalize-space()='Registered Patient']")));
              Assert.assertTrue(registeredPatient.isDisplayed(), "Registered Patient panel is not visible, indicating issue with the dashboard or login.");

              System.out.println("Registered Patient panel is visible, confirming successful login.");
          } catch (Exception e) {
              System.out.println("Error during test execution: " + e.getMessage());
              throw e; // Re-throwing the exception to make sure TestNG marks this test as failed if there is an error.
          }
          
          System.out.println("Current URL (Post-Login): " + driver.getCurrentUrl());
      } catch (Exception e) {
          System.out.println("Error during test execution: " + e.getMessage());
          throw e; // Re-throwing the exception to make sure TestNG marks this test as failed.
      }
      System.out.println("this is my precondition test case 2");
  }

 
 
  @BeforeMethod
  public void beforeMethod() {
	  
	  System.out.println("this is before method test area");
  }

  @AfterMethod
  public void afterMethod() {
  
	  System.out.println("this is after methord test area");
  }
  
  
  


  @BeforeClass
  public void beforeClass() {
	  
      WebDriverManager.edgedriver().setup();  // Set up EdgeDriver instead of ChromeDriver
      EdgeOptions options = new EdgeOptions();
      options.addArguments("--remote-allow-origins=*");
      options.setPageLoadStrategy(PageLoadStrategy.NORMAL);  // Ensure the complete page is loaded
      driver = new EdgeDriver(options);  // Use EdgeDriver
     // driver.get("https://www.selenium.dev/");
      driver.get("https://healthapp.yaksha.com/");
      wait = new WebDriverWait(driver, Duration.ofSeconds(60));  // Set the explicit wait
     //driver.get("https://healthapp.yaksha.com/");
	  System.out.println("this is my before class test area");
  }

  @AfterClass
  public void afterClass() {
      if (driver != null) {
          driver.quit();
      }
	  System.out.println("this is my after class test area");
  }
  
  

  @BeforeTest
  public void beforeTest() {
	  
	  System.out.println("this is my before test area");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("this is my after test area");
  }

  @BeforeSuite
  public void beforeSuite() {
	  
	  System.out.println("this is my before suite area");
  }

  @AfterSuite
  public void afterSuite() {
	  
	  System.out.println("this is my after suite test area");
  }

}
