package com.example.tests;

import com.example.pages.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CoreFunctionalityTest extends BaseTest {

  @Test
  public void testSearchButtonPresence() {
    driver.get("https://www.google.com");
    GoogleHomePage homePage = new GoogleHomePage(driver);
    Assert.assertTrue(homePage.isSearchButtonDisplayed(), "Search button is not displayed");
  }

  @DataProvider(name = "searchQueries")
  public Object[][] createSearchQueries() {
    return new Object[][] {
      {"TestNG"},
      {"Selenium"},
      {"Maven"}
    };
  }

  @Test(dataProvider = "searchQueries")
  public void testDataDrivenSearch(String query) {
    driver.get("https://www.google.com");
    GoogleHomePage homePage = new GoogleHomePage(driver);
    homePage.searchFor(query);
    Assert.assertTrue(driver.getTitle().contains(query), "Search result title does not contain query");
  }
}
