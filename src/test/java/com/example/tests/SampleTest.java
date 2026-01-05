package com.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test(priority = 1, description = "Verify Google homepage title")
    public void testGoogleTitle() {
        logger.info("Starting testGoogleTitle");
        navigateTo("https://www.google.com");
        
        String actualTitle = driver.getTitle();
        logger.info("Page title: " + actualTitle);
        
        Assert.assertEquals(actualTitle, "Google", "Page title does not match");
        logger.info("testGoogleTitle passed");
    }

    @Test(priority = 2, description = "Verify Google search functionality")
    public void testGoogleSearch() {
        logger.info("Starting testGoogleSearch");
        navigateTo("https://www.google.com");
        
        WebElement searchBox = driver.findElement(By.name("q"));
        String searchQuery = "Selenium WebDriver";
        
        logger.info("Searching for: " + searchQuery);
        searchBox.sendKeys(searchQuery);
        searchBox.sendKeys(Keys.RETURN);
        
        String pageTitle = driver.getTitle();
        logger.info("Search result page title: " + pageTitle);
        
        Assert.assertTrue(pageTitle.contains(searchQuery), 
                         "Search result title does not contain the search query");
        logger.info("testGoogleSearch passed");
    }

    @Test(priority = 3, description = "Verify Google search button is displayed")
    public void testGoogleSearchButton() {
        logger.info("Starting testGoogleSearchButton");
        navigateTo("https://www.google.com");
        
        WebElement searchButton = driver.findElement(By.name("btnK"));
        boolean isDisplayed = searchButton.isDisplayed();
        
        logger.info("Search button displayed: " + isDisplayed);
        Assert.assertTrue(isDisplayed, "Search button is not displayed");
        logger.info("testGoogleSearchButton passed");
    }
}
