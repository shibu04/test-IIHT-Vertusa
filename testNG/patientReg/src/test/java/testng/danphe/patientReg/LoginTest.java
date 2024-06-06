package testng.danphe.patientReg;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.Alert;


public class LoginTest {
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
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://healthapp.yaksha.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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

    @Test(priority = 0)
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
//         // Assuming the dashboard has a unique h1 with the text "Danphe EMR" - validate the page load
//         WebElement dashboardHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Danphe EMR')]")));
//         Assert.assertTrue(dashboardHeader.isDisplayed(), "Dashboard header 'DANPHE EMR' is visible, indicating successful login.");

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

    @Test(priority = 2)
public void verifyPatientMenuAndNavigateToSearch() {
    try {
        // Assuming login and navigation to the dashboard are successful
        System.out.println("Navigating to Dashboard...");

        // Assert and click on the "Patient" menu item using absolute XPath
        try {
            WebElement patientMenuItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/my-app[1]/div[1]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[7]/a[1]")));
            Assert.assertTrue(patientMenuItem.isDisplayed(), "Patient menu item is not visible on the dashboard.");
            System.out.println("Patient menu item is visible.");

            // Click on the "Patient" menu item to navigate to the search page
            patientMenuItem.click();
            System.out.println("Clicked on Patient menu item.");
        } catch (Exception e) {
            System.out.println("Failed to find or interact with the Patient menu item: " + e.getMessage());
            throw e; // Ensuring TestNG marks this as failed
        }

        // Verify the URL to ensure that the "Patient Search" page is loaded
        try {
            String expectedUrl = "https://healthapp.yaksha.com/Home/Index#/Patient/SearchPatient";
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL does not match the expected Patient search page URL.");

            System.out.println("Patient search page is displayed correctly.");
            System.out.println("Current URL (Patient Search): " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Failed to navigate to Patient search page: " + e.getMessage());
            throw e; // Ensuring TestNG marks this as failed
        }
    } catch (Exception e) {
        System.out.println("Error during test execution: " + e.getMessage());
        throw e; // Re-throwing the exception to make sure TestNG marks this test as failed.
    }
}
    
    @Test(priority = 3)
    public void navigateToPatientRegistration() {
        try {
            // Using an absolute XPath to locate the "Register Patient" link and click on it
            WebElement registerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html[1]/body[1]/my-app[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/ng-component[1]/div[1]/ul[1]/li[3]/a[1]")
            ));
            registerLink.click();
            
            // Print out for debugging purposes
            System.out.println("Clicked on the Register Patient link via XPath.");

            // Wait until the expected URL is displayed to ensure navigation was successful
            String expectedUrl = "https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/BasicInfo";
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Navigation to the Patient Registration page failed.");

            System.out.println("Successfully navigated to the Patient Registration page.");
        } catch (Exception e) {
            System.out.println("Error during navigation to Patient Registration: " + e.getMessage());
            throw e; // Ensure that any error is propagated upwards so that TestNG can handle it accordingly
        }
    }
    
    @Test(priority = 3)
    public void verifyMandatoryFields() {
        try {
            // Navigate to the registration page if not already there
            driver.get("https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/BasicInfo");

            // Click on the 'Register Patient' button to trigger validation
            WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("regPatientSubmitBtn")));
            registerButton.click();

            // Check for error messages using the correct paths and ids
            Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'First Name is required')]")).isDisplayed(), "Error message for First Name is missing.");
            Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Last Name is required')]")).isDisplayed(), "Error message for Last Name is missing.");
            Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Primary Phone is required')]")).isDisplayed(), "Error message for Phone Number is missing.");
            Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Gender is required')]")).isDisplayed(), "Error message for Gender is missing.");

            System.out.println("Mandatory fields validation errors displayed correctly.");
        } catch (Exception e) {
            System.out.println("Error validating mandatory fields: " + e.getMessage());
            throw e;
        }
    }

    
//    @Test(enabled=false)
//    public void testSalutationAffectsGenderSelection() {
//        // Wait for 'Mr' to be clickable and then click it
//        WebElement mrRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='salutation'][@value='Mr.']")));
//        try {
//            mrRadioButton.click();
//        } catch (ElementClickInterceptedException e) {
//            // If normal click fails, try clicking via JavaScript
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mrRadioButton);
//        }
//
//        // Verify Gender is set to 'Male'
//        WebElement genderDropdown = driver.findElement(By.id("Gender"));
//        String selectedGenderForMr = genderDropdown.getAttribute("value");
//        Assert.assertEquals(selectedGenderForMr, "Male", "Gender should be set to Male when Mr is selected");
//
//        // Repeat steps for 'Mrs' and 'Ms' with appropriate checks
//        handleSalutationAndVerifyGender("Mrs", "Female");
//        handleSalutationAndVerifyGender("Ms", "Female");
//    }
//
//    private void handleSalutationAndVerifyGender(String salutation, String expectedGender) {
//        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='salutation'][@value='" + salutation + "']")));
//        try {
//            radioButton.click();
//        } catch (ElementClickInterceptedException e) {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);
//        }
//
//        WebElement genderDropdown = driver.findElement(By.id("Gender"));
//        String selectedGender = genderDropdown.getAttribute("value");
//        Assert.assertEquals(selectedGender, expectedGender, "Gender should be set to " + expectedGender + " when " + salutation + " is selected");
//    }
    
    
    
