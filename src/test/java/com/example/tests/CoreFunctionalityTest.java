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
        getLogger().info("Starting testSearchButtonPresence");
        navigateTo("https://www.google.com");
        
        GoogleHomePage homePage = new GoogleHomePage(getDriver());
        boolean isDisplayed = homePage.isSearchButtonDisplayed();
        
        getLogger().info("Search button displayed: " + isDisplayed);
        Assert.assertTrue(isDisplayed, "Search button is not displayed");
        getLogger().info("testSearchButtonPresence passed");
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
        getLogger().info("Starting testDataDrivenSearch with query: " + query);
        navigateTo("https://www.google.com");
        
        GoogleHomePage homePage = new GoogleHomePage(getDriver());
        homePage.searchFor(query);
        
        String pageTitle = getDriver().getTitle();
        getLogger().info("Page title after search: " + pageTitle);
        
        Assert.assertTrue(pageTitle.contains(query), 
                         "Search result title does not contain query: " + query);
        getLogger().info("testDataDrivenSearch passed for query: " + query);
    }
}
