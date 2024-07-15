# Customer Transaction Management

## Overview

This project is a Spring Boot application that manages customer transactions with authentication and role-based access control.

## Author
- **Janardhan Reddy Nagam**
    - GitHub: [GitHub Profile](https://github.com/janardhanreddy790)
    - Under this profile you can find more POC´s or Projects.

## Prerequisites

- Java 17
- Maven
- PostgreSQL

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/customer-transaction-management.git
    cd customer-transaction-management
    ```

2. Configure the database in `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/yourdbname
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword

    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Build the project:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

### Authentication

#### 1. Sign Up

**Endpoint:** `POST /api/auth/signup`

**Request Body:**
```json
{
  "username": "janardhan",
  "email":"janardhan@gmail.com",
  "password": "password123",
  "role": ["mod", "user"]
}
```
**Response Body:**
```
{
  "message": "User registered successfully!"
}
```

#### 2. Sign In

**Endpoint:** `POST /api/auth/signin`

**Request Body:**
```json
{
  "username": "janardhan",
  "password": "password123"
}
```
**Response Body:**
```
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW5hcmRoYW4iLCJpYXQiOjE3MjEwNTIyNzEsImV4cCI6MTcyMTEzODY3MX0.-4aD4oUHHugRZj05sCoRL4zEmUf9hjFBdOBSOVP0XVY",
    "type": "Bearer",
    "id": 1,
    "username": "janardhan",
    "email": "janardhan@gmail.com",
    "roles": [
        "ROLE_MODERATOR",
        "ROLE_USER"
    ]
}
```
