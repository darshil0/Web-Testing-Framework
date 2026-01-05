package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleHomePage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public void searchFor(String query) {
        sendKeysToElement(searchBox, query);
        clickElement(searchButton);
    }

    public boolean isSearchButtonDisplayed() {
        return searchButton.isDisplayed();
    }
}
