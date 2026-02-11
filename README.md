# Smart Billing System

A full-stack Smart Billing System built with **HTML/CSS/JavaScript + Java Spring Boot + MySQL**.

## Project Structure

- `smart-billing-frontend/` - static web UI pages and vanilla JS modules
- `smart-billing-backend/` - Spring Boot REST API with session auth and BCrypt password verification
- `database/` - MySQL schema and seed scripts

## Frontend Pages

- Landing (`index.html`)
- Login (`login.html`)
- Dashboard (`dashboard.html`)
- Customers (`customers.html`)
- Products (`products.html`)
- Billing (`billing.html`)
- Invoice (`invoice.html`)
- Reports (`reports.html`)

## Backend APIs

- `POST /api/login`
- `POST /api/logout`
- `GET/POST/PUT/DELETE /api/customers`
- `GET/POST/PUT/DELETE /api/products`
- `POST /api/bills`
- `GET /api/bills/{id}`
- `GET /api/bills/today`

## Setup

### 1) Database

Run:

```sql
SOURCE database/schema.sql;
SOURCE database/seed_data.sql;
```

### 2) Backend

```bash
cd smart-billing-backend
mvn spring-boot:run
```

### 3) Frontend

Serve `smart-billing-frontend` using any static server (Live Server, Python HTTP server, etc.).

Default backend URL in frontend is `http://localhost:8080/api`.

## Business Logic

When creating a bill:

- product stock is reduced
- customer total visits are incremented
- customer last visit is updated
- final amount is calculated as `amount - discount` (minimum 0)
