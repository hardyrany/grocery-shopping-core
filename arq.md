# Project Architecture: Grocery Shopping Core

## 1. Overview
**Grocery Shopping Core** is a console application built with **Pure Java (no frameworks)**. The main goal is to master Object-Oriented Programming (OOP) fundamentals, Collections, Exception Handling, and software architecture before moving on to Spring Boot.

## 2. Architectural Pattern
The project uses a hybrid approach:
- **Package by Feature**: The code is organized by business domain (`product`, `cart`) instead of global layers.
- **Layered Architecture**: Within each feature, the code is divided into logical layers:
  - `domain`: Entities and pure business rules.
  - `application`: Services that orchestrate use cases.
  - `infrastructure`: In-memory repositories and persistence details.
- **Global Infrastructure**: Cross-cutting utilities (e.g., `ConsoleUtils`, `GlobalExceptionHandler`) are isolated in the `infrastructure` folder at the project root.

## 3. Directory Structure

```text
01-grocery-shopping-core/
│
├── .github/
│   └── workflows/
│       ├── ci.yml                   # Continuous Integration Pipeline
│       ├── cd.yml                   # Continuous Delivery Pipeline
│       └── block-direct-pr.yml      # Branch Protection Workflow
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── hardy/
│   │               └── grocery/
│   │                   ├── GroceryApplication.java  # Entry point (Main / Menu)
│   │                   │
│   │                   ├── infrastructure/          # Global concerns
│   │                   │   ├── exception/
│   │                   │   │   ├── GlobalExceptionHandler.java
│   │                   │   │   ├── InvalidQuantityException.java
│   │                   │   │   └── ProductNotFoundException.java
│   │                   │   └── util/
│   │                   │       └── ConsoleUtils.java
│   │                   │
│   │                   ├── product/                 # Feature: Products
│   │                   │   ├── domain/entity/Product.java
│   │                   │   ├── infrastructure/repository/ProductRepository.java
│   │                   │   └── application/service/ProductService.java
│   │                   │
│   │                   └── cart/                    # Feature: Cart
│   │                       ├── domain/entity/
│   │                       │   ├── Cart.java
│   │                       │   └── CartItem.java
│   │                       ├── infrastructure/repository/CartRepository.java
│   │                       └── application/service/CartService.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── hardy/
│                   └── grocery/
│                       ├── product/ProductServiceTest.java
│                       └── cart/CartServiceTest.java
│
├── .env.example                     # Base environment variables
├── .gitignore
├── Dockerfile                       # Multi-stage build for Java 21
├── LICENSE
├── README.md
├── arq.md                           # This document
└── pom.xml                          # Dependency management (Maven + JUnit 5)
