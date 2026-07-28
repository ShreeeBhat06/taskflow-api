## 🔑 Authentication

The API uses **JWT (JSON Web Token)** for secure authentication.

### Authentication Flow

1. Register a new user
2. Login with credentials
3. Receive a JWT token
4. Include the token in every protected request

```
Authorization: Bearer <your-jwt-token>
```

---

## 📚 API Endpoints

### Authentication

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and receive JWT |

### Tasks

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get task by ID |
| POST | `/api/tasks` | Create task |
| PUT | `/api/tasks/{id}` | Update task |
| DELETE | `/api/tasks/{id}` | Delete task |

---

## 📖 Swagger Documentation

Once the application is running, access the API documentation at:

```
http://localhost:8080/swagger-ui/index.html
```
