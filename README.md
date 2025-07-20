# E-commerce Application

This is a complete e-commerce application built with Spring Boot. It provides functionalities for user management, product catalog, and order processing. The application uses JWT for authentication and has role-based access control for different endpoints.

## Features

  * **User Authentication**:
      * User registration and login.
      * JWT-based authentication to secure the APIs.
  * **Product Management**:
      * Admins can add, update, and delete products.
      * Anyone can view the list of products and individual product details.
      * Products can be filtered by category.
  * **Order Management**:
      * Authenticated users can place orders.
      * Users can view their own order history.
      * Admins can view all orders.
  * **Role-Based Access Control**:
      * **ADMIN**: Full access to product and order management.
      * **CUSTOMER**: Can place orders and view their own orders.

## Technologies Used

  * **Java**: 21
  * **Framework**: Spring Boot 3.4.4
  * **Database**: MySQL
  * **Data Access**: Spring Data JPA
  * **Security**: Spring Security, JSON Web Tokens (JWT)
  * **Build Tool**: Maven

## Setup and Installation

### Prerequisites

  * JDK 21 or later
  * Maven 3.x
  * MySQL Server

### Configuration

1.  **Clone the repository**:

    ```bash
    git clone <repository-url>
    cd ecomm
    ```

2.  **Database Setup**:

      * Create a MySQL database named `ecommerce`.
      * Open `src/main/resources/application.properties` and update the database URL, username, and password:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
        spring.datasource.username=<your-mysql-username>
        spring.datasource.password=<your-mysql-password>
        ```

3.  **Run the application**:

    ```bash
    mvn spring-boot:run
    ```

    The application will start on port `8086`.

## API Endpoints

### Authentication

| Method | Endpoint         | Description          | Access  |
| ------ | ---------------- | -------------------- | ------- |
| POST   | `/users/register`  | Register a new user. | Public  |
| POST   | `/users/login`     | Login to get a token.| Public  |

### Products

| Method | Endpoint                | Description                            | Access             |
| ------ | ----------------------- | -------------------------------------- | ------------------ |
| GET    | `/products`             | Get all products (paginated).        | Public             |
| GET    | `/products/category/{category}` | Get products by category (paginated). | Public             |
| GET    | `/products/{id}`        | Get a product by its ID.               | Public             |
| POST   | `/products`             | Add a new product.                     | ADMIN              |
| DELETE | `/products/{id}`        | Delete a product by its ID.            | ADMIN              |

### Orders

| Method | Endpoint         | Description                       | Access              |
| ------ | ---------------- | --------------------------------- | ------------------- |
| POST   | `/orders/place`    | Place a new order.                | CUSTOMER, ADMIN     |
| GET    | `/orders/all-orders` | Get all orders in the system.     | ADMIN               |
| GET    | `/orders/user`     | Get all orders for the logged-in user. | CUSTOMER, ADMIN     |
