package com.example.tests;

import com.aventstack.extentreports.Status;
import com.example.framework.ConfigReader;
import com.example.framework.ExtentManager;
import com.example.framework.ExtentTestManager;
import com.example.framework.WebDriverFactory;
import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

  protected WebDriver driver;
  private static final Logger logger = LogManager.getLogger(BaseTest.class);

  @BeforeSuite
  public void beforeSuite() {
    ExtentManager.getInstance();
  }

  @BeforeMethod
  public void setUp(Method method) {
    ExtentTestManager.startTest(method.getName());
    logger.info("Starting test: " + method.getName());
    driver = WebDriverFactory.createWebDriver(ConfigReader.getProperty("browser"));
    driver.get(ConfigReader.getProperty("url"));
  }

  @AfterMethod
  public void tearDown(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
      ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
      logger.error("Test Failed: " + result.getName());
    } else if (result.getStatus() == ITestResult.SUCCESS) {
      ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
      logger.info("Test Passed: " + result.getName());
    } else if (result.getStatus() == ITestResult.SKIP) {
      ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
      logger.warn("Test Skipped: " + result.getName());
    }

    if (driver != null) {
      driver.quit();
    }
  }

  @AfterSuite
  public void afterSuite() {
    ExtentTestManager.endTest();
  }
}
