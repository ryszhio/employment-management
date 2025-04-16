# Employee Management API

## Overview
This is a RESTful API for managing employees, built using Spring Boot. It provides endpoints to create, retrieve, update, and delete employee records.

This project is just part of me practicing & learning Spring Boot. :)

## Base URL
#### For Local Machine
```
http://localhost:8080/api/emp
```

#### For Hosted Machine
```
http://(yourdomain or ip)/api/emp
e.g. https://rishabkarki.com.np/api/emp
```

## Endpoints

### 1. Create an Employee
**Endpoint:**
```
POST /create
```
**Request Body (JSON):**
```json
{
  "name": "John Doe",
  "department": "IT",
  "position": "MANAGER"
}
```
**Response:**
- `200 OK` with the created employee object.
- `400 Bad Request` if invalid data is provided.

---
### 2. Get All Employees
**Endpoint:**
```
GET /getAll
```
**Response:**
- `200 OK` with a list of all employees.

---
### 3. Get Employee by Properties
**Endpoint:**
```
GET /search?arg=value
```
**Request Parameter:**
- `arguments` (Object) - Property of employee.
- `value` (Value) - Value of Object.

**Response:**
- `200 OK` with employee details.
- `404 Not Found` if the employee does not exist.

***Example:***
*Query Example*:
```
GET http://localhost:8080/api/emp/search?name=John
```

*Output Example*:
```json
[
	{
		"id": 12,
		"name": "John Doe",
		"joinedDate": "2023-06-15",
		"salary": 150000.00,
		"position": "MANAGER",
		"department": "SALES"
	}
]
```

---
### 4. Update an Employee
**Endpoint:**
```
PUT /{id}
```
**Path Parameter:**
- `id` (Integer) - The ID of the employee.

**Request Body (JSON):**
```json
{
  "name": "Jane Doe",
  "department": "SALES",
  "position": "SENIOR_MANAGER"
}
```
**Response:**
- `200 OK` with updated employee details.
- `404 Not Found` if the employee does not exist.

---
### 5. Delete an Employee
**Endpoint:**
```
DELETE /{id}
```
**Path Parameter:**
- `id` (Integer) - The ID of the employee.

**Response:**
- `204 No Content` if successful.
- `404 Not Found` if the employee does not exist.

## Package Used
- Spring Boot
- Spring Security
- Spring Data JPA

## Database
- PostgreSQL

## Setup Instructions
1. Clone the repository.
2. Configure database settings in `application.properties`.
3. Run the application using:
   ```
   mvn spring-boot:run
   ```
4. Use Postman or any API testing tool to interact with the endpoints.
