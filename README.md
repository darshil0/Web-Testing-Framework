# Web Testing Framework 1.2

This repository contains a lightweight and scalable web testing framework built with Java, Selenium, and TestNG. This updated version introduces a more robust and thread-safe architecture, featuring the Page Object Model (POM), a centralized configuration reader, integrated logging and reporting, and screenshot-on-failure functionality.

## Key Improvements in Version 1.2
- **Thread-Safe Architecture**: The framework now uses `ThreadLocal` to manage WebDriver and ExtentReports instances, ensuring safe parallel test execution.
- **Screenshot on Failure**: Automatically captures a screenshot when a test fails, which is then attached to the test report.
- **Corrected File Structure**: The project structure has been reorganized to follow Maven standard conventions.
- **Bug Fixes**: Resolved issues with report generation and improved overall stability.

## Architecture

The framework has been enhanced to follow a more structured and maintainable architecture:

-   `src/main/java/com/example/framework`: Contains core framework components, including:
    -   `WebDriverFactory`: Manages WebDriver instances.
    -   `ConfigReader`: Reads configuration from `config.properties`.
    -   `ExtentManager`: Manages ExtentReports instances.
    -   `ExtentTestManager`: Manages individual ExtentTest instances in a thread-safe manner.
-   `src/main/java/com/example/pages`: Contains Page Object Model (POM) classes.
    -   `BasePage`: A base class with common web interaction methods.
    -   `LoginPage`: An example page object for the login page.
-   `src/main/resources`: Contains configuration files.
    -   `config.properties`: Stores browser, URL, and login credentials.
    -   `log4j2.xml`: Configures logging levels and appenders.
-   `src/test/java/com/example/tests`: Contains test classes.
    -   `BaseTest`: A base class for tests, handling thread-safe setup and teardown, as well as screenshot capture on failure.
    -   `LoginTest`: An example test class for the login functionality.
-   `pom.xml`: Defines project dependencies and build configuration.
-   `testng.xml`: Configures the TestNG test suite.
-   `target/extent-reports`: Contains the generated ExtentReports.
-   `logs`: Contains the application logs.

## Technologies Used

-   **Java**: The programming language used to build the framework.
-   **Selenium**: The browser automation library.
-   **TestNG**: The testing framework.
-   **Maven**: The build and dependency management tool.
-   **WebDriverManager**: A library that automates the management of WebDriver binaries.
-   **Log4j2**: The logging framework.
-   **ExtentReports**: The reporting library.
-   **Apache Commons IO**: A library used for file operations, including capturing screenshots.

## How to Run Tests

To run the tests, execute the following command from the project root:

```bash
mvn clean test
```

## Reporting

After running the tests, you can find the detailed test report at:

`target/extent-reports/extent-report.html`

The application logs are available at:

`logs/app.log`

Screenshots for failed tests are stored in `target/screenshots/`.
