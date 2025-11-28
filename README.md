# Spring Resource Server v1

A Spring Boot application that implements both an OAuth2 Authorization Server and Resource Server, providing JWT-based authentication and role-based access control (RBAC) for protected resources.

## Disclaimer

As of 20th November, Spring Boot version 4.0.0 has been released. As such, old Spring security dependancies has been deprecated and moved to new dependancies for consolidation. As such, this project is under revision for API design related to the new dependancies. Should this project be compiled and build, it will not be successful.

## Overview

This project serves as a comprehensive OAuth2 resource server that:
- Acts as an OAuth2 Authorization Server for issuing JWT tokens
- Functions as a Resource Server to protect API endpoints
- Manages user registration and authentication
- Enforces role-based access control (USER and ADMIN roles)
- Stores user data in a SQL Server database

## Technologies

- **Spring Boot** 4.0.0
- **Spring Security** - Authentication and authorization framework
- **OAuth2 Resource Server** - JWT token validation and resource protection
- **OAuth2 Authorization Server** - Token issuance and OAuth2 flows
- **Spring Data JPA** - Database persistence layer
- **SQL Server** - Relational database for user storage
- **JWT (JSON Web Tokens)** - Stateless authentication mechanism
- **BCrypt** - Password hashing algorithm

## Features

- ✅ JWT-based authentication
- ✅ Role-based access control (USER, ADMIN)
- ✅ User registration endpoint
- ✅ OAuth2 Authorization Code flow support
- ✅ Protected resource endpoints with role-based restrictions
- ✅ SQL Server database integration
- ✅ RSA key pair generation for JWT signing

## Prerequisites

- Java 25
- Maven 3.x
- SQL Server (running on localhost:1433)
- Database: `userdtbs` (will be created automatically if using `ddl-auto=update`)

## Configuration

### Database Setup

The application is configured to connect to a SQL Server database. Update the connection details in `application.properties`:

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=userdtbs;encrypt=false
spring.datasource.username=ResourceServer
spring.datasource.password=JWTisWack
```

### Server Port

The application runs on port **8082** by default.

### OAuth2 Client Configuration

The authorization server is pre-configured with a demo client:
- **Client ID**: `demo-client`
- **Client Secret**: `secret`
- **Scopes**: `read`, `write`
- **Grant Types**: Authorization Code, Refresh Token
- **Redirect URI**: `http://localhost:8082/login/oauth2/code/demo-client`

## API Endpoints

### Public Endpoints

#### Register User
- **Endpoint**: `POST /register/user`
- **Authentication**: Not required
- **Description**: Creates a new user account with encrypted password
- **Request Body**:
  ```json
  {
    "userName": "string",
    "passWord": "string",
    "role": "USER" | "ADMIN"
  }
  ```
- **Response**: Returns the created user object (password is hashed)

#### Greetings (Public)
- **Endpoint**: `GET /greetings`
- **Authentication**: Not required
- **Description**: Returns a public greeting message
- **Response**: `"Welcome to the resource server!"`

### Protected Endpoints

#### User Greetings
- **Endpoint**: `GET /user/greetings`
- **Authentication**: Required (JWT token)
- **Authorization**: Requires `ROLE_USER`
- **Description**: Returns a personalized greeting for authenticated users
- **Response**: `"Welcome to your page!"`

#### Admin Greetings
- **Endpoint**: `GET /admin/greetings`
- **Authentication**: Required (JWT token)
- **Authorization**: Requires `ROLE_ADMIN`
- **Description**: Returns an administrator greeting
- **Response**: `"Welcome, Administrator!"`

### OAuth2 Authorization Server Endpoints

The following standard OAuth2 endpoints are available (configured via Spring Security OAuth2 Authorization Server):

- **Authorization Endpoint**: `GET /oauth2/authorize`
  - Initiates the OAuth2 authorization flow
  - Requires authentication

- **Token Endpoint**: `POST /oauth2/token`
  - Issues access tokens and refresh tokens
  - Requires client authentication

