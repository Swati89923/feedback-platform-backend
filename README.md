# 📢 Feedback Platform Backend

A **Spring Boot-based backend application** for managing user feedback.
This project provides REST APIs for user management, feedback handling, authentication, and profile management.

---

## 🚀 Features

* 👤 User Registration & Authentication
* 🔐 Secure APIs with Spring Security
* 📝 Feedback submission & management
* 📦 Layered architecture (Controller → Service → Repository)
* 📄 DTO-based request/response handling
* ⚠️ Centralized exception handling
* 🖼️ User profile update with avatar support

---

## 🛠️ Tech Stack

* **Java**
* **Spring Boot**
* **Spring Security**
* **Spring Data JPA**
* **Maven**
* **MySQL / H2 (configurable)**

---

## 📁 Project Structure

```
src/main/java/com/feedback/feedbackplatform
│
├── config        # Configuration classes (WebConfig, etc.)
├── controller    # REST Controllers
├── dto           # Data Transfer Objects
├── exception     # Custom exception handling
├── model         # Entity classes
├── repository    # JPA repositories
├── security      # Security configurations
├── service       # Business logic layer
└── FeedbackplatformApplication.java
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the repository

```bash
git clone https://github.com/Swati89923/feedback-platform-backend.git
cd feedback-platform-backend
```

### 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/feedback_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3️⃣ Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

Server will be start at:

```
http://localhost:8080
```

---

## 🔐 API Endpoints (Sample)

### 👤 User

* `POST /api/users/register` → Register user
* `POST /api/users/login` → Login user
* `GET /api/users/profile` → Get user profile
* `PUT /api/users/profile` → Update profile

### 📝 Feedback

* `POST /api/feedback` → Submit feedback
* `GET /api/feedback` → Get all feedback
* `DELETE /api/feedback/{id}` → Delete feedback

---

## 🔒 Security

* Uses **Spring Security**
* Authentication & authorization configured in `security/`
* Passwords encrypted using secure hashing

---

## ⚠️ Exception Handling

* Centralized error handling using custom exceptions
* Clean and consistent API error responses

---

## 📌 Future Improvements

* JWT-based authentication
* Role-based access control
* API documentation (Swagger)
* Docker support
* Pagination & filtering for feedback

---

## 🤝 Contributing

Contributions are welcome!
Feel free to fork the repo and submit a pull request.

---

## 📜 License

This project is licensed under the **MIT License**.

---

## 💡 Author

Developed by **Swati** ✨

---
