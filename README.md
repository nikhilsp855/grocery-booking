Grocery Booking API
A Spring Boot-based REST API for managing and booking grocery items, designed with two roles: Admin and User.

Features
Admin:
Add, view, update, and delete grocery items.
Manage inventory levels.
User:
View available grocery items.
Book multiple grocery items in a single order.
Technology Stack
Backend: Spring Boot (Java)
Database: H2 (In-Memory Database)
Build Tool: Maven
Dependencies: JPA, Hibernate, H2, Lombok, Spring Validation
Setup Instructions
Clone the repository:

bash
Copy code
git clone https://github.com/nikhilsp855/grocery-booking.git
cd grocery-booking
Build the project:

bash
Copy code
mvn clean install
Run the application:

bash
Copy code
mvn spring-boot:run
Access the H2 console:

URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:grocerydb
Username: admin
Password: password
API Endpoints
1. Admin APIs
Add a Grocery Item
Method: POST
URL: /api/admin/groceries
Description: Add a new grocery item.
Request Body:
json
Copy code
{
  "name": "Apple",
  "price": 1.5,
  "inventory": 100
}
View All Grocery Items
Method: GET
URL: /api/admin/groceries
Description: Retrieve a list of all grocery items.
View a Grocery Item by ID
Method: GET
URL: /api/admin/groceries/{id}
Description: Retrieve details of a specific grocery item by ID.
Update a Grocery Item
Method: PUT
URL: /api/admin/groceries/{id}
Description: Update the details of a grocery item.
Request Body:
json
Copy code
{
  "name": "Updated Apple",
  "price": 1.8,
  "inventory": 90
}
Delete a Grocery Item
Method: DELETE
URL: /api/admin/groceries/{id}
Description: Remove a grocery item by ID.
2. User APIs
View Available Groceries
Method: GET
URL: /api/user/groceries
Description: Retrieve a list of all available grocery items.
Place an Order
Method: POST
URL: /api/user/orders
Description: Book multiple grocery items in a single order.
Request Body:
json
Copy code
{
  "userId": "user123",
  "orderItems": [
    {
      "groceryId": 1,
      "quantity": 2
    },
    {
      "groceryId": 2,
      "quantity": 1
    }
  ]
}
H2 Database Access
You can access the in-memory database to view tables and data:

H2 Console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:grocerydb
Username: admin
Password: password

Future Enhancements
Add user authentication and role-based access control.
Use a persistent database like MySQL/PostgreSQL for production.
