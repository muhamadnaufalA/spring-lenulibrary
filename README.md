# spring-lenulibrary
this is a Spring Boot project created for the internship selection process at PT LEN IOT. The project aims to demonstrate a simple application architecture using Spring Boot.


## project-structure
The project follows a typical Spring Boot application structure with the following main components:

- **Controller:** Contains classes annotated with @RestController that handle incoming HTTP requests, interact with the service layer, and return appropriate responses.

- **Service:** Consists of classes annotated with @Service that contain business logic. These classes interact with repositories to perform CRUD operations.

- **Repository:** Classes annotated with @Repository responsible for interacting with the database or any data source. They provide methods to perform CRUD operations on entities.

- **Model:** Represents data objects or entities used by the application. These are typically annotated with @Entity and mapped to database tables.


## Depedencies
The project uses the following key dependencies:
- **Spring Web:** For building RESTful web services using Spring MVC.
- **Spring Data JPA:** Provides easy integration with JPA (Java Persistence API) and simplifies the implementation of  the data access layer.
- **PostgreSQL Driver:** JDBC driver for PostgreSQL to connect and interact with the PostgreSQL database.


## Running the Application
To run the Spring Lenulibrary project locally with PostgreSQL database, follow these steps:
1. Clone the repository to your local machine:
    ```bash
    git clone https://github.com/your-username/spring-lenulibrary.git
    ```
2. Navigate to the project directory:
    ```bash
    cd spring-lenulibrary
    ```
3. Open the application.properties file located in src/main/resources directory, and configure the PostgreSQL database connection properties:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.datasource.driver-class-name=org.postgresql.Driver
    ```
4. Build the project using Maven:
   ```bash
    mvn clean install
   ```
5. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
6. Access the application in your web browser at http://localhost:8080.

