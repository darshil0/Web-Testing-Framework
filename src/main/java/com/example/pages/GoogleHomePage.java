package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleHomePage {

  private WebDriver driver;

  // Locators
  private By searchBox = By.name("q");
  private By searchButton = By.name("btnK");

  public GoogleHomePage(WebDriver driver) {
    this.driver = driver;
  }

  public void searchFor(String query) {
    WebElement searchElement = driver.findElement(searchBox);
    searchElement.sendKeys(query);
    searchElement.sendKeys(Keys.RETURN);
  }

  public boolean isSearchButtonDisplayed() {
    return driver.findElement(searchButton).isDisplayed();
  }
}
