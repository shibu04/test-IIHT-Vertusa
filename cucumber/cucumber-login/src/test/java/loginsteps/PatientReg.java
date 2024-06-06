package loginsteps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

public class PatientReg {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("the user logs into the healthcare portal")
    public void the_user_logs_into_the_healthcare_portal() {
        driver.get("https://healthapp.yaksha.com/");
        driver.findElement(By.id("Username_id")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("pass123");
        driver.findElement(By.id("login")).click();
        wait.until(ExpectedConditions.urlToBe("https://healthapp.yaksha.com/Home/Index#/"));
        System.out.println("Login successful and navigated to dashboard.");
    }
    
    
    @Given("the user is on the dashboard")
    public void the_user_is_on_the_dashboard() {
    
    	
    	String currentUrl = driver.getCurrentUrl();
    	
        assertTrue(driver.getCurrentUrl().equals("https://healthapp.yaksha.com/Home/Index#/"));
        System.out.println("The current URL after login " + currentUrl);
        System.out.println("Verified user is on the dashboard.");
       
        
        // Wait for the navigation to the dashboard
        wait.until(ExpectedConditions.urlToBe("https://healthapp.yaksha.com/Home/Index#/"));
        // Verify "Registered Patient" text is displayed
       
        WebElement registeredPatientText = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//p[contains(text(), 'Registered Patient')]")));
        assertTrue("Registered Patient text is not visible", registeredPatientText.isDisplayed());

        
       System.out.println("User is logged in and on the dashboard.");
    }
    


    @When("the user clicks on Patient in the left side menu")
    public void the_user_clicks_on_patient_in_the_left_side_menu() {
        try {
        	WebElement patientMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/my-app[1]/div[1]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[7]/a[1]")));
            patientMenu.click();
            System.out.println("Clicked on Patient menu item.");
           } catch (Exception e) {
            System.out.println("Failed to find or interact with the Patient menu item: " + e.getMessage());
            throw new RuntimeException("Failed to find or interact with the Patient menu item", e);
        }
    }

    @Then("the Patient Registration page is displayed")
    public void the_patient_registration_page_is_displayed() {
        // Check if the patient registration page is visible
        try {
            String expectedUrl = "https://healthapp.yaksha.com/Home/Index#/Patient/SearchPatient";
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            assertTrue(driver.getCurrentUrl().equals("https://healthapp.yaksha.com/Home/Index#/Patient/SearchPatient"));
            System.out.println("Patient Registration page is correctly displayed. " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Failed to navigate to Patient search page: " + e.getMessage());
            throw new RuntimeException("Failed to navigate to Patient search page", e);
        }
    }
    
    @And("the user navigates to the Register Patient page")
    public void the_user_navigates_to_the_register_patient_page() {
        WebElement registerPatientButton = driver.findElement(By.xpath("//body/my-app[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/ng-component[1]/div[1]/ul[1]/li[3]/a[1]"));
        registerPatientButton.click();
        wait.until(ExpectedConditions.urlToBe("https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/BasicInfo"));
        assertTrue("Not on the Register Patient page.", driver.getCurrentUrl().contains("/Patient/RegisterPatient/BasicInfo"));
        System.out.println("Navigated to Register Patient page.");
    }

    @When("the user attempts to submit the registration form with all mandatory fields empty")
    public void the_user_attempts_to_submit_the_registration_form_with_all_mandatory_fields_empty() {
    	WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("regPatientSubmitBtn")));
    	    submitButton.click();
    	    System.out.println("Registration form submitted with all mandatory fields empty.");
    }
    @Then("the {string} field should display the error message {string}")
    public void the_field_should_display_the_error_message(String fieldName, String errorMessage) {
    	String xpathExpression = "//span[contains(text(),'" + errorMessage + "')]";
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));

        // Assert that the error message is displayed
        Assert.assertTrue(fieldName + " error message is not displayed as expected.", errorElement.isDisplayed());
        System.out.println(fieldName + " displays the correct error message: " + errorMessage);
    }
    
    @Given("the user is on the Basic Info tab")
    public void the_user_is_on_the_basic_info_tab() {
    	String currentUrl = driver.getCurrentUrl();
    	
        assertTrue(driver.getCurrentUrl().equals("https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/BasicInfo"));
        System.out.println("The current URL after login " + currentUrl);
        System.out.println("Verified user is on the Basic Info Page.");
       
        
        // Wait for the navigation to the dashboard
        wait.until(ExpectedConditions.urlToBe("https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/BasicInfo"));
        // Verify "Registered Patient" text is displayed
       
//        WebElement registeredPatientText = wait.until(ExpectedConditions.visibilityOfElementLocated(
//            By.xpath("//p[contains(text(), 'Registered Patient')]")));
//        assertTrue("Registered Patient text is not visible", registeredPatientText.isDisplayed());

        
       System.out.println("User to click on Insurance.");
    }
    
    
    @When("the user clicks on the Insurance tab")
    public void the_user_clicks_on_the_insurance_tab() {
    	
    	String currentUrl = driver.getCurrentUrl();
    	
        // assertTrue(driver.getCurrentUrl().equals("https://healthapp.yaksha.com/Home/Index#/"));
        System.out.println("The current URL inside the user clicks on the Insurance tab WHEN mathord  " + currentUrl);
        
        WebElement insuranceTab = driver.findElement(By.linkText("Insurance"));
        insuranceTab.click();
        // Alternatively, using XPath with a class and text
        // WebElement insuranceTab = driver.findElement(By.xpath("//a[@class='a-tab-active'][contains(text(),'Insurance')]"));
        // insuranceTab.click();

        System.out.println("Clicked on the Insurance tab");
        System.out.println("Control is in @WHEN  to click user insurance tab the user clicks on the Insurance tab ");
    }
    
    
    @Then("the user should be redirected to the Insurance page")
    public void the_user_should_be_redirected_to_the_insurance_page() {
        wait.until(ExpectedConditions.urlToBe("https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/Insurance"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/Insurance");
        System.out.println("Verified the should be redirected to the Insurance page");
    }
    
    
    
//    @Then("the user is redirected to the Insurance page")
//    public void the_user_is_redirected_to_the_insurance_page() {
//
//
//      //  WebElement insuranceProviderLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='control-label col-md-4'  and contains(text(), 'Insurance Provider')]")));
//        // WebElement insuranceProviderLabel = driver.findElement(By.xpath("//label[@class='control-label col-md-4' and contains(text(), 'Insurance Provider')]"));
//
////        Assert.assertEquals(insuranceProviderLabel.isDisplayed(), "Insurance Provider label is not visible");
//  //      System.out.println("Verified 'Insurance Provider' label is visible on the page");
//    }
    @When("the user attempts to submit the insurance form with all mandatory fields empty")
    public void the_user_attempts_to_submit_the_insurance_form_with_all_mandatory_fields_empty() {

    	WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Add Insurance']")));
        addButton.click();
        
        System.out.println("Printed from WHEN : the user attempts to submit the insurance form with all mandatory fields empty");
    }
    
    @Then("the {string} insurance field should display the error message {string}")
    public void the_insurance_field_should_display_the_error_message(String fieldName, String errorMessage) {
        // Construct an XPath that locates the error message specifically for the field in question
        String fieldXPath = getFieldXPath(fieldName);
        assertErrorMessage(fieldXPath, "Error message for " + fieldName + " is missing.");
    }

    private void assertErrorMessage(String xpathExpression, String failureMessage) {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
            Assert.assertTrue(failureMessage, errorElement.isDisplayed());
            System.out.println(failureMessage.replace(" is missing.", "") + " displayed correctly.");
        } catch (Exception e) {
            System.out.println(failureMessage + ": " + e.getMessage());
            throw e; // Rethrow the exception to mark the test as failed
        }
    }

    private String getFieldXPath(String fieldName) {
        // Customize this method based on the actual layout of your HTML and field names
        return "//label[contains(.,'" + fieldName + "')]/../following-sibling::div//span[contains(text(),'" + fieldName + " is required')]";
    }
    
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}