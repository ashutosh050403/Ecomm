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
       **Product Table Values**:

  INSERT INTO product (name, description, price, image_url, category) VALUES
-- 📌 Electronics
('Laptop', 'High-performance laptop for gaming and work', 75000, 'https://cdn.pixabay.com/photo/2020/10/21/18/07/laptop-5673901_1280.jpg', 'Electronics'),
('Apple MacBook', 'Lightweight and powerful MacBook with Retina Display', 95000, 'https://cdn.pixabay.com/photo/2016/11/29/05/08/apple-1867461_1280.jpg', 'Electronics'),
('Headphones', 'Wireless noise-cancelling headphones', 5000, 'https://cdn.pixabay.com/photo/2018/10/04/05/38/headphone-3722950_1280.jpg', 'Electronics'),

-- 📌 Clothing
('Men Shirts', 'Cotton casual shirts for men', 1999, 'https://cdn.pixabay.com/photo/2014/08/26/21/49/shirts-428618_640.jpg', 'Clothing'),
('Women Fashion', 'Trendy women outfits for all seasons', 2499, 'https://cdn.pixabay.com/photo/2017/01/14/10/03/fashion-1979136_640.jpg', 'Clothing'),
('Baby Shoes', 'Soft and comfortable baby shoes', 999, 'https://cdn.pixabay.com/photo/2017/09/13/18/06/babys-bootees-2746390_640.jpg', 'Clothing'),

-- 📌 Gadgets
('Studio Headset', 'High-quality audio headset for music production', 11999, 'https://cdn.pixabay.com/photo/2022/06/21/21/15/audio-7276511_640.jpg', 'Gadgets'),
('Nikon Camera', 'Professional DSLR camera with high-quality lens', 129999, 'https://cdn.pixabay.com/photo/2023/11/14/15/46/nikon-8388022_640.jpg', 'Gadgets'),
('Wireless Earphones', 'Compact and stylish wireless earphones', 3499, 'https://cdn.pixabay.com/photo/2020/09/24/14/51/earphones-5598952_640.jpg', 'Gadgets');

     **User Table Values**:
     INSERT INTO user (name, email, password, role) 
     VALUES (
     'Admin User', 
     'admin@shopify.com', 
     '$2y$10$6m58toBn5TwCQFWmrxIjEuNzYcodvmF6bqvMAXoouhjutYpp6JVNK', 
     'ROLE_ADMIN'
     );
     Password for admin:- admin123
     INSERT INTO user (name, email, password, role) 
     VALUES (
     'Customer User', 
     'customer@shopify.com', 
     '$2a$10$E.qA.f3fB0gQ3b4a/R5eTeuF.zJG.9dUnTq9kStx8/E3jH9d.PzCy', 
     'ROLE_CUSTOMER'
     );  
     Password for customer:-customer123
      
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
