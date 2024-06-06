package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"features"},
    glue = {"loginsteps"}, // specify the package containing step definitions
    plugin = {"pretty", "html:target/cucumber-reports"},
    // dryRun = true,
    monochrome = true
   //  tags = "@R"
    )
public class TestRunnerLogin {

}