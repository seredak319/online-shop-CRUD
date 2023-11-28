# Project: Online Shop Management System

### Description
This project implements an Online Shop Management System, providing functionalities for managing clients, products, and orders. It adheres to the following requirements:

### Data Model

* Client
ID: Unique identifier for each client.
First Name: First name of the client. Required and cannot be null.
Last Name: Last name of the client. Required and cannot be null.
Email: Unique email address of the client. Required and cannot be null.
* Product
ID: Unique identifier for each product.
Name: Name of the product. Required and cannot be null.
Price: Price of the product. Required and cannot be null.
Stock Quantity: Quantity of the product available in the stock. Required and cannot be null.
Product Code: Unique code assigned to the product.
* Order
ID: Unique identifier for each order.
Client ID: References the ID of the client placing the order.
Product List: List of products included in the order.
* Order Status:
New: The order is newly created.
In Progress: The order is currently being processed.
Completed: The order has been successfully completed.

### CRUD Operations
The system supports CRUD operations for Clients, Products, and Orders, including methods for adding, removing, updating, and reading.

### Business Logic
The project includes the following business logic:

* Place a New Order: Ensures product availability in the warehouse and verifies client registration before processing.
* Update Order Status: Allows for the modification of the order status.
* Cancel Order: Returns products to the warehouse upon order cancellation.
### New Technologies Used
ORM: MyBatis
Documentation: Swagger
Database: H2

### How to Run the Application
Ensure you have Java and Maven installed.
Clone the repository.
Navigate to the project directory.
Run the application using mvn spring-boot:run.

### API Documentation
Access the Swagger UI for API documentation: [Swagger UI](http://localhost:8080/swagger-ui/index.html#/)

### H2 Database
Access the H2 Database Console: [H2 Database Console](http://localhost:8080/h2-console) 


The project includes comprehensive unit tests using JUnit and Mockito. Test coverage includes various scenarios to ensure the reliability of the implemented features.

### Notes
Feel free to explore and modify the project as needed. If you encounter any issues or have suggestions for improvement, please let me know!

Enjoy managing your online shop efficiently with this system!
