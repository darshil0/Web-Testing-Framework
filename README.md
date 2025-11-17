# Web Testing Framework

This is a new repository created for Web Testing Framework creation and understanding. This is a lightweight web testing framework built using Java, Selenium, and TestNG.


## Architecture

The framework follows a simple and extensible architecture:

- `src/main/java`: Contains the core framework components, including the `WebDriverFactory`.
- `src/test/java`: Contains the test classes and the `BaseTest` class.
- `pom.xml`: Defines the project dependencies and build configuration.
- `testng.xml`: Configures the TestNG test suite.

## Technologies Used

- **Java**: The programming language used to build the framework.
- **Selenium**: The browser automation library.
- **TestNG**: The testing framework.
- **Maven**: The build and dependency management tool.
- **WebDriverManager**: A library that automates the management of WebDriver binaries.

## How to Run Tests

To run the tests, execute the following command from the project root:

```bash
mvn clean test
```
