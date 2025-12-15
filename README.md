# Game Sessions API

A Spring Boot–based REST API for managing games, players, and multiplayer game sessions. The project demonstrates a clean layered architecture (controllers, services, repositories), DTO-based request/response handling, and JWT-based authentication.

This repository is suitable as a backend foundation for multiplayer or session-based gaming platforms and as a reference project for Spring Boot REST API design.

## Key Features

- **Game management** – create and retrieve games with genre support
- **Player management** – register and manage players
- **Session management** – create and manage game sessions
- **JWT authentication** – stateless authentication and request filtering
- **DTO-driven API** – clear separation between persistence models and API contracts
- **Global exception handling** – consistent error responses

## Tech Stack

- **Java 17+**
- **Spring Boot 3**
- **Spring Web (REST)**
- **Spring Security** with JWT
- **Spring Data JPA**
- **Maven**

## Project Structure

```
src/main/java/com/example/testing
├── configuration        # Security configuration
├── controller           # REST controllers (Game, Player, Session)
├── dto                  # Request/response DTOs
├── exception            # Custom exceptions and global handler
├── mappers              # Entity ↔ DTO mapping
├── model                # JPA entities and enums
├── repository           # Spring Data JPA repositories
├── security             # JWT utilities and filters
├── service              # Business logic layer
└── TestingApplication   # Application entry point
```

## Domain Model Overview

- **Game** – represents a game with a specific genre
- **Player** – represents a user/player in the system
- **Session** – represents a game session connecting players to a game

Enums are used for:
- **Genre** – game categorization
- **Role** – security roles

## API Overview

The API exposes REST endpoints through the following controllers:

- `GameController` – game-related operations
- `PlayerController` – player-related operations
- `SessionController` – session lifecycle operations

All endpoints use DTOs for input and output, ensuring clean API boundaries.

## Security

- JWT-based authentication using a custom `JwtAuthenticationFilter`
- Token generation and validation handled via `JwtUtils`
- User details managed through a custom `UserDetailsImpl`
- Stateless authentication suitable for scalable backend services

## Getting Started

### Prerequisites

- Java 17 or newer
- Maven 3.8+

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start using Spring Boot’s default configuration.

## Error Handling

The project includes:

- Custom exceptions (e.g., object not found)
- A global exception handler returning structured error responses

This ensures consistent and predictable API error behavior.

## Use Cases

- Backend service for multiplayer or session-based games
- Practice project for Spring Boot REST, Security, and JPA
- Foundation for extending with matchmaking, scoring, or real-time features

## Possible Extensions

- Persistent database configuration (PostgreSQL, MySQL, etc.)
- API documentation (Swagger / OpenAPI)
- WebSocket or real-time session updates
- Role-based access control per endpoint