//    @Test(priority = 4)
//    public void verifyErrorPopupOnRegistration() {
//        try {
////            // Navigate to the registration page if not already there
////            driver.get("https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/BasicInfo");
////
////            // Click the 'Register Patient' button to trigger validation
////            WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("regPatientSubmitBtn")));
////            registerButton.click();
////            System.out.println("Error validating mandatory fields: " + e.getMessage());
//            // Use a more refined XPath to accurately target the error message within visible pop-ups
//            // String xpathExpression = "//div[contains(@class,'danphe-msgbox')]//p[contains(text(), 'One or more fields are invalid, please correct them and submit again')]";
//            String xpathExpression = "/html[1]/body[1]/my-app[1]/div[1]/div[1]/div[3]/div[2]/danphe-msgbox[1]/div[1]/div[1]/div[2]/p[2], 'One or more fields are invalid, please correct them and submit again')]";
//            
//            System.out.println("XPATH of msg " + xpathExpression);
//            WebElement errorPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
//           // Assert.assertTrue(errorPopup.isDisplayed(), "Error popup did not appear.");
//
////            // Check for the position of the popup to ensure it's on the right side bottom
////            WebElement popupContainer = errorPopup.findElement(By.xpath("ancestor::div[contains(@class, 'danphe-msgbox')]"));
////            String positionRight = popupContainer.getCssValue("right");
////            String positionBottom = popupContainer.getCssValue("bottom");
////            Assert.assertEquals(positionRight, "0px", "Popup is not at the right side of the screen.");
////            Assert.assertEquals(positionBottom, "0px", "Popup is not at the bottom of the screen.");
//
//            // Enhanced stability: Replace Thread.sleep with a more robust wait strategy
//            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(errorPopup));
//
//            // Validate again to ensure the popup remains visible
//            Assert.assertTrue(errorPopup.isDisplayed(), "Error popup did not remain visible for 10 seconds.");
//
//            System.out.println("Error popup displayed as expected and remained for 10 seconds.");
//        } catch (Exception e) {
//            System.out.println("Error in verifying the error popup: " + e.getMessage());
//            throw e; // Rethrow the exception to mark the test as failed
//        }
//    }

