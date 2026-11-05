# Grocery Shopping Core

A console application built with **Pure Java (no frameworks)** to simulate a grocery store. This project focuses on mastering Object-Oriented Programming (OOP), Collections, Exception Handling, and clean software architecture.

![Java](https://img.shields.io/badge/Java-21-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Status](https://img.shields.io/badge/Status-Completed-success)

## 📋 About the Project

**Grocery Shopping Core** is a command-line application that allows users to manage products and a shopping cart. It was designed to practice Java fundamentals before moving on to Spring Boot.

The project is structured using **Package by Feature** and **Layered Architecture**, keeping the domain logic separate from infrastructure and application services.

## 🚀 Features

- **Product Management:** Add, list, remove products, and update stock.
- **Shopping Cart:** Start a cart, add products (with stock validation), and view the total price.
- **Exception Handling:** Custom exceptions for invalid quantities and missing products.
- **In-Memory Persistence:** Simulates a database using `HashMap`.

## 🏗️ Architecture

The code is organized by business domains (`product`, `cart`), and each domain is divided into layers:

- `domain/entity`: Core business models.
- `infrastructure/repository`: In-memory data persistence.
- `application/service`: Business logic and use cases.

Global utilities and exception handlers live in the `infra` package at the project root.

## 🛠️ Technologies Used

- **Java 21**
- **Maven** (Build and Dependency Management)
- **JUnit 5** (Unit Testing)
- **Java Collections Framework** (HashMap, List)
- **Docker** (Multi-stage build)

## ⚙️ How to Run

### Prerequisites

- Java 21 or higher
- Maven installed

### Steps

```bash
# Clone the repository
git clone https://github.com/hardyrany/grocery-shopping-core.git

# Enter the folder
cd 01-grocery-shopping-core

# Compile and run tests
mvn clean test

# Package the application
mvn clean package

# Run the generated JAR
java -jar target/grocery-shopping-core-1.0.0.jar
```

## 🧪 Testing

The project uses JUnit 5 to validate business rules, such as:

- Preventing the creation of products with negative prices or stock.
- Preventing the addition of items to the cart when there is not enough stock.
- Calculating the cart's total price correctly.

To run the tests:

```bash
mvn test
```

## 👤 Author

Hardy Rany

GitHub: [@hardyrany](https://github.com/hardyrany)

## 📄 License

This project is licensed under the MIT License. See the LICENSE file for details.
