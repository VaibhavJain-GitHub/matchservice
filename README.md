# Match Service

The Match Service is a microservice that manages cricket match data. It provides RESTful APIs for creating, retrieving, updating, and deleting match records. This service is built using Spring Boot, H2 in-memory database, and Swagger for API documentation.

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Running Tests](#running-tests)
- [Error Handling](#error-handling)
- [Swagger Documentation](#swagger-documentation)
- [Contributing](#contributing)
- [License](#license)

## Features
- CRUD operations for cricket matches
- In-memory H2 database for development and testing
- Detailed API documentation with Swagger
- Global exception handling for consistent error responses

## Tech Stack
- Java 17
- Spring Boot 3.3.0
- Spring Data JPA
- H2 Database
- Swagger (Springdoc OpenAPI 3)
- JUnit 5
- Mockito
- Lombok

## Setup and Installation

### Prerequisites
- Java 17
- Maven

### Installation Steps
1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/matchservice.git
   cd match-service
