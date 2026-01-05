package com.example.tests;

import com.example.framework.RetryAnalyzer;
import com.example.pages.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CoreFunctionalityTest extends BaseTest {

    @Test(priority = 1, description = "Verify search button presence on Google homepage",
          retryAnalyzer = RetryAnalyzer.class)
    public void testSearchButtonPresence() {
        logger.info("Starting testSearchButtonPresence");
        navigateTo("https://www.google.com");
        
        GoogleHomePage homePage = new GoogleHomePage(driver);
        boolean isDisplayed = homePage.isSearchButtonDisplayed();
        
        logger.info("Search button displayed: " + isDisplayed);
        Assert.assertTrue(isDisplayed, "Search button is not displayed");
        logger.info("testSearchButtonPresence passed");
    }

    @DataProvider(name = "searchQueries")
    public Object[][] createSearchQueries() {
        return new Object[][] {
            {"TestNG"},
            {"Selenium"},
            {"Maven"}
        };
    }

    @Test(priority = 2, dataProvider = "searchQueries", 
          description = "Data-driven search test",
          retryAnalyzer = RetryAnalyzer.class)
    public void testDataDrivenSearch(String query) {
        logger.info("Starting testDataDrivenSearch with query: " + query);
        navigateTo("https://www.google.com");
        
        GoogleHomePage homePage = new GoogleHomePage(driver);
        homePage.searchFor(query);
        
        String pageTitle = driver.getTitle();
        logger.info("Page title after search: " + pageTitle);
        
        Assert.assertTrue(pageTitle.contains(query), 
                         "Search result title does not contain query: " + query);
        logger.info("testDataDrivenSearch passed for query: " + query);
    }
}
