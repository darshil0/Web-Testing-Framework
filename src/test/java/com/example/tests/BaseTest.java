package com.example.tests;

import com.example.framework.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

  protected WebDriver driver;

  @BeforeMethod
  public void setUp() {
    driver = WebDriverFactory.createWebDriver("chrome");
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
