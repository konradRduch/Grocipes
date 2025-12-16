# API Documentation 📡

## Table of Contents
1. [Overview](#overview)
2. [Authentication](#authentication)
3. [Authentication Endpoints](#authentication-endpoints)
4. [Product Endpoints](#product-endpoints)
5. [Recipe Endpoints](#recipe-endpoints)
6. [Shopping List Endpoints](#shopping-list-endpoints)
7. [Nutritional Goal Endpoints](#nutritional-goal-endpoints)
8. [User Data Endpoints](#user-data-endpoints)
9. [Nutrition Schedule Endpoints](#nutrition-schedule-endpoints)
10. [Error Handling](#error-handling)
11. [Data Models](#data-models)

---

## Overview

The Grocipes REST API provides access to all application features including user authentication, product management, recipe creation, shopping lists, and nutritional tracking.

### Base URL

```
http://localhost:8080
```

### Response Format

All responses are in JSON format.

### Date Format

All dates follow ISO 8601 format: `YYYY-MM-DDTHH:mm:ss`

---

## Authentication

### JWT Token Authentication

Most endpoints require JWT (JSON Web Token) authentication. After successful login or registration, you'll receive a token that must be included in subsequent requests.

#### How to Use the Token

Include the token in the `Authorization` header:

```http
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwiaWF0IjoxNzA...
```

#### Token Expiration

Tokens are valid for a limited time. If you receive a 401 Unauthorized response, you'll need to re-authenticate.

---

## Authentication Endpoints

### 1. Register New User

Create a new user account.

**Endpoint:** `POST /auth/register`

**Authentication:** Not required

**Request Body:**

```json
{
  "email": "user@example.com",
  "password": "SecurePass123!",
  "name": "John",
  "surname": "Doe",
  "birthday": "1990-05-15",
  "gender": "MALE"
}
```

**Response (200 OK):**

```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwiaWF0IjoxNzA..."
}
```

**Response Codes:**
- `200 OK` - User successfully registered
- `400 BAD REQUEST` - Email already exists

---

### 2. Login

Authenticate existing user.

**Endpoint:** `POST /auth/login`

**Authentication:** Not required

**Request Body:**

```json
{
  "email": "user@example.com",
  "password": "SecurePass123!"
}
```

**Response (200 OK):**

```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwiaWF0IjoxNzA..."
}
```

**Response Codes:**
- `200 OK` - Login successful
- `401 UNAUTHORIZED` - Invalid credentials

---

## Product Endpoints

### 3. Get All Products

Retrieve all available products with nutritional information.

**Endpoint:** `GET /products`

**Authentication:** Required

**Request Headers:**

```http
Authorization: Bearer <your-jwt-token>
```

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "name": "Chicken Breast",
    "weight": 100.0,
    "price": 5.99,
    "image_url": "/images/chicken-breast.jpg",
    "calories": 165,
    "unitId": 1,
    "unitName": "grams",
    "nutritionFacts": [
      {
        "nutrientId": 1,
        "nutrientName": "Protein",
        "amount": 31.0,
        "unitName": "g"
      },
      {
        "nutrientId": 2,
        "nutrientName": "Fat",
        "amount": 3.6,
        "unitName": "g"
      }
    ]
  }
]
```

---

### 4. Get Product by ID

Retrieve a specific product by its ID.

**Endpoint:** `GET /products/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Product ID

**Response (200 OK):**

```json
{
  "id": 1,
  "name": "Chicken Breast",
  "weight": 100.0,
  "price": 5.99,
  "image_url": "/images/chicken-breast.jpg",
  "calories": 165,
  "unitId": 1,
  "unitName": "grams",
  "nutritionFacts": [
    {
      "nutrientId": 1,
      "nutrientName": "Protein",
      "amount": 31.0,
      "unitName": "g"
    }
  ]
}
```

---

### 5. Add New Product

Create a new product (Admin only).

**Endpoint:** `POST /products/add`

**Authentication:** Required (Admin role)

**Request Body:**

```json
{
  "productDTO": {
    "name": "Greek Yogurt",
    "weight": 100.0,
    "price": 3.49,
    "image_url": "/images/greek-yogurt.jpg",
    "calories": 59,
    "unitId": 1
  },
  "nutritionFactNutrientDTO": [
    {
      "nutrientId": 1,
      "amount": 10.0
    },
    {
      "nutrientId": 2,
      "amount": 0.4
    },
    {
      "nutrientId": 3,
      "amount": 3.6
    }
  ]
}
```

**Response Codes:**
- `200 OK` - Product added successfully
- `400 BAD REQUEST` - Invalid data
- `403 FORBIDDEN` - Not authorized (user is not admin)

---

### 6. Update Product

Edit an existing product (Admin only).

**Endpoint:** `PATCH /products/edit/{id}`

**Authentication:** Required (Admin role)

**Path Parameters:**
- `id` (Integer) - Product ID

**Request Body:** Same as Add New Product

**Response Codes:**
- `200 OK` - Product updated successfully
- `404 NOT FOUND` - Product not found

---

### 7. Delete Product

Remove a product (Admin only).

**Endpoint:** `DELETE /products/delete/{id}`

**Authentication:** Required (Admin role)

**Path Parameters:**
- `id` (Integer) - Product ID

**Response Codes:**
- `200 OK` - Product deleted successfully
- `404 NOT FOUND` - Product not found

---

## Recipe Endpoints

### 8. Get All Recipes

Retrieve all recipes with their nutritional information.

**Endpoint:** `GET /recipes`

**Authentication:** Required

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "title": "Grilled Chicken Salad",
    "description": "Healthy protein-packed salad",
    "preparation_method": "1. Grill chicken\n2. Chop vegetables\n3. Mix ingredients",
    "image_url": "/images/chicken-salad.jpg",
    "typeOfMeal": "LUNCH",
    "calories": 350,
    "protein": 42.0,
    "fat": 12.0,
    "carbohydrates": 18.0,
    "products": [
      {
        "productId": 1,
        "productName": "Chicken Breast",
        "amount": 150.0,
        "unitName": "grams"
      },
      {
        "productId": 5,
        "productName": "Lettuce",
        "amount": 100.0,
        "unitName": "grams"
      }
    ]
  }
]
```

---

### 9. Get Recipe by ID

Retrieve a specific recipe.

**Endpoint:** `GET /recipes/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Recipe ID

**Response:** Same structure as Get All Recipes (single object)

---

### 10. Add New Recipe

Create a new recipe.

**Endpoint:** `POST /recipes/add`

**Authentication:** Required

**Request Body:**

```json
{
  "recipe": {
    "title": "Protein Smoothie",
    "description": "Post-workout recovery smoothie",
    "preparation_method": "1. Add all ingredients to blender\n2. Blend until smooth\n3. Serve cold",
    "image_url": "/images/protein-smoothie.jpg",
    "typeOfMeal": "SNACK"
  },
  "products": [
    {
      "product_id": 15,
      "amount": 30.0,
      "unit_id": 1
    },
    {
      "product_id": 20,
      "amount": 250.0,
      "unit_id": 2
    }
  ]
}
```

**Type of Meal Options:**
- `BREAKFAST`
- `LUNCH`
- `DINNER`
- `SNACK`

**Response Codes:**
- `200 OK` - Recipe created successfully
- `400 BAD REQUEST` - Invalid data

---

### 11. Update Recipe

Edit an existing recipe.

**Endpoint:** `PATCH /recipes/edit/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Recipe ID

**Request Body:** Same as Add New Recipe

**Response Codes:**
- `200 OK` - Recipe updated successfully
- `404 NOT FOUND` - Recipe not found

---

### 12. Delete Recipe

Remove a recipe.

**Endpoint:** `DELETE /recipes/delete/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Recipe ID

**Response Codes:**
- `200 OK` - Recipe deleted successfully
- `404 NOT FOUND` - Recipe not found

---

## Shopping List Endpoints

### 13. Get User Shopping Schedules

Retrieve all shopping schedules for the authenticated user.

**Endpoint:** `GET /shoppingList/getUserShoppingSchedules`

**Authentication:** Required

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "name": "John shopping schedule",
    "userId": 5,
    "shoppingLists": [
      {
        "id": 10,
        "name": "Weekly Groceries",
        "shopping_date": "2025-12-20T00:00:00",
        "cardColor": "#4CAF50",
        "likedList": true,
        "products": [
          {
            "id": 1,
            "productId": 5,
            "productName": "Eggs",
            "amount": 12.0,
            "unitName": "pieces",
            "bought": false
          }
        ]
      }
    ]
  }
]
```

---

### 14. Get Shopping List by ID

Retrieve a specific shopping list.

**Endpoint:** `GET /shoppingList/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Shopping List ID

**Response (200 OK):**

```json
{
  "id": 10,
  "name": "Weekly Groceries",
  "shopping_date": "2025-12-20T00:00:00",
  "cardColor": "#4CAF50",
  "likedList": true,
  "products": [
    {
      "id": 1,
      "productId": 5,
      "productName": "Eggs",
      "amount": 12.0,
      "unitName": "pieces",
      "bought": false
    }
  ]
}
```

---

### 15. Add Shopping List

Create a new shopping list.

**Endpoint:** `POST /shoppingList/add`

**Authentication:** Required

**Request Body:**

```json
{
  "name": "Weekend Shopping",
  "shopping_date": "2025-12-22T10:00:00",
  "cardColor": "#FF5722",
  "likedList": false,
  "productShoppingLists": [
    {
      "product_id": 8,
      "amount": 500.0,
      "unit_id": 1,
      "bought": false
    },
    {
      "product_id": 12,
      "amount": 2.0,
      "unit_id": 3,
      "bought": false
    }
  ]
}
```

**Response Codes:**
- `200 OK` - Shopping list created successfully
- `400 BAD REQUEST` - Invalid data

---

### 16. Delete Shopping List

Remove a shopping list.

**Endpoint:** `DELETE /shoppingList/delete/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Shopping List ID

**Response Codes:**
- `200 OK` - Shopping list deleted successfully
- `404 NOT FOUND` - Shopping list not found

---

## Nutritional Goal Endpoints

### 17. Get All Nutritional Goals

Retrieve all nutritional goals for the authenticated user.

**Endpoint:** `GET /nutritionalGoal/getAll`

**Authentication:** Required

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "name": "Weight Loss Goal",
    "typeOfGoal": "WEIGHT_LOSS",
    "targetWeight": 75.0,
    "startDate": "2025-01-01T00:00:00",
    "endDate": "2025-06-01T00:00:00",
    "dailyCalories": 1800,
    "protein": 135,
    "carbohydrates": 180,
    "fat": 60,
    "activityLevel": "MODERATE",
    "estimatedDays": 150
  }
]
```

---

### 18. Get Nutritional Goal by ID

Retrieve a specific nutritional goal.

**Endpoint:** `GET /nutritionalGoal/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Nutritional Goal ID

**Response:** Same structure as Get All Nutritional Goals (single object)

---

### 19. Add Nutritional Goal

Create a new nutritional goal.

**Endpoint:** `POST /nutritionalGoal/add`

**Authentication:** Required

**Request Body:**

```json
{
  "name": "Muscle Gain Program",
  "typeOfGoal": "MUSCLE_GAIN",
  "targetWeight": 85.0,
  "startDate": "2025-01-01T00:00:00",
  "activityLevel": "VERY_ACTIVE"
}
```

**Type of Goal Options:**
- `WEIGHT_LOSS`
- `WEIGHT_GAIN`
- `MUSCLE_GAIN`
- `MAINTENANCE`

**Activity Level Options:**
- `SEDENTARY` - Little or no exercise
- `LIGHTLY_ACTIVE` - Light exercise 1-3 days/week
- `MODERATE` - Moderate exercise 3-5 days/week
- `VERY_ACTIVE` - Hard exercise 6-7 days/week
- `EXTREMELY_ACTIVE` - Very hard exercise & physical job

**Response Codes:**
- `200 OK` - Goal created successfully (includes calculated daily requirements)
- `400 BAD REQUEST` - Invalid data

---

### 20. Estimate Time to Achieve Goal

Calculate estimated time to reach target weight.

**Endpoint:** `POST /nutritionalGoal/getEstimatedTimeToAchieveGoal`

**Authentication:** Required

**Request Body:**

```json
{
  "currentWeight": 90.0,
  "targetWeight": 75.0,
  "typeOfGoal": "WEIGHT_LOSS"
}
```

**Response (200 OK):**

```json
150.0
```

*Returns estimated days to achieve goal*

---

### 21. Get Daily Demand

Get calculated daily nutritional requirements based on active goal.

**Endpoint:** `GET /nutritionalGoal/getGoalDailyDemand`

**Authentication:** Required

**Response (200 OK):**

```json
{
  "calories": 1800,
  "protein": 135,
  "carbohydrates": 180,
  "fat": 60
}
```

---

### 22. Delete Nutritional Goal

Remove a nutritional goal.

**Endpoint:** `DELETE /nutritionalGoal/delete/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Nutritional Goal ID

**Response Codes:**
- `200 OK` - Goal deleted successfully
- `404 NOT FOUND` - Goal not found

---

## User Data Endpoints

### 23. Get User Profile

Retrieve authenticated user's profile information.

**Endpoint:** `GET /userData/getUser`

**Authentication:** Required

**Response (200 OK):**

```json
{
  "id": 5,
  "email": "user@example.com",
  "name": "John",
  "surname": "Doe",
  "birthday": "1990-05-15",
  "gender": "MALE",
  "roles": ["USER"]
}
```

---

### 24. Add Body Measurement

Log a new body measurement entry.

**Endpoint:** `POST /userData/add/bodyMeasurment`

**Authentication:** Required

**Request Body:**

```json
{
  "weight": 82.5,
  "waist": 85.0,
  "chest": 100.0,
  "hips": 95.0,
  "height": 180.0
}
```

*Note: `measurement_date` is automatically set to current timestamp*

**Response Codes:**
- `200 OK` - Measurement added successfully
- `400 BAD REQUEST` - Invalid data

---

### 25. Get All Body Measurements

Retrieve all body measurements for the authenticated user.

**Endpoint:** `GET /userData/getAll/bodyMeasurment`

**Authentication:** Required

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "userId": 5,
    "weight": 82.5,
    "waist": 85.0,
    "chest": 100.0,
    "hips": 95.0,
    "height": 180.0,
    "measurement_date": "2025-12-16T10:30:00"
  },
  {
    "id": 2,
    "userId": 5,
    "weight": 81.0,
    "waist": 84.0,
    "chest": 100.0,
    "hips": 94.0,
    "height": 180.0,
    "measurement_date": "2025-12-09T10:15:00"
  }
]
```

---

### 26. Get User Profile Info

Get comprehensive profile information including BMI and latest measurements.

**Endpoint:** `GET /userData/getUserProfileInfo`

**Authentication:** Required

**Response (200 OK):**

```json
{
  "name": "John",
  "surname": "Doe",
  "email": "user@example.com",
  "birthday": "1990-05-15",
  "gender": "MALE",
  "age": 35,
  "currentWeight": 82.5,
  "height": 180.0,
  "bmi": 25.46,
  "bmiCategory": "OVERWEIGHT",
  "latestMeasurement": {
    "weight": 82.5,
    "waist": 85.0,
    "chest": 100.0,
    "hips": 95.0,
    "measurement_date": "2025-12-16T10:30:00"
  }
}
```

**BMI Categories:**
- `UNDERWEIGHT` - BMI < 18.5
- `NORMAL` - BMI 18.5-24.9
- `OVERWEIGHT` - BMI 25-29.9
- `OBESE` - BMI ≥ 30

---

## Nutrition Schedule Endpoints

### 27. Get All Eat Deadlines

Retrieve all scheduled meals.

**Endpoint:** `GET /eatDeadline/getAllEatDeadline`

**Authentication:** Required

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "recipeId": 5,
    "recipeTitle": "Grilled Chicken Salad",
    "nutritionScheduleId": 1,
    "eatDate": "2025-12-17T12:00:00",
    "eaten": true,
    "rate": 4,
    "comment": "Delicious and filling!"
  }
]
```

---

### 28. Get Eat Deadline by ID

Retrieve a specific scheduled meal.

**Endpoint:** `GET /eatDeadline/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Eat Deadline ID

**Response:** Same structure as Get All Eat Deadlines (single object)

---

### 29. Add Eat Deadline

Schedule a meal.

**Endpoint:** `POST /eatDeadline/add`

**Authentication:** Required

**Request Body:**

```json
{
  "recipeId": 5,
  "nutritionScheduleId": 1,
  "eatDate": "2025-12-18T12:00:00"
}
```

**Response Codes:**
- `200 OK` - Meal scheduled successfully
- `400 BAD REQUEST` - Invalid data

---

### 30. Update Meal Comment

Add or update comment for a completed meal.

**Endpoint:** `PUT /eatDeadline/comment/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Eat Deadline ID

**Request Body:**

```json
{
  "comment": "Great taste, will make again!"
}
```

**Response Codes:**
- `200 OK` - Comment updated successfully
- `404 NOT FOUND` - Meal not found

---

### 31. Rate Meal

Rate a completed meal.

**Endpoint:** `PUT /eatDeadline/rate/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Eat Deadline ID

**Request Body:**

```json
{
  "rate": 5,
  "eaten": true
}
```

**Rate Range:** 1-5 (5 being the best)

**Response Codes:**
- `200 OK` - Rating updated successfully
- `404 NOT FOUND` - Meal not found

---

### 32. Delete Eat Deadline

Remove a scheduled meal.

**Endpoint:** `DELETE /eatDeadline/delete/{id}`

**Authentication:** Required

**Path Parameters:**
- `id` (Integer) - Eat Deadline ID

**Response Codes:**
- `200 OK` - Meal deleted successfully
- `404 NOT FOUND` - Meal not found

---

## Error Handling

### Error Response Format

All errors follow this structure:

```json
{
  "timestamp": "2025-12-16T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/products/add"
}
```

### Common HTTP Status Codes

| Code | Description | Common Causes |
|------|-------------|---------------|
| 200 | OK | Request succeeded |
| 400 | Bad Request | Invalid request data, missing required fields |
| 401 | Unauthorized | Missing or invalid JWT token |
| 403 | Forbidden | Insufficient permissions (e.g., non-admin trying admin action) |
| 404 | Not Found | Resource doesn't exist |
| 500 | Internal Server Error | Server-side error |

### Authentication Errors

```json
{
  "timestamp": "2025-12-16T10:30:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Token not found or invalid",
  "path": "/products"
}
```

### Validation Errors

```json
{
  "timestamp": "2025-12-16T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email already exists",
  "path": "/auth/register"
}
```

---

## Data Models

### User Entity

```typescript
{
  id: number;
  email: string;
  name: string;
  surname: string;
  birthday: string; // ISO 8601 date
  gender: "MALE" | "FEMALE" | "OTHER";
  roles: string[]; // ["USER", "ADMIN"]
}
```

### Product

```typescript
{
  id: number;
  name: string;
  weight: number;
  price: number;
  image_url: string;
  calories: number;
  unitId: number;
  unitName: string;
  nutritionFacts: NutritionFact[];
}
```

### Recipe

```typescript
{
  id: number;
  title: string;
  description: string;
  preparation_method: string;
  image_url: string;
  typeOfMeal: "BREAKFAST" | "LUNCH" | "DINNER" | "SNACK";
  calories: number;
  protein: number;
  fat: number;
  carbohydrates: number;
  products: RecipeProduct[];
}
```

### Shopping List

```typescript
{
  id: number;
  name: string;
  shopping_date: string; // ISO 8601 datetime
  cardColor: string; // Hex color code
  likedList: boolean;
  products: ProductShoppingList[];
}
```

### Nutritional Goal

```typescript
{
  id: number;
  name: string;
  typeOfGoal: "WEIGHT_LOSS" | "WEIGHT_GAIN" | "MUSCLE_GAIN" | "MAINTENANCE";
  targetWeight: number;
  startDate: string; // ISO 8601 datetime
  endDate: string; // ISO 8601 datetime
  dailyCalories: number;
  protein: number;
  carbohydrates: number;
  fat: number;
  activityLevel: "SEDENTARY" | "LIGHTLY_ACTIVE" | "MODERATE" | "VERY_ACTIVE" | "EXTREMELY_ACTIVE";
  estimatedDays: number;
}
```

### Body Measurement

```typescript
{
  id: number;
  userId: number;
  weight: number; // kg
  waist: number; // cm
  chest: number; // cm
  hips: number; // cm
  height: number; // cm
  measurement_date: string; // ISO 8601 datetime
}
```

---

## Rate Limiting

Currently, no rate limiting is implemented. Best practices recommend:
- Max 100 requests per minute per user
- Max 1000 requests per hour per user

---

## Changelog

### Version 1.0.0 (Current)
- Initial API release
- User authentication with JWT
- CRUD operations for products, recipes, shopping lists
- Nutritional goal tracking
- Body measurement logging
- Meal scheduling

---

## Support

For API support and questions:
- Check the [main README](../README.md)
- Open an issue on [GitHub](https://github.com/konradRduch/Grocipes/issues)
- Contact: See repository for contact information

---

**Last Updated:** December 16, 2025
