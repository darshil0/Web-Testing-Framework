package com.example.tests;

import com.example.framework.ConfigReader;
import com.example.framework.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        try {
            String browser = ConfigReader.getProperty("browser", "chrome");
            boolean headless = ConfigReader.getBooleanProperty("headless", true);
            
            logger.info("Setting up test with browser: " + browser);
            driver = WebDriverFactory.createWebDriver(browser, headless);
            
            // Configure timeouts
            int implicitWait = ConfigReader.getIntProperty("implicit.wait", 10);
            int pageLoadTimeout = ConfigReader.getIntProperty("page.load.timeout", 30);
            
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
            
            logger.info("Test setup completed successfully");
        } catch (Exception e) {
            logger.error("Failed to setup test", e);
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                logger.info("Tearing down test");
                driver.quit();
                logger.info("WebDriver quit successfully");
            } catch (Exception e) {
                logger.error("Error during teardown", e);
            }
        }
    }

    protected void navigateTo(String url) {
        logger.info("Navigating to: " + url);
        driver.get(url);
    }
}
