# 📝 Expense Tracker (Spring Boot + MongoDB + JWT)

A secure and feature-rich Expense Tracker REST API built with Spring Boot, MongoDB, and JWT-based authentication.

---

## 🚀 Features

- User Registration & Login with JWT authentication
- CRUD operations for expenses
- Filter expenses by Category and Date Range
- Dashboard summary with total and monthly expenses, category-wise breakdown, and recent expenses
- Export expenses as CSV file
- Each user accesses only their own data

---

## 🧑‍💻 Tech Stack

- Java 17
- Spring Boot
- MongoDB
- Spring Security
- JWT (JSON Web Token)
- Maven

---

## 📦 API Endpoints

### 🔐 Authentication

- POST /api/auth/register – Register a new user
- POST /api/auth/login – Login and receive JWT token

### 💸 Expense Management (JWT token required)

- POST /api/expenses – Create a new expense
- GET /api/expenses – Get all expenses for logged-in user
- PUT /api/expenses/{id} – Update an expense
- DELETE /api/expenses/{id} – Delete an expense

### 🔎 Filtering & Summary

- GET /api/expenses/filter/category?category=<category> – Filter expenses by category
- GET /api/expenses/filter/date?from=yyyy-MM-dd&to=yyyy-MM-dd – Filter by date range
- GET /api/expenses/dashboard – Get dashboard summary
- GET /api/expenses/export/csv – Export all expenses as CSV file

---

## 🛡️ Security

- Passwords hashed using BCrypt
- JWT tokens validated for each request
- Users can only access their own expenses

---

## 🧪 Testing the API

Use tools like:

- Postman
- curl
- REST Client extensions (e.g., VS Code)

Add JWT token in request header:

Authorization: Bearer <your_jwt_token_here>

---

## 📁 Project Structure

src  
└── main  
├── java  
│   └── com.example.expensetracker  
│       ├── config  
│       ├── controller  
│       ├── model  
│       ├── payload  
│       ├── repository  
│       ├── security  
│       └── service  
└── resources  
└── application.properties

---


