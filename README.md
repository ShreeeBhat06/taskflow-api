# TaskFlow API
RESTful Task Management API built with Java, Spring Boot 3, Spring Security, JWT, and MySQL.

[![Swagger UI](https://img.shields.io/badge/Swagger-UI-green)](https://taskflow-api-production-57b6.up.railway.app/swagger-ui/index.html)
[![Live](https://img.shields.io/badge/Live-Railway-blueviolet)](https://taskflow-api-production-57b6.up.railway.app)

## Live API
Base URL: `https://taskflow-api-production-57b6.up.railway.app`

## Try it Live
1. Open [Swagger UI](https://taskflow-api-production-57b6.up.railway.app/swagger-ui/index.html)
2. Register a user via `POST /auth/register`
3. Login via `POST /auth/login` — copy the JWT token from the response
4. Click **Authorize 🔒** at the top → paste the token as `Bearer <token>`
5. Now you can try all task endpoints

## Tech Stack
- Java 17 + Spring Boot 3
- Spring Security + JWT Authentication
- MySQL + Spring Data JPA
- Swagger UI (API Documentation)
- Deployed on Railway