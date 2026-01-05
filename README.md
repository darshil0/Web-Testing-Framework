# Web Testing Framework

A comprehensive, production-ready web testing framework built with Java, Selenium WebDriver, and TestNG. This framework provides a robust foundation for automated UI testing with built-in reporting, logging, retry mechanisms, and parallel execution support.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Viewing Reports](#viewing-reports)
- [Writing Tests](#writing-tests)
- [Best Practices](#best-practices)
- [Technologies Used](#technologies-used)

## Features

- **Page Object Model (POM)**: Clean separation of test logic and page elements
- **WebDriver Management**: Automatic driver management with WebDriverManager
- **Configurable Execution**: Support for multiple browsers and headless mode
- **Parallel Execution**: Run tests in parallel for faster execution
- **Extent Reports**: Beautiful HTML reports with screenshots
- **Logging**: Comprehensive logging with Log4j2
- **Retry Mechanism**: Automatic retry for flaky tests
- **Screenshot Capture**: Automatic screenshots on test failures
- **Data-Driven Testing**: Support for data providers
- **Utilities**: Common helper methods for web automation
- **Configuration Management**: Externalized configuration via properties file

## Architecture

The framework follows a layered architecture:

```
┌─────────────────────────────────────┐
│         Test Layer                  │
│  (SampleTest, CoreFunctionalityTest)│
└─────────────────┬───────────────────┘
                  │
┌─────────────────▼───────────────────┐
│         Page Layer                  │
│  (LoginPage, GoogleHomePage, etc.)  │
└─────────────────┬───────────────────┘
                  │
┌─────────────────▼───────────────────┐
│      Framework Layer                │
│  (WebDriverFactory, ConfigReader,   │
│   TestListener, ExtentManager)      │
└─────────────────────────────────────┘
```

## Prerequisites

- **Java**: JDK 11 or higher
- **Maven**: 3.6.0 or higher
- **Chrome/Firefox**: Latest stable version

## Project Structure

```
web-testing-framework/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       ├── framework/         # Core framework components
│   │   │       │   ├── ConfigReader.java
│   │   │       │   ├── ExtentManager.java
│   │   │       │   ├── ExtentTestManager.java
│   │   │       │   ├── RetryAnalyzer.java
│   │   │       │   ├── TestListener.java
│   │   │       │   ├── TestUtils.java
│   │   │       │   └── WebDriverFactory.java
│   │   │       └── pages/             # Page Object classes
│   │   │           ├── BasePage.java
│   │   │           ├── GoogleHomePage.java
│   │   │           └── LoginPage.java
│   │   └── resources/
│   │       ├── config.properties      # Test configuration
│   │       └── log4j2.xml            # Logging configuration
│   │
│   └── test/
│       └── java/
│           └── com/example/tests/     # Test classes
│               ├── BaseTest.java
│               ├── CoreFunctionalityTest.java
│               └── SampleTest.java
│
├── target/                            # Build output
│   ├── extent-reports/               # HTML test reports
│   ├── screenshots/                  # Failure screenshots
│   └── logs/                         # Application logs
│
├── .gitignore
├── pom.xml                           # Maven configuration
├── testng.xml                        # TestNG suite configuration
└── README.md
```

## Installation

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd web-testing-framework
   ```

2. **Install dependencies**:
   ```bash
   mvn clean install -DskipTests
   ```

3. **Move configuration files** (if not already in resources):
   ```bash
   # Ensure config.properties is in src/main/resources/
   # Ensure log4j2.xml is in src/main/resources/
   ```

## Configuration

Edit `src/main/resources/config.properties` to customize test execution:

```properties
# Browser Configuration
browser=chrome                         # chrome or firefox
headless=true                         # true for headless, false for GUI

# Test URLs
url=http://the-internet.herokuapp.com/login

# Test Credentials
username=tomsmith
password=SuperSecretPassword!

# Timeout Configuration (in seconds)
implicit.wait=10
explicit.wait=15
page.load.timeout=30

# Parallel Execution
thread.count=3

# Retry Configuration
max.retry.count=2

# Screenshot Configuration
capture.screenshot.on.failure=true
screenshot.path=target/screenshots/
```

## Running Tests

### Run all tests:
```bash
mvn clean test
```

### Run specific test suite:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run with custom browser:
```bash
mvn clean test -Dbrowser=firefox
```

### Run in non-headless mode:
```bash
mvn clean test -Dheadless=false
```

### Run specific test class:
```bash
mvn clean test -Dtest=SampleTest
```

### Run specific test method:
```bash
mvn clean test -Dtest=SampleTest#testGoogleTitle
```

### Run with parallel execution:
```bash
mvn clean test -Dparallel=tests -DthreadCount=3
```

## Viewing Reports

After test execution, reports are generated in multiple locations:

### Extent Reports (Recommended):
- **Location**: `target/extent-reports/extent-report.html`
- **Features**: 
  - Beautiful dashboard with test statistics
  - Screenshots for failed tests
  - Detailed test execution logs
  - Filterable results

### TestNG Reports:
- **Location**: `target/surefire-reports/index.html`
- **Features**: Standard TestNG HTML report

### Screenshots:
- **Location**: `target/screenshots/`
- Automatically captured on test failures

### Logs:
- **Location**: `logs/app.log`
- Detailed execution logs with timestamps

## Writing Tests

### 1. Create a Page Object Class

```java
package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourPage extends BasePage {

    @FindBy(id = "elementId")
    private WebElement element;

    public YourPage(WebDriver driver) {
        super(driver);
    }

    public void performAction() {
        clickElement(element);
    }
}
```

### 2. Create a Test Class

```java
package com.example.tests;

import com.example.framework.RetryAnalyzer;
import com.example.pages.YourPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YourTest extends BaseTest {

    @Test(priority = 1, 
          description = "Test description",
          retryAnalyzer = RetryAnalyzer.class)
    public void testSomething() {
        logger.info("Starting test");
        navigateTo("https://example.com");
        
        YourPage page = new YourPage(driver);
        page.performAction();
        
        Assert.assertTrue(true, "Test assertion");
        logger.info("Test completed");
    }
}
```

### 3. Add Test to testng.xml

```xml
<test name="YourTests">
    <classes>
        <class name="com.example.tests.YourTest"/>
    </classes>
</test>
```

## Best Practices

1. **Follow Page Object Model**: Keep page elements and actions in page classes
2. **Use Descriptive Names**: Test methods should clearly describe what they test
3. **Add Logging**: Log important steps for better debugging
4. **Handle Waits Properly**: Use explicit waits from BasePage methods
5. **Keep Tests Independent**: Each test should be able to run independently
6. **Use Data Providers**: For data-driven testing scenarios
7. **Add Retry Logic**: Use RetryAnalyzer for flaky tests
8. **Verify Assertions**: Always include meaningful assertion messages
9. **Clean Up Resources**: Ensure tearDown methods run properly
10. **Document Tests**: Add descriptions to @Test annotations

## Common Issues and Solutions

### Issue: WebDriver not found
**Solution**: WebDriverManager automatically handles this. Ensure you have internet connectivity.

### Issue: Tests fail in headless mode but pass in GUI mode
**Solution**: Check for timing issues. Increase wait times or add explicit waits.

### Issue: Screenshots not captured
**Solution**: Verify the screenshot path exists and has write permissions.

### Issue: Configuration not loading
**Solution**: Ensure config.properties is in src/main/resources/ directory.

## Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 11+ | Programming language |
| Selenium WebDriver | 4.21.0 | Browser automation |
| TestNG | 7.11.0 | Testing framework |
| Maven | 3.6.0+ | Build and dependency management |
| WebDriverManager | 5.3.2 | Automatic WebDriver management |
| ExtentReports | 5.1.1 | Advanced test reporting |
| Log4j2 | 2.23.1 | Logging framework |
| Spotless | 2.43.0 | Code formatting |

## Advanced Features

### Parallel Execution
Configure in testng.xml:
```xml
<suite name="Suite" parallel="tests" thread-count="3">
```

### Custom Listeners
Add custom behavior by extending TestListener:
```java
public class CustomListener extends TestListener {
    // Override methods as needed
}
```

### Utility Methods
Use TestUtils for common operations:
```java
TestUtils.waitForPageLoad(driver, 30);
TestUtils.scrollToElement(driver, element);
TestUtils.clickWithJS(driver, element);
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## Support

For issues, questions, or contributions, please:
- Create an issue in the repository
- Contact the development team
- Review existing documentation

## License

This project is licensed under the MIT License.

---

**Happy Testing! 🚀**