- **JWK Set Endpoint**: `GET /.well-known/jwks.json`
  - Returns the JSON Web Key Set (JWKS) for JWT verification
  - Public endpoint for token validation

- **Token Introspection Endpoint**: `POST /oauth2/introspect`
  - Validates and returns information about a token

- **Token Revocation Endpoint**: `POST /oauth2/revoke`
  - Revokes access or refresh tokens

## Security Configuration

### Role-Based Access Control

The application enforces the following security rules:

- `/home` and `/register/**` - Public access (no authentication required)
- `/admin/**` - Requires `ROLE_ADMIN`
- `/user/**` - Requires `ROLE_USER`
- All other endpoints - Require authentication

### JWT Configuration

- **Issuer URI**: `http://localhost:8082`
- **Authority Claim**: `role` (extracted from JWT claims)
- **Authority Prefix**: `ROLE_` (e.g., "ADMIN" becomes "ROLE_ADMIN")
- **Key Algorithm**: RSA (2048-bit keys)

## Project Structure

```
src/main/java/minhtranc6/Spring_Resource_Server_v1/
├── configs/
│   ├── AuthorizationServerConfig.java    # OAuth2 Authorization Server configuration
│   ├── JwksConfig.java                   # JWKS endpoint configuration
│   ├── JwtCustomizer.java                # JWT token customization
│   └── SecurityConfig.java               # Security filter chain and JWT configuration
├── controllers/
│   ├── GreetingsController.java          # Greeting endpoints
│   └── RegistrationController.java       # User registration endpoint
├── entities/
│   └── MyUser.java                       # User entity (JPA)
├── repositories/
│   └── UserDetailRepository.java         # User data access layer
├── services/
│   └── UserDetailService.java            # User details service for Spring Security
├── legacy/
│   ├── JwtAuthenticationFilter.java      # Legacy JWT filter (not in use)
│   └── JwtService.java                   # Legacy JWT service (not in use)
└── Jwks.java                             # RSA key pair generation utility
```

## Running the Application

1. **Start SQL Server** and ensure the database is accessible

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

   Or run the main class:
   ```bash
   java -jar target/Spring-Resource-Server-v1-0.0.1-SNAPSHOT.jar
   ```

4. **Access the application**:
   - Base URL: `http://localhost:8082`
   - OAuth2 Authorization: `http://localhost:8082/oauth2/authorize`
   - JWKS Endpoint: `http://localhost:8082/.well-known/jwks.json`

## Usage Examples

### Register a New User

```bash
curl -X POST http://localhost:8082/register/user \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "john_doe",
    "passWord": "password123",
    "role": "USER"
  }'
```

### Access Public Endpoint

```bash
curl http://localhost:8082/greetings
```

### Access Protected Endpoint (with JWT)

```bash
curl -X GET http://localhost:8082/user/greetings \
  -H "Authorization: Bearer <your-jwt-token>"
```

### Obtain OAuth2 Token

1. Navigate to: `http://localhost:8082/oauth2/authorize?client_id=demo-client&response_type=code&scope=read write&redirect_uri=http://localhost:8082/login/oauth2/code/demo-client`
2. Authenticate with your credentials
3. Exchange the authorization code for a token at `/oauth2/token`

## Database Schema

The `MyUser` entity maps to the `myuser` table with the following structure:

- `id` (Long, Primary Key, Auto-generated)
- `username` (String)
- `password` (String, BCrypt hashed)
- `role` (String, e.g., "USER", "ADMIN")

## Notes

- Passwords are automatically hashed using BCrypt before storage
- JWT tokens include role information in the `role` claim
- The authorization server generates RSA key pairs automatically on startup
- CSRF protection is disabled for OAuth2 endpoints
- The application uses JPA with `ddl-auto=update` for automatic schema management

## Development

### Building

```bash
mvn clean package
```

### Testing

```bash
mvn test
```

## License

This project is part of a Spring Security and OAuth2 learning resource.

## Author

minhtranc6