//    @Test(priority = 5)
//    public void testSalutationAffectsGenderSelection() {
//        driver.get("https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/BasicInfo");
//
//        // Select 'Mr' and verify Gender is set to 'Male'
//        selectSalutationAndVerifyGender("Mr.", "Male");
//
//        // Select 'Mrs' and verify Gender is set to 'Female'
//        selectSalutationAndVerifyGender("Mrs.", "Female");
//
//        // Select 'Ms' and verify Gender is set to 'Female'
//        selectSalutationAndVerifyGender("Ms.", "Female");
//    }
//
//    private void selectSalutationAndVerifyGender(String salutationValue, String expectedGender) {
//        // Click the salutation radio button
//        WebElement salutationRadioButton = driver.findElement(By.xpath("//input[@name='salutation'][@value='" + salutationValue + "']"));
//        salutationRadioButton.click();
//
//        // Wait for gender dropdown to reflect the change
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(d -> d.findElement(By.id("Gender")).getAttribute("value").equals(expectedGender));
//
//        // Get the selected gender from the dropdown and verify
//        WebElement genderDropdown = driver.findElement(By.id("Gender"));
//        String selectedGender = genderDropdown.getAttribute("value");
//        Assert.assertEquals(selectedGender, expectedGender, "Gender should be set to " + expectedGender + " when " + salutationValue + " is selected");
//    }
    
    @Test(priority = 4)
    public void navigateToInsurancePage() {
        try {
            // Navigate to Basic Information page if not already there
            driver.get("https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/BasicInfo");

            // Click on the "Insurance" tab using the navigation menu
            WebElement insuranceTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body/my-app[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/ng-component[1]/ng-component[1]/div[1]/ul[1]/li[4]/a[1]")));
            insuranceTab.click();

            // Verify the URL to ensure that the "Insurance" page is loaded
            String expectedInsuranceUrl = "https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/Insurance";
            wait.until(ExpectedConditions.urlToBe(expectedInsuranceUrl));
            Assert.assertEquals(driver.getCurrentUrl(), expectedInsuranceUrl, "Failed to navigate to the Insurance page.");

            // Assert the "Add Insurance" button is displayed
            WebElement addInsuranceButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/my-app[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/ng-component[1]/ng-component[1]/ng-component[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/input[1]")));
            Assert.assertTrue(addInsuranceButton.isDisplayed(), "Add Insurance button is not displayed on the Insurance page.");

            System.out.println("Successfully navigated to the Insurance page and verified the Add Insurance button.");
        } catch (Exception e) {
            System.out.println("Error during navigation to the Insurance page: " + e.getMessage());
            throw e; // Re-throwing the exception to make sure TestNG marks this test as failed.
        }
    }
    
    
    @Test(priority = 5)
    public void verifyMandatoryInsuranceFields() {
        try {
            // Navigate to the Insurance page
            driver.get("https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/Insurance");

            // Click on the 'Add Insurance' button to trigger validation
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Add Insurance']")));
            addButton.click();

            // Check for error messages
            assertErrorMessage("//span[contains(text(),'Card Number is required')]", "Error message for Card Number is missing.");
            assertErrorMessage("//span[contains(text(),'Insurance Number is required')]", "Error message for Insurance Number is missing.");
            assertErrorMessage("//span[contains(text(),'Facility Code is required')]", "Error message for Facility Code is missing.");

            System.out.println("Mandatory fields validation errors displayed correctly.");
        } catch (Exception e) {
            System.out.println("Error validating mandatory fields on Insurance page: " + e.getMessage());
            throw e; // Rethrow the exception to mark the test as failed
        }
    }

    private void assertErrorMessage(String xpath, String errorMessage) {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Assert.assertTrue(errorElement.isDisplayed(), errorMessage);
    }
    
    
    @Test(priority = 6)
    public void navigateToAddressPageAndValidate() {
        try {
            // Assuming that the test starts on the Insurance page
          //  driver.get("https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/Insurance");

        	
            // Click on the "Address" tab in the navigation menu
            WebElement addressTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body/my-app[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/ng-component[1]/ng-component[1]/div[1]/ul[1]/li[2]/a[1]")));
            addressTab.click();

            try {
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                System.out.println("Alert detected: " + alert.getText());  // Optional: log alert text
                alert.accept();  // Click 'OK' to accept and close the alert
                System.out.println("Alert accepted.");
            } catch (Exception e) {
                System.out.println("No alert present or error handling alert: " + e.getMessage());
            }
            
            // Verify the URL to ensure that the "Address" page is loaded
            String expectedAddressUrl = "https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/Address";
            wait.until(ExpectedConditions.urlToBe(expectedAddressUrl));
            Assert.assertEquals(driver.getCurrentUrl(), expectedAddressUrl, "Failed to navigate to the Address page.");

            // Verify that specific elements on the "Address" page are visible
            // Example: Check if the 'Add Address' button is visible
            WebElement addAddressButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/my-app[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/ng-component[1]/ng-component[1]/ng-component[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/input[1]")));
            Assert.assertTrue(addAddressButton.isDisplayed(), "Add Address button is not displayed on the Address page.");

            System.out.println("Successfully navigated to the Address page and verified elements.");
        } catch (Exception e) {
            System.out.println("Error during navigation to the Address page: " + e.getMessage());
            throw e; // Ensuring that the test fails in case of any error
        }
    }
    
    @Test(priority = 7)
    public void verifyMandatoryAddressFields() {
        try {
            // Navigate to the Address page
           // driver.get("https://healthapp.yaksha.com/Home/Index#/Patient/RegisterPatient/Address");

            // Click on the 'Add Address' button to trigger validation
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name'][@value='Add Address']")));
            addButton.click();

            // Check for error messages
            assertErrorMessage("//span[contains(text(),'Street1 is required')]", "Error message for Street1 is missing.");
            assertErrorMessage("//span[contains(text(),'City is required')]", "Error message for City is missing.");

            System.out.println("Mandatory fields validation errors displayed correctly.");
        } catch (Exception e) {
            System.out.println("Error validating mandatory fields on Address page: " + e.getMessage());
            throw e; // Rethrow the exception to mark the test as failed
        }
    }

    private void assertErrorMessage(String xpath, String errorMessage) {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Assert.assertTrue(errorElement.isDisplayed(), errorMessage);
    }
    
    
}



