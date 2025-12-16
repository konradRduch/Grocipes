# API Examples 🔌

This file contains practical examples of using the Grocipes API with different tools and programming languages.

---

## Table of Contents

1. [Getting Started](#getting-started)
2. [Authentication Examples](#authentication-examples)
3. [Product Examples](#product-examples)
4. [Recipe Examples](#recipe-examples)
5. [Shopping List Examples](#shopping-list-examples)
6. [Nutritional Goal Examples](#nutritional-goal-examples)
7. [Using Different Tools](#using-different-tools)
8. [Programming Language Examples](#programming-language-examples)

---

## Getting Started

**Base URL:**
```
http://localhost:8080
```

**Content Type:**
```
Content-Type: application/json
```

**Authentication:**
Most endpoints require a JWT token in the Authorization header:
```
Authorization: Bearer YOUR_JWT_TOKEN
```

---

## Authentication Examples

### Register a New User

**cURL:**
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "SecurePass123!",
    "name": "John",
    "surname": "Doe",
    "birthday": "1990-05-15",
    "gender": "MALE"
  }'
```

**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsImlhdCI6MTcwMzAwNjQwMCwiZXhwIjoxNzAzMDkyODAwfQ.signature"
}
```

**Save the token for subsequent requests!**

---

### Login

**cURL:**
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "SecurePass123!"
  }'
```

**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9..."
}
```

---

## Product Examples

### Get All Products

**cURL:**
```bash
curl -X GET http://localhost:8080/products \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response:**
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
      },
      {
        "nutrientId": 3,
        "nutrientName": "Carbohydrates",
        "amount": 0.0,
        "unitName": "g"
      }
    ]
  }
]
```

---

### Add a New Product (Admin)

**cURL:**
```bash
curl -X POST http://localhost:8080/products/add \
  -H "Authorization: Bearer YOUR_ADMIN_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
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
  }'
```

---

## Recipe Examples

### Get All Recipes

**cURL:**
```bash
curl -X GET http://localhost:8080/recipes \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response:**
```json
[
  {
    "id": 1,
    "title": "Grilled Chicken Salad",
    "description": "Healthy protein-packed salad perfect for lunch",
    "preparation_method": "1. Season and grill chicken\n2. Chop vegetables\n3. Mix all ingredients\n4. Drizzle with dressing",
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

### Create a Recipe

**cURL:**
```bash
curl -X POST http://localhost:8080/recipes/add \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
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
      },
      {
        "product_id": 25,
        "amount": 1.0,
        "unit_id": 3
      }
    ]
  }'
```

---

### Update a Recipe

**cURL:**
```bash
curl -X PATCH http://localhost:8080/recipes/edit/5 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "recipe": {
      "title": "Updated Protein Smoothie",
      "description": "Enhanced post-workout recovery smoothie with berries",
      "preparation_method": "1. Add protein powder to blender\n2. Add milk and banana\n3. Add frozen berries\n4. Blend until smooth\n5. Serve immediately",
      "image_url": "/images/berry-protein-smoothie.jpg",
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
      },
      {
        "product_id": 25,
        "amount": 1.0,
        "unit_id": 3
      },
      {
        "product_id": 30,
        "amount": 50.0,
        "unit_id": 1
      }
    ]
  }'
```

---

## Shopping List Examples

### Get User Shopping Schedules

**cURL:**
```bash
curl -X GET http://localhost:8080/shoppingList/getUserShoppingSchedules \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response:**
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
          },
          {
            "id": 2,
            "productId": 8,
            "productName": "Milk",
            "amount": 2.0,
            "unitName": "liters",
            "bought": true
          }
        ]
      }
    ]
  }
]
```

---

### Create a Shopping List

**cURL:**
```bash
curl -X POST http://localhost:8080/shoppingList/add \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
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
      },
      {
        "product_id": 20,
        "amount": 1.0,
        "unit_id": 4,
        "bought": false
      }
    ]
  }'
```

---

## Nutritional Goal Examples

### Get All Nutritional Goals

**cURL:**
```bash
curl -X GET http://localhost:8080/nutritionalGoal/getAll \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response:**
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

### Create a Nutritional Goal

**cURL:**
```bash
curl -X POST http://localhost:8080/nutritionalGoal/add \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Muscle Gain Program",
    "typeOfGoal": "MUSCLE_GAIN",
    "targetWeight": 85.0,
    "startDate": "2025-01-01T00:00:00",
    "activityLevel": "VERY_ACTIVE"
  }'
```

---

### Get Daily Nutritional Requirements

**cURL:**
```bash
curl -X GET http://localhost:8080/nutritionalGoal/getGoalDailyDemand \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Response:**
```json
{
  "calories": 2400,
  "protein": 180,
  "carbohydrates": 240,
  "fat": 80
}
```

---

## Using Different Tools

### Postman

1. **Create a new request**
   - Method: POST
   - URL: `http://localhost:8080/auth/login`

2. **Add Headers**
   ```
   Content-Type: application/json
   ```

3. **Add Body** (raw JSON)
   ```json
   {
     "email": "john.doe@example.com",
     "password": "SecurePass123!"
   }
   ```

4. **Send Request** and save the token

5. **For authenticated requests**
   - Add Header: `Authorization: Bearer YOUR_TOKEN`

---

### Thunder Client (VS Code)

1. **Install Thunder Client** extension

2. **Create New Request**
   - Click "New Request"
   - Enter URL and method

3. **Set Headers**
   ```
   Content-Type: application/json
   Authorization: Bearer YOUR_TOKEN
   ```

4. **Add Body** (JSON)

5. **Send**

---

### HTTPie

**Install:**
```bash
pip install httpie
```

**Register:**
```bash
http POST http://localhost:8080/auth/register \
  email=john.doe@example.com \
  password=SecurePass123! \
  name=John \
  surname=Doe \
  birthday=1990-05-15 \
  gender=MALE
```

**Get Products:**
```bash
http GET http://localhost:8080/products \
  "Authorization:Bearer YOUR_JWT_TOKEN"
```

---

## Programming Language Examples

### JavaScript (Fetch API)

```javascript
// Login
async function login() {
  const response = await fetch('http://localhost:8080/auth/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      email: 'john.doe@example.com',
      password: 'SecurePass123!'
    })
  });
  
  const data = await response.json();
  const token = data.accessToken;
  localStorage.setItem('token', token);
  return token;
}

// Get Products
async function getProducts() {
  const token = localStorage.getItem('token');
  
  const response = await fetch('http://localhost:8080/products', {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  });
  
  const products = await response.json();
  return products;
}

// Create Recipe
async function createRecipe(recipeData) {
  const token = localStorage.getItem('token');
  
  const response = await fetch('http://localhost:8080/recipes/add', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify(recipeData)
  });
  
  return response.ok;
}
```

---

### Python (Requests)

```python
import requests

BASE_URL = "http://localhost:8080"

# Login
def login(email, password):
    response = requests.post(
        f"{BASE_URL}/auth/login",
        json={
            "email": email,
            "password": password
        }
    )
    data = response.json()
    return data['accessToken']

# Get Products
def get_products(token):
    response = requests.get(
        f"{BASE_URL}/products",
        headers={
            "Authorization": f"Bearer {token}"
        }
    )
    return response.json()

# Create Recipe
def create_recipe(token, recipe_data):
    response = requests.post(
        f"{BASE_URL}/recipes/add",
        headers={
            "Authorization": f"Bearer {token}",
            "Content-Type": "application/json"
        },
        json=recipe_data
    )
    return response.status_code == 200

# Example usage
if __name__ == "__main__":
    # Login
    token = login("john.doe@example.com", "SecurePass123!")
    print(f"Token: {token[:20]}...")
    
    # Get products
    products = get_products(token)
    print(f"Found {len(products)} products")
    
    # Create recipe
    recipe = {
        "recipe": {
            "title": "Test Recipe",
            "description": "A test recipe",
            "preparation_method": "Mix and serve",
            "typeOfMeal": "LUNCH"
        },
        "products": [
            {
                "product_id": 1,
                "amount": 150.0,
                "unit_id": 1
            }
        ]
    }
    success = create_recipe(token, recipe)
    print(f"Recipe created: {success}")
```

---

### Java (Spring RestTemplate)

```java
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class GrocipesApiClient {
    
    private static final String BASE_URL = "http://localhost:8080";
    private RestTemplate restTemplate = new RestTemplate();
    private String token;
    
    // Login
    public String login(String email, String password) {
        String url = BASE_URL + "/auth/login";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);
        
        HttpEntity<Map<String, String>> request = 
            new HttpEntity<>(loginData, headers);
        
        ResponseEntity<Map> response = restTemplate.postForEntity(
            url, request, Map.class);
        
        this.token = (String) response.getBody().get("accessToken");
        return this.token;
    }
    
    // Get Products
    public List<Product> getProducts() {
        String url = BASE_URL + "/products";
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<List> response = restTemplate.exchange(
            url, HttpMethod.GET, entity, List.class);
        
        return response.getBody();
    }
    
    // Create Recipe
    public boolean createRecipe(RecipeCreationDTO recipeData) {
        String url = BASE_URL + "/recipes/add";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        
        HttpEntity<RecipeCreationDTO> request = 
            new HttpEntity<>(recipeData, headers);
        
        ResponseEntity<Void> response = restTemplate.postForEntity(
            url, request, Void.class);
        
        return response.getStatusCode() == HttpStatus.OK;
    }
}
```

---

### C# (.NET)

```csharp
using System;
using System.Net.Http;
using System.Net.Http.Json;
using System.Threading.Tasks;

public class GrocipesApiClient
{
    private static readonly string BaseUrl = "http://localhost:8080";
    private readonly HttpClient _client = new HttpClient();
    private string _token;
    
    // Login
    public async Task<string> LoginAsync(string email, string password)
    {
        var loginData = new
        {
            email = email,
            password = password
        };
        
        var response = await _client.PostAsJsonAsync(
            $"{BaseUrl}/auth/login", loginData);
        
        var result = await response.Content.ReadFromJsonAsync<LoginResponse>();
        _token = result.AccessToken;
        return _token;
    }
    
    // Get Products
    public async Task<List<Product>> GetProductsAsync()
    {
        _client.DefaultRequestHeaders.Authorization = 
            new AuthenticationHeaderValue("Bearer", _token);
        
        var products = await _client.GetFromJsonAsync<List<Product>>(
            $"{BaseUrl}/products");
        
        return products;
    }
    
    // Create Recipe
    public async Task<bool> CreateRecipeAsync(RecipeCreationDTO recipe)
    {
        _client.DefaultRequestHeaders.Authorization = 
            new AuthenticationHeaderValue("Bearer", _token);
        
        var response = await _client.PostAsJsonAsync(
            $"{BaseUrl}/recipes/add", recipe);
        
        return response.IsSuccessStatusCode;
    }
}

public class LoginResponse
{
    public string AccessToken { get; set; }
}
```

---

## Error Handling Examples

### Handling 401 Unauthorized

```javascript
async function getProducts() {
  const token = localStorage.getItem('token');
  
  try {
    const response = await fetch('http://localhost:8080/products', {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    
    if (response.status === 401) {
      // Token expired, redirect to login
      console.log('Token expired, please login again');
      window.location.href = '/login';
      return;
    }
    
    const products = await response.json();
    return products;
  } catch (error) {
    console.error('Error fetching products:', error);
  }
}
```

---

### Handling Validation Errors

```python
def create_recipe(token, recipe_data):
    try:
        response = requests.post(
            f"{BASE_URL}/recipes/add",
            headers={
                "Authorization": f"Bearer {token}",
                "Content-Type": "application/json"
            },
            json=recipe_data
        )
        
        if response.status_code == 400:
            error = response.json()
            print(f"Validation error: {error['message']}")
            return False
        
        response.raise_for_status()
        return True
        
    except requests.exceptions.RequestException as e:
        print(f"Error: {e}")
        return False
```

---

## Complete Workflow Example

```javascript
// Complete workflow: Register, Login, Create Recipe, Plan Meal

async function completeWorkflow() {
  try {
    // 1. Register
    const registerResponse = await fetch('http://localhost:8080/auth/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        email: 'new.user@example.com',
        password: 'SecurePass123!',
        name: 'New',
        surname: 'User',
        birthday: '1995-03-20',
        gender: 'FEMALE'
      })
    });
    
    const { accessToken } = await registerResponse.json();
    console.log('✓ Registered and logged in');
    
    // 2. Create a recipe
    const recipeResponse = await fetch('http://localhost:8080/recipes/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${accessToken}`
      },
      body: JSON.stringify({
        recipe: {
          title: 'Quick Omelette',
          description: 'Fast and nutritious breakfast',
          preparation_method: '1. Beat eggs\n2. Cook in pan\n3. Serve hot',
          typeOfMeal: 'BREAKFAST'
        },
        products: [
          { product_id: 5, amount: 3, unit_id: 3 },
          { product_id: 10, amount: 50, unit_id: 1 }
        ]
      })
    });
    console.log('✓ Recipe created');
    
    // 3. Set nutritional goal
    const goalResponse = await fetch('http://localhost:8080/nutritionalGoal/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${accessToken}`
      },
      body: JSON.stringify({
        name: 'My Weight Loss Goal',
        typeOfGoal: 'WEIGHT_LOSS',
        targetWeight: 70.0,
        startDate: new Date().toISOString(),
        activityLevel: 'MODERATE'
      })
    });
    console.log('✓ Nutritional goal set');
    
    // 4. Create shopping list
    const shoppingResponse = await fetch('http://localhost:8080/shoppingList/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${accessToken}`
      },
      body: JSON.stringify({
        name: 'This Week',
        shopping_date: new Date(Date.now() + 86400000).toISOString(),
        cardColor: '#2196F3',
        likedList: false,
        productShoppingLists: [
          { product_id: 5, amount: 12, unit_id: 3, bought: false },
          { product_id: 10, amount: 200, unit_id: 1, bought: false }
        ]
      })
    });
    console.log('✓ Shopping list created');
    
    console.log('✓ Workflow complete!');
    
  } catch (error) {
    console.error('Error in workflow:', error);
  }
}

completeWorkflow();
```

---

## Tips & Best Practices

### Token Management

```javascript
// Store token securely
localStorage.setItem('token', token);

// Retrieve token
const token = localStorage.getItem('token');

// Remove token on logout
localStorage.removeItem('token');

// Check token expiration
function isTokenExpired(token) {
  const payload = JSON.parse(atob(token.split('.')[1]));
  return Date.now() >= payload.exp * 1000;
}
```

---

### Retry Logic

```javascript
async function fetchWithRetry(url, options, retries = 3) {
  for (let i = 0; i < retries; i++) {
    try {
      const response = await fetch(url, options);
      if (response.ok) return response;
      
      if (response.status === 401) {
        // Don't retry on auth errors
        throw new Error('Unauthorized');
      }
    } catch (error) {
      if (i === retries - 1) throw error;
      await new Promise(resolve => setTimeout(resolve, 1000 * (i + 1)));
    }
  }
}
```

---

## Support

For more examples and help:
- Check [API Documentation](./API_DOCUMENTATION.md)
- Review [Developer Guide](./DEVELOPER_GUIDE.md)
- Open an [issue](https://github.com/konradRduch/Grocipes/issues)

---

**Happy API Integration! 🚀**
