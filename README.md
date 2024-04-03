# Project Name
## SmartAPI Integration

## Description

The SmartAPI Integration project is a Java Spring Boot application designed to provide user authentication, data retrieval, and documentation functionalities using Swagger. It includes features for user registration, login, logout, and retrieving data from a public API. Additionally, it allows users to interact with Ethereum wallet balances through API endpoints. The project utilizes H2 Database as an in-memory storage solution for user data.

## Features

### User Authentication

- **User Registration**: 
  - Method: POST
  - Endpoint: `/api/register`
  - Parameters:
    - `name` (string, required): Full name of the user.
    - `username` (string, required): Chosen username.
    - `password` (string, required): Chosen password.

- **User Login**:
  - Method: POST
  - Endpoint: `/api/login`
  - Parameters:
    - `username` (string, required): User's username.
    - `password` (string, required): User's password.

- **User Logout**:
  - Method: POST
  - Endpoint: `/api/logout`
  - Description: Logs out the authenticated user.

### Data Retrieval

- **Get Entries**:
  - Method: GET
  - Endpoint: `/api/entries`
  - Description: Retrieves entries from a public API with filtering options based on categories and result limits.

- **Get Ethereum Wallet Balance**:
  - Method: GET
  - Endpoint: `/api/ethereumbalance`
  - Parameters:
    - `wallet_address` (string, required): Ethereum wallet address.

### Swagger Documentation

- **Swagger UI**:
  - Accessible at: `/swagger-ui.html`
  - Description: Interactive API documentation generated using Swagger, providing clear documentation of all API endpoints, request parameters, and responses.

## Technologies Used

- Spring Boot
- Java
- H2 Database (In-memory database)
- Public APIs (for retrieving data)
- Ethereum API (for fetching wallet balances)
- Swagger (for API documentation)

## Installation

1. Clone the repository.
2. Build the project using Maven or your preferred build tool.
3. Run the application.
