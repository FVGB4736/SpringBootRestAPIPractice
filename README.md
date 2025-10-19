# Spring Boot REST API Practice

A RESTful API built with Spring Boot, Spring Data JPA, and PostgreSQL, featuring book and author management.

## 🚀 Features

- RESTful API endpoints for managing books and authors
- PostgreSQL database integration
- Docker support for easy setup
- ModelMapper for DTO mapping
- Lombok for reducing boilerplate code

## 🛠️ Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose
- PostgreSQL (optional, can use Docker)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone [your-repository-url]
cd SpringBootRestAPIPractice
```

### 2. Database Setup with Docker

This project includes a `docker-compose.yml` file to easily set up a PostgreSQL database:

```bash
# Start PostgreSQL using Docker Compose
docker-compose up -d
```

This will start a PostgreSQL container with the following configuration:
- **Host**: localhost
- **Port**: 5432
- **Database**: practicedatabase
- **Username**: user
- **Password**: password

### 3. Application Configuration

The application is pre-configured to connect to the PostgreSQL database. The configuration can be found in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/practicedatabase
spring.datasource.username=user
spring.datasource.password=password
```

### 4. Build and Run

#### Using Maven Wrapper (recommended):

```bash
# Build the project
./mvnw clean package

# Run the application
./mvnw spring-boot:run
```

#### Or using installed Maven:

```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## 📚 API Endpoints

### Book Endpoints
- `GET /api/books` - Get all books
- `GET /api/books/{id}` - Get a book by ID
- `POST /api/books` - Create a new book
- `PUT /api/books/{id}` - Update a book
- `DELETE /api/books/{id}` - Delete a book

### Author Endpoints
- `GET /api/authors` - Get all authors
- `GET /api/authors/{id}` - Get an author by ID
- `POST /api/authors` - Create a new author
- `PUT /api/authors/{id}` - Update an author
- `DELETE /api/authors/{id}` - Delete an author

## 🛠️ Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - For database operations
- [PostgreSQL](https://www.postgresql.org/) - Database
- [Lombok](https://projectlombok.org/) - For reducing boilerplate code
- [ModelMapper](http://modelmapper.org/) - For object mapping
- [Maven](https://maven.apache.org/) - Dependency Management


By FVGB4736