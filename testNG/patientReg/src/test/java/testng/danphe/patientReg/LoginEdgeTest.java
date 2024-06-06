package testng.danphe.patientReg;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.timeout.TimeoutException;

import org.testng.Assert;
import java.time.Duration;
import java.util.NoSuchElementException;

public class LoginEdgeTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Starting Test Suite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Test Suite completed");
    }

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.edgedriver().setup();  // Set up EdgeDriver instead of ChromeDriver
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);  // Ensure the complete page is loaded
        driver = new EdgeDriver(options);  // Use EdgeDriver
        driver.get("https://healthapp.yaksha.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));  // Set the explicit wait
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Starting new test");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Test completed");
    }

    @Test (priority = 0)
    public void verifyDanpheEmrHeader() {
  	  try {
		    // Waiting for the "DANPHE EMR" text to be visible on the page using the correct
		    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-md-4 div.left-img h1")));
		    // Perform actions on the element or just check its presence
		    System.out.println("Element is found: " + element.getText());
		} catch (NoSuchElementException e) {
		    System.out.println("Element not found");
		}
   	  try {
          WebElement danpheEMRText = driver.findElement(By.cssSelector("div.col-md-4 div.left-img h1"));
          Assert.assertEquals(danpheEMRText.getText(), "DANPHE EMR", "DANPHE EMR text is not matching");
          System.out.println("DANPHE EMR text is present and correct.");
      } catch (Exception e) {
          Assert.fail("DANPHE EMR text is not present on the page: " + e.getMessage());
      }
    }

    @Test(priority = 1)
    public void userLoginAndCheckDashboard() {
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
//             // Assuming the dashboard has a unique h1 with the text "Danphe EMR" - validate the page load
//             WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Danphe EMR')]")));
//             Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header 'DANPHE EMR' is visible, indicating successful login.");

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
    }
}
