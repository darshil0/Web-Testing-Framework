package com.example.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebDriverFactory {

    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver createWebDriver(String browser) {
        return createWebDriver(browser, false);
    }

    public static WebDriver createWebDriver(String browser, boolean headless) {
        WebDriver driver;
        logger.info("Creating WebDriver for browser: " + browser + " (headless: " + headless + ")");

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                logger.error("Unsupported browser: " + browser);
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        logger.info("WebDriver created successfully");
        return driver;
    }
}
