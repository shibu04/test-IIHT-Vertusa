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
import org.testng.Assert;
import java.time.Duration;

public class LoginEdgeTest2 {
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
        driver.get("http://localhost:5000/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Set the explicit wait
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

    @Test
    public void verifyDanpheEmrHeader() {
        try {
            // Wait for the text to be present in the element
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1[contains(text(), 'Danphe Emr')]"), "Danphe EMR"));
            WebElement header = driver.findElement(By.xpath("//h1[contains(text(), 'Danphe Emr')]"));
            Assert.assertTrue(header.isDisplayed(), "Header 'Danphe EMR' is visible.");
        } catch (Exception e) {
            System.out.println("Current Body Text: " + driver.findElement(By.tagName("body")).getText());
            throw e;
        }
    }

    @Test
    public void userLoginAndCheckDashboard() {
        try {
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username_id")));
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            username.sendKeys("admin");
            password.sendKeys("pass123");

            WebElement loginButton = driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"));
            loginButton.click();

            // Wait and check for the presence of the header to confirm dashboard load
            WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Danphe Emr')]")));
            Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header 'DANPHE EMR' is visible, indicating successful login.");

            System.out.println("Current URL (Post-Login): " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Error during test execution: " + e.getMessage());
            throw e; // Re-throwing the exception to make sure TestNG marks this test as failed.
        }
    }
}
