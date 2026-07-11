# CareerCompass

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![JWT](https://img.shields.io/badge/Auth-JWT-red)
![Railway](https://img.shields.io/badge/Deployment-Railway-purple)

A full-stack opportunity tracker built for placement season. CareerCompass helps students organize internships, hackathons, placements, certifications, deadlines, and resume versions in one secure application.

🌐 **Live Demo:** https://careercompass-production-c71a.up.railway.app/index.html

📂 **Repository:** https://github.com/Srihitha-29/CareerCompass

---

# Why I Built This

During placement season, students often apply to multiple internships, hackathons, and jobs simultaneously. Tracking application status, deadlines, and resume versions across spreadsheets and notes quickly becomes confusing.

CareerCompass provides a single dashboard where users can securely manage opportunities, monitor application progress, organize resumes, and never miss important deadlines.

---

# Features

- Secure JWT Authentication
  - User Registration & Login
  - BCrypt password hashing
  - Auto-login after registration
  - Stateless authentication using JWT

- Opportunity Management
  - Create, Update, Delete opportunities
  - Categories:
    - Internships
    - Placements
    - Hackathons
    - Certifications
    - Competitions
    - Workshops
  - Track application status:
    - Planned
    - Applied
    - Interview
    - Shortlisted
    - Selected
    - Rejected

- Dashboard
  - Total Opportunities
  - Applied Count
  - Pending Count
  - Shortlisted Count
  - Selected Count
  - Rejected Count
  - Personalized greeting

- Upcoming Deadlines
  - Automatically displays nearest deadlines

- Resume Vault
  - Store multiple resume versions
  - Example:
    - Backend Resume
    - Frontend Resume
    - Java Resume

- User Isolation
  - Users can only access their own opportunities and resumes.

---

# Tech Stack

## Backend

- Java 21
- Spring Boot 3.5
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT Authentication
- BCrypt Password Encoder
- Bean Validation
- Lombok

## Frontend

- HTML
- CSS
- JavaScript

Frontend files are served directly from Spring Boot's `static/` directory.

---

# Architecture

```
Controller
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
MySQL Database
```

Project Structure

```
src/main/java/com/srihitha/careercompass

├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── security
├── service
└── util
```

---

# Security

- BCrypt password hashing
- JWT Stateless Authentication
- Spring Security Filters
- DTO Validation
- Resource Ownership Validation
- Global Exception Handling

---

# Design Decisions

## DTO-Based API

Entities are never exposed directly.

Every request and response uses dedicated DTOs.

---

## Centralized Exception Handling

Implemented using:

- `@ControllerAdvice`
- `GlobalExceptionHandler`

Handles:

- EmailAlreadyExistsException
- InvalidCredentialsException
- ResourceNotFoundException
- AccessDeniedException

---

## User Ownership Validation

Every opportunity and resume is verified against the authenticated user before any read, update, or delete operation.

---

## Environment Variables

Production secrets are never stored inside the project.

Railway injects:

- MYSQLHOST
- MYSQLPORT
- MYSQLDATABASE
- MYSQLUSER
- MYSQLPASSWORD
- JWT_SECRET
- JWT_EXPIRATION

---

# REST API

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/users/register` | Register User |
| POST | `/api/users/login` | Login User |
| GET | `/api/dashboard` | Dashboard Statistics |
| GET | `/api/opportunities` | List Opportunities |
| POST | `/api/opportunities` | Create Opportunity |
| GET | `/api/opportunities/{id}` | View Opportunity |
| PUT | `/api/opportunities/{id}` | Update Opportunity |
| DELETE | `/api/opportunities/{id}` | Delete Opportunity |
| GET | `/api/resumes` | List Resumes |
| POST | `/api/resumes` | Add Resume |
| PUT | `/api/resumes/{id}` | Update Resume |
| DELETE | `/api/resumes/{id}` | Delete Resume |

All endpoints except Register and Login require:

```
Authorization: Bearer <JWT_TOKEN>
```

---

# Running Locally

1. Clone the repository

```
git clone https://github.com/Srihitha-29/CareerCompass.git
```

2. Configure MySQL.

3. Update `application.properties` or set environment variables.

4. Run

```
mvn spring-boot:run
```

5. Open

```
http://localhost:8080
```

---

# Deployment

The application is deployed on **Railway**.

Deployment includes:

- Spring Boot Backend
- MySQL Database
- Static Frontend
- JWT Authentication
- Environment Variable Configuration

---

# Screenshots

> Add screenshots of:

- Login Page
- Dashboard
- Opportunity Tracker
- Resume Vault

---

# What I Learned

Developing CareerCompass helped me strengthen my understanding of:

- Spring Boot Architecture
- REST API Design
- Spring Security
- JWT Authentication
- BCrypt Password Hashing
- JPA & Hibernate
- Layered Architecture
- DTO Pattern
- Exception Handling
- Railway Cloud Deployment
- Environment Variables
- Git & GitHub Workflow

---

# Future Enhancements

- Calendar View
- Email Deadline Reminders
- Resume File Upload
- Pagination
- Search & Filters
- Unit Testing (JUnit & Mockito)

---

# Author

**Savarapu Lakshmi Srihitha**

GitHub: https://github.com/Srihitha-29

LinkedIn: https://linkedin.com/in/srihitha-savarapu
