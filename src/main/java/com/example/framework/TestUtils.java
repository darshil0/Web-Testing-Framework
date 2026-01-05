package com.example.framework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class TestUtils {

    private static final Logger logger = LogManager.getLogger(TestUtils.class);

    public static void waitForPageLoad(WebDriver driver, int timeout) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        
        wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
        logger.info("Page loaded completely");
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        logger.info("Scrolled to element");
    }

    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
        logger.info("Element is now visible");
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        logger.info("Element is now clickable");
    }

    public static void clickWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        logger.info("Clicked element using JavaScript");
    }

    public static void switchToFrame(WebDriver driver, WebElement frameElement) {
        driver.switchTo().frame(frameElement);
        logger.info("Switched to frame");
    }

    public static void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
        logger.info("Switched to default content");
    }

    public static void acceptAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        logger.info("Alert accepted");
    }

    public static void dismissAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        logger.info("Alert dismissed");
    }

    public static String getAlertText(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        logger.info("Alert text: " + text);
        return text;
    }
}
