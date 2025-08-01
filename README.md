# books

This repository is a Spring Boot application for managing a collection of books

## Features

- **CRUD Operations:** Create, read, update, and delete books.
- **Search:** Search for books by title.
- **ISBN Validation:** Ensures ISBNs are unique for each book.
- **RESTful API:** Exposes endpoints to interact with the book data.

## Main Components

- **Book model:** Represents each book with fields for title, author, publication year (between 1700 and 2025), and ISBN.
- **BookService:** Handles business logic for book management, including validations and mapping between data transfer objects and entities.
- **BookRepo:** Spring Data JPA repository for accessing and querying book data.
- **BookController:** REST controller exposing endpoints at `/api/books` for managing books and searching by title.

## Instructions to build and run the application

1. **Clone the repository:**
   git clone https://github.com/karankharbanda/books.git

2. **Build and run the application:**
   Make sure you have Java (17+) and Maven installed.
   
   mvn spring-boot:run
   
   Or build the JAR and run it manually:
   
   mvn clean package
   
   java -jar target/books-*.jar

4. **API Endpoints:**

   - `POST /api/books` - Create a new book
   - `GET /api/books` - Get all books
   - `GET /api/books/{id}` - Get a book by ID
   - `PUT /api/books/{id}` - Update a book by ID
   - `DELETE /api/books/{id}` - Delete a book by ID
   - `GET /api/books/search?title={title}` - Search books by title

## Additional Notes about Design Decisions

- **Spring Boot & JPA:** The application uses Spring Boot for rapid development and Spring Data JPA for database interactions.
- **Exception Handling:** Custom exceptions like `ResourceNotFoundException` and data integrity violations are used for robust error management.
- **DTO Pattern:** Separates request/response objects from the entity to keep API contracts clean and flexible.
- **Modularity:** Service, controller, and repository layers are separated for maintainability and scalability.

