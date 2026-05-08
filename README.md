# NBP Exchange Rates API Test Suite

[![Java](https://img.shields.io/badge/Java-26-orange)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue)](https://maven.apache.org/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.34.3-brightgreen)](https://cucumber.io/)

## 📋 Description

A comprehensive test suite for validating the NBP (National Bank of Poland) exchange rates API using Cucumber BDD framework and REST Assured.

## ✨ Features

- Fetches current exchange rates from NBP API (Table A)
- Validates API response status codes
- Displays exchange rates by currency code (e.g., USD, EUR)
- Displays exchange rates by currency name (e.g., "dolar amerykański")
- Filters and displays currencies with rates above a specified threshold
- Filters and displays currencies with rates below a specified threshold

## 🛠 Technologies Used

- **Java**: Programming language
- **Maven**: Build automation and dependency management
- **Cucumber**: BDD testing framework
- **REST Assured**: API testing library
- **JUnit**: Test execution engine
- **Jackson**: JSON processing
- **Log4j**: Logging in console

## 📋 Prerequisites

- **Java 26** or higher
- **Maven 3.8+**

## 🚀 Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd T-Mobile_api
   ```

2. Install dependencies:
   ```bash
   mvn clean install
   ```

## 🧪 Running Tests

Execute the test suite using Maven:

```bash
mvn test
```

The tests will run the Cucumber scenarios defined in `src/test/resources/Features/Bank.feature`.

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   └── resources/
└── test/
│   ├── java/
│   │   ├── models/
│   │   │   └── Rate.java              # Data model for exchange rates
│   │   ├── runner/
│   │   │   └── TestRunner.java        # Cucumber test runner configuration
│   │   └── StepDefinitions/
│   │       └── BankSteps.java         # Cucumber step definitions
│   └── resources/
│       └── Features/
│           └── Bank.feature           # Cucumber feature file
├── pom.xml                  # Maven configuration
└── README.md                # This file
```

## 🧩 API Endpoint

The tests interact with the NBP API endpoint:
- **Base URL**: `http://api.nbp.pl`
- **Endpoint**: `/api/exchangerates/tables/A?format=json`

## 🧩 Test Scenarios

The current test scenario validates:
- Successful API response (status code verification)
- Retrieval of USD exchange rate by code
- Retrieval of "dolar amerykański" by name
- Display of currencies with rates above 4.0
- Display of currencies with rates below 3.0

---

**Author:** Marcin Radke
**Date:** 07.05.2026
