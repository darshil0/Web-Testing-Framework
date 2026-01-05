package com.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test(priority = 1, description = "Verify Google homepage title")
    public void testGoogleTitle() {
        getLogger().info("Starting testGoogleTitle");
        navigateTo("https://www.google.com");
        
        String actualTitle = getDriver().getTitle();
        getLogger().info("Page title: " + actualTitle);
        
        Assert.assertEquals(actualTitle, "Google", "Page title does not match");
        getLogger().info("testGoogleTitle passed");
    }

    @Test(priority = 2, description = "Verify Google search functionality")
    public void testGoogleSearch() {
        getLogger().info("Starting testGoogleSearch");
        navigateTo("https://www.google.com");
        
        WebElement searchBox = getDriver().findElement(By.name("q"));
        String searchQuery = "Selenium WebDriver";
        
        getLogger().info("Searching for: " + searchQuery);
        searchBox.sendKeys(searchQuery);
        searchBox.sendKeys(Keys.RETURN);
        
        String pageTitle = getDriver().getTitle();
        getLogger().info("Search result page title: " + pageTitle);
        
        Assert.assertTrue(pageTitle.contains(searchQuery), 
                         "Search result title does not contain the search query");
        getLogger().info("testGoogleSearch passed");
    }

    @Test(priority = 3, description = "Verify Google search button is displayed")
    public void testGoogleSearchButton() {
        getLogger().info("Starting testGoogleSearchButton");
        navigateTo("https://www.google.com");
        
        WebElement searchButton = getDriver().findElement(By.name("btnK"));
        boolean isDisplayed = searchButton.isDisplayed();
        
        getLogger().info("Search button displayed: " + isDisplayed);
        Assert.assertTrue(isDisplayed, "Search button is not displayed");
        getLogger().info("testGoogleSearchButton passed");
    }
}
