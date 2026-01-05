package com.example.tests;

import com.example.framework.ConfigReader;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

  @Test
  public void testSuccessfulLogin() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterUsername(ConfigReader.getProperty("username"));
    loginPage.enterPassword(ConfigReader.getProperty("password"));
    loginPage.clickLoginButton();
    Assert.assertTrue(
        loginPage.getFlashMessageText().contains("You logged into a secure area!"),
        "Login was not successful");
  }

  @Test
  public void testFailedLogin() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.enterUsername("wronguser");
    loginPage.enterPassword("wrongpassword");
    loginPage.clickLoginButton();
    Assert.assertTrue(
        loginPage.getFlashMessageText().contains("Your username is invalid!"),
        "Error message for failed login was not displayed");
  }
}
