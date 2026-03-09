# OrangeHRM Automation Framework

![Java](https://img.shields.io/badge/Java-17-blue)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-green)
![Cucumber](https://img.shields.io/badge/Cucumber-BDD-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-orange)
![BrowserStack](https://img.shields.io/badge/BrowserStack-CrossBrowser-blueviolet)
![GitHub Actions](https://img.shields.io/badge/CI-GitHub%20Actions-black)

Automated **End-to-End testing framework** for **OrangeHRM** using **Selenium WebDriver, Cucumber (BDD), Java, Maven**.  
Includes **cross-browser execution on BrowserStack** and **CI/CD with GitHub Actions**.

Application under test:

https://opensource-demo.orangehrmlive.com

---

# Overview

This project automates a critical workflow in OrangeHRM:

1. Login
2. Navigate to **PIM**
3. Create a new employee
4. Upload profile photo
5. Validate employee creation
6. Navigate to **Directory**
7. Search employee
8. Validate employee exists

The framework supports:

- Local execution
- Remote execution in BrowserStack
- CI execution in GitHub Actions

---

# Tech Stack

| Tool | Purpose |
|-----|-----|
| Java 17 | Programming language |
| Selenium WebDriver | UI Automation |
| Cucumber | BDD |
| Maven | Dependency management |
| WebDriverManager | Driver management |
| Extent Reports | Advanced reporting |
| BrowserStack | Cross browser testing |
| GitHub Actions | CI/CD |

---

# Project Structure

| Folder/File | Description |
|-------------|-------------|
| `src/main` | Base source folder (reserved for framework core if needed) |
| `src/test/java/pages` | Page Object Model classes that contain UI locators and actions |
| `src/test/java/stepdefinitions` | Cucumber step definitions implementing the BDD scenarios |
| `src/test/java/hooks` | Setup and teardown logic executed before and after test scenarios |
| `src/test/java/utils` | Utility classes such as `DriverFactory` used for driver management |
| `src/test/java/runners` | TestRunner configuration used to execute Cucumber tests |
| `src/test/resources/features` | Cucumber feature files describing the scenarios |
| `src/test/resources/testdata` | Test data used during execution (e.g., images for upload) |
| `.github/workflows` | GitHub Actions CI/CD pipeline configuration |
| `pom.xml` | Maven project configuration and dependency management |
| `README.md` | Project documentation |
---

# Framework Architecture

The framework follows **Page Object Model (POM)**.

Components:

| Component | Responsibility |
|------|------|
| Pages | UI interactions |
| Step Definitions | BDD logic |
| Hooks | Setup / teardown |
| DriverFactory | Driver management |
| TestRunner | Cucumber execution |

---

# Test Execution

## Run locally
mvn clean test

## Run with BrowserStack
mvn clean test "-Dexecution.mode=browserstack"

## Supported Browsers
- Chrome
- Edge
- Firefox

Executed through BrowserStack cloud infrastructure.

# Reports
Reports generated after execution.

## Cucumber Report
target/cucumber-reports
## Extent Report
target/extent-reports

## Reports include
- Scenario results
- Logs
- Screenshot on failure

# CI/CD
This project includes a GitHub Actions pipeline that automatically runs tests.

## Workflow location:
.github/workflows/ci.yml

## Pipeline Steps
1. Checkout repository
2. Steup Java 17
3. Buils with Maven
4. Execute automated tests
5. Publish Reports

# BrowserStack Integration

Tests can be executed in the cloud using BrowserStack.

## Configured capabilities:

- OS: Windows 11
- Browsers: Chrome, Edge, Firefox
- Resolution: 1920x1080

File uploads are supported using:
LocalFileDetector

# Key Technical Decisions
- Page Object Model implementation
- DriverFactory supporting local and remote execution
- Custom waits for dynamic components
- Parameterized execution using system properties
- GitHub Actions CI/CD pipeline
- BrowserStack cloud execution
