# Project Tracker

A comprehensive project management system that allows tracking of projects, tasks, and developers. This application
provides a RESTful API for managing project resources.

## Features

- Project management (create, read, update, delete)
- Task management with assignment to projects
- Developer management and assignment to tasks
- Audit logging for tracking changes
- Pagination support for listing resources
- Swagger/OpenAPI documentation

## Technologies Used

- **Java 21**
- **Spring Boot 3.5.0**
- **Spring Data JPA** - For SQL database operations
- **Spring Data MongoDB** - For NoSQL database operations
- **PostgreSQL** - Primary database
- **MongoDB** - Secondary database for specific features
- **Swagger/OpenAPI** - API documentation
- **Maven** - Dependency management and build tool

## Prerequisites

- Java 21 or higher
- PostgreSQL 12 or higher
- MongoDB (optional, depending on features used)
- Maven 3.6 or higher

## Setup and Installation

1. **Clone the repository**

2. **Configure the databases**

    - PostgreSQL:
        - Create a database named `project_tracker`
        - Update the credentials in `application.properties` if needed

    - MongoDB (if needed):
        - Update the MongoDB URI in `application.properties`

3. **Build the application**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   The application will start on port 3000 by default.

## API Documentation

The API documentation is available via Swagger UI at:

```
http://localhost:3000/swagger-ui.html
```

API endpoints include:

- **Projects**
    - `POST /api/v1/projects` - Create a new project
    - `GET /api/v1/projects` - Get all projects (paginated)
    - `GET /api/v1/projects/{id}` - Get a project by ID
    - `PATCH /api/v1/projects/{id}` - Update a project
    - `DELETE /api/v1/projects/{id}` - Delete a project

- **Tasks**
    - `POST /api/v1/tasks` - Create a new task
    - `GET /api/v1/tasks` - Get all tasks (paginated)
    - `GET /api/v1/tasks/{id}` - Get a task by ID
    - `PATCH /api/v1/tasks/{id}` - Update a task
    - `DELETE /api/v1/tasks/{id}` - Delete a task

- **Developers**
    - `POST /api/v1/developers` - Create a new developer
    - `GET /api/v1/developers` - Get all developers (paginated)
    - `GET /api/v1/developers/{id}` - Get a developer by ID
    - `PATCH /api/v1/developers/{id}` - Update a developer
    - `DELETE /api/v1/developers/{id}` - Delete a developer

- **Audit Logs**
    - `GET /api/v1/logs` - Get all audit logs (paginated)

## Configuration

The application can be configured through the `application.properties` file:

```properties
# Server configuration
server.port=3000
# PostgreSQL Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/project_tracker
spring.datasource.username=postgres
spring.datasource.password=your_password
# MongoDB Configuration (if needed)
spring.data.mongodb.uri=your_mongodb_uri
# Swagger/OpenAPI Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## Usage Examples

### Creating a Project

```
POST /api/v1/projects
```

Request body:

```json
{
  "name": "Web Application",
  "description": "A new web application for client XYZ",
  "startDate": "2023-06-01",
  "endDate": "2023-12-31",
  "status": "IN_PROGRESS",
  "priority": "HIGH"
}
```

### Assigning a Task to a Developer

```
PATCH /api/v1/tasks/{taskId}
```

Request body:

```json
{
  "developerId": 123,
  "status": "IN_PROGRESS"
}
```