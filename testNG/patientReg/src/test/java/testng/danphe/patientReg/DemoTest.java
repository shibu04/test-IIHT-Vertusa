package testng.danphe.patientReg;

import org.testng.annotations.Test;

import io.netty.handler.timeout.TimeoutException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class DemoTest {
  @Test
  public void f() {
	  System.out.println("this is my test area");
  }
  
  @Test
  public void verifyDanpheEmrHeader() {
	  
	  System.out.println("this is my test area 2");
//      try {
//          // Wait for the text to be present in the element
//          wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h1[contains(text(), 'Danphe Emr')]"), "Danphe EMR"));
//          WebElement header = driver.findElement(By.xpath("//h1[contains(text(), 'Danphe Emr')]"));
//          Assert.assertTrue(header.isDisplayed(), "Header 'Danphe EMR' is visible.");
//      } catch (TimeoutException e) {
//          System.out.println("Element not found: " + e.getMessage());
//          // Log more information or take necessary action
//          throw e;
//          }
  }
  
  @BeforeClass
  public void beforeClass() {
	  System.out.println("this is my precondition test area");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("this is my post condition test area");
  }
  
  @BeforeMethod
  public void beforeMethod() {
      System.out.println("Before mathord validate app url , Starting new test");
  }

  @AfterMethod
  public void afterMethod() {
      System.out.println("after methord close URL Test completed");
  }
  

}


//testng.danphe.patientReg.DemoTest