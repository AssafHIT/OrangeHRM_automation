# 🍊 OrangeHRM Test Automation Framework

Welcome to the **Selenium Test Automation Framework** for **OrangeHRM**! This project automates functional and UI testing for the **OrangeHRM** web application, a popular Human Resource Management (HRM) system. The goal is to ensure high-quality, bug-free, and seamless user interactions in the application.

🔧 **Built With:**
- **Selenium** 🤖 - For automating browser interactions
- **Java** ☕ - For scripting and automation logic
- **JUnit 5** 🚀 - For test execution and reporting
- **Maven** 📦 - For managing project dependencies and build automation
- **Page Object Model** (POM) 📂 - For scalable, maintainable, and reusable test scripts

This framework automates testing for essential HRM workflows such as logging in, adding/removing employees, and managing employee information. The automated tests ensure the application functions as expected and is free from regressions.

---

![OrangeHRM Dashboard](image2025-02-26112838164.png)

## 🚀 Features

- **Login Automation**: Automated testing for both valid and invalid login scenarios.
- **Employee Management**: Test functionalities for adding and searching employees in the Personal Information Management (PIM) section.
- **PIM Module**: Tests for adding new employees and searching for existing employees with specific criteria (e.g., first name, last name, employee ID).
- **Cross-Platform Testing**: Supports running tests across different browsers and platforms using Maven for dependency management.

---

## 🔍 Test Scenarios

Here are some of the scenarios automated in this framework:

- **Login Automation**:
    - Valid login with correct username and password.
    - Invalid login with incorrect password.
  
- **Employee Management**:
    - Add a new employee and verify that it appears in the system.
    - Search for an employee by first name, last name, and employee ID.
  
- **PIM Module**:
    - Navigate to PIM section and ensure proper functionality (e.g., employee data retrieval).

---

## 🛠️ Framework Structure

The project is organized in a modular structure to enhance maintainability and scalability:

- **`src/main/java`**: Contains the page object classes representing the different pages of the OrangeHRM application.
  - **`pages`**: Includes classes such as `LoginPage`, `DashboardPage`, `PIMPage`, and `BasePage`.
  
- **`src/test/java`**: Contains test classes with scenarios for login, employee management, and more.
  - **`tests`**: Includes classes like `LoginTest`, `DashboardTest`, `PIMTest`, etc.

- **`pom.xml`**: Manages project dependencies using Maven.
- **`.gitignore`**: Specifies which files should be ignored by Git (e.g., IDE configurations, build outputs).

---


## 🔍 Summary

This project showcases an automated framework for testing critical functionalities of the **OrangeHRM** web application using **Selenium** and **JUnit**. The framework follows best practices in test automation, including **Page Object Model** for scalability, and generates insightful reports with **Allure**. 

Feel free to explore the repository and raise issues or questions if you have any feedback or would like to discuss the automation techniques used in this project!
---
