package com.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

  @Test
  public void testGoogleTitle() {
    driver.get("https://www.google.com");
    Assert.assertEquals(driver.getTitle(), "Google");
  }

  @Test
  public void testGoogleSearch() {
    driver.get("https://www.google.com");
    WebElement searchBox = driver.findElement(By.name("q"));
    searchBox.sendKeys("Selenium WebDriver");
    searchBox.sendKeys(Keys.RETURN);
    Assert.assertTrue(driver.getTitle().contains("Selenium WebDriver"));
  }

  @Test
  public void testGoogleSearchButton() {
    driver.get("https://www.google.com");
    WebElement searchButton = driver.findElement(By.name("btnK"));
    Assert.assertTrue(searchButton.isDisplayed());
  }
}
