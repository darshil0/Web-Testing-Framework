package com.example.tests;

import com.aventstack.extentreports.Status;
import com.example.framework.ConfigReader;
import com.example.framework.ExtentManager;
import com.example.framework.ExtentTestManager;
import com.example.framework.WebDriverFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

  private static final Logger logger = LogManager.getLogger(BaseTest.class);
  private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

  @BeforeSuite
  public void beforeSuite() {
    ExtentManager.getInstance();
  }

  @BeforeMethod
  public void setUp(Method method) {
    ExtentTestManager.startTest(method.getName());
    logger.info("Starting test: " + method.getName());
    driver.set(WebDriverFactory.createWebDriver(ConfigReader.getProperty("browser")));
    getDriver().manage().window().maximize();
    getDriver()
        .manage()
        .timeouts()
        .implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicit.wait"))));
    getDriver()
        .manage()
        .timeouts()
        .pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("page.load.timeout"))));
    getDriver().get(ConfigReader.getProperty("url"));
  }

  @AfterMethod
  public void tearDown(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
      ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
      logger.error("Test Failed: " + result.getName());
      if (Boolean.parseBoolean(ConfigReader.getProperty("capture.screenshot.on.failure"))) {
        captureScreenshot(result.getName());
      }
    } else if (result.getStatus() == ITestResult.SUCCESS) {
      ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
      logger.info("Test Passed: " + result.getName());
    } else if (result.getStatus() == ITestResult.SKIP) {
      ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
      logger.warn("Test Skipped: " + result.getName());
    }

    if (getDriver() != null) {
      getDriver().quit();
    }
  }

  @AfterSuite
  public void afterSuite() {
    ExtentTestManager.endTest();
  }

  public WebDriver getDriver() {
    return driver.get();
  }

  public Logger getLogger() {
    return logger;
  }

  public void navigateTo(String url) {
    getDriver().get(url);
  }

  private void captureScreenshot(String testName) {
    File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
    String screenshotPath = ConfigReader.getProperty("screenshot.path") + testName + ".png";
    try {
      FileUtils.copyFile(screenshot, new File(screenshotPath));
      ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
    } catch (IOException e) {
      logger.error("Failed to capture screenshot: " + e.getMessage());
    }
  }
}
