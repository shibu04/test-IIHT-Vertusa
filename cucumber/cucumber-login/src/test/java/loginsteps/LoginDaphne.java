package loginsteps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
import java.time.Duration;

public class LoginDaphne {

    private WebDriver driver;
    private WebDriverWait wait; // Correctly initialized WebDriverWait

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // wait = new WebDriverWait(driver, 10); // Initialize WebDriverWait with a 10-second timeout
    }

    @Given("the user has opened the browser")
    public void the_user_has_opened_the_browser() {
        // This method is now correctly handled by the setup method
    	System.out.println("the_user_has_opened_the_browser");
    }

    @Given("the user is on the sign in page {string}")
    public void the_user_is_on_the_sign_in_page(String url) {
    	System.out.println("Navigating to URL: " + url);
        driver.get(url);
    }

    @When("the user clicks on the username button")
    public void the_user_clicks_on_the_username_button() {
        // Wait for the username button to be clickable before clicking
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login"))).click();
    	System.out.println("the user clicks on the username button tc-1 When block");
    }

    @Then("the username button should be clickable")
    public void the_username_button_should_be_clickable() {
        // Confirm that the button is still present and clickable after the initial click
        boolean isClickable = ExpectedConditions.elementToBeClickable(By.id("login")).apply(driver) != null;
        assertTrue("The username (login) button should be clickable", isClickable);
        System.out.println("the user clicks on the username button tc-1 Then block");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void loginToApplication(WebDriver driver, String username, String password) {
        driver.findElement(By.id("Username")).sendKeys("admin"); // Replace "username" with the actual field ID
        driver.findElement(By.id("password")).sendKeys("pass123"); // Replace "password" with the actual field ID
        driver.findElement(By.id("login")).click();

        // Handle potential alerts
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept(); // Or handle the alert as necessary
        } catch (Exception e) {
            // No alert present, or not handling alerts
        }
    }
}
