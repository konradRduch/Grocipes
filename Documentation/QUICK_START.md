# Quick Start Guide ⚡

Get up and running with Grocipes in minutes!

---

## 🎯 Choose Your Path

### 👤 I'm a User
[Jump to User Quick Start](#user-quick-start)

### 💻 I'm a Developer
[Jump to Developer Quick Start](#developer-quick-start)

### 🔌 I Want to Use the API
[Jump to API Quick Start](#api-quick-start)

---

## User Quick Start

### Step 1: Access the Application

Navigate to: **http://localhost** (or your deployment URL)

### Step 2: Create an Account (2 minutes)

```
1. Click "Register" or "Sign Up"
2. Fill in your details:
   ✓ Email
   ✓ Password
   ✓ Name & Surname
   ✓ Birthday
   ✓ Gender
3. Click "Create Account"
4. You're automatically logged in!
```

### Step 3: Set Up Your Profile (3 minutes)

```
1. Go to "Profile"
2. Add body measurements:
   ✓ Current weight
   ✓ Height
   ✓ (Optional) Waist, chest, hips
3. Click "Save"
```

### Step 4: Create Your First Nutritional Goal (2 minutes)

```
1. Navigate to "Nutritional Goals"
2. Click "Create New Goal"
3. Select goal type (e.g., Weight Loss)
4. Enter target weight
5. Choose activity level
6. Click "Create Goal"

✨ Your daily nutritional requirements are automatically calculated!
```

### Step 5: Create Your First Recipe (5 minutes)

```
1. Go to "Recipes"
2. Click "Add Recipe"
3. Fill in:
   ✓ Title: "My Protein Smoothie"
   ✓ Description: Brief overview
   ✓ Meal Type: Breakfast/Lunch/Dinner/Snack
4. Add ingredients:
   ✓ Search for products
   ✓ Specify quantities
5. Write preparation steps
6. Click "Create Recipe"

✨ Nutritional values are calculated automatically!
```

### Step 6: Plan Your First Meal (2 minutes)

```
1. Navigate to "Meal Planning"
2. Select a date
3. Click "Add Meal"
4. Choose your recipe
5. Set time
6. Click "Schedule Meal"
```

### Step 7: Create a Shopping List (3 minutes)

```
1. Go to "Shopping Lists"
2. Click "Create from Recipes" (or "New Shopping List")
3. Select recipes for the week
4. Review generated list
5. Adjust quantities if needed
6. Click "Save List"
```

### Step 8: Track Your Progress (Ongoing)

```
Daily:
- Mark meals as eaten
- Rate your meals

Weekly:
- Log body measurements
- Review nutritional summary

Monthly:
- Check progress toward goals
- Adjust targets if needed
```

---

## Developer Quick Start

### Prerequisites Check ✅

Before starting, ensure you have:
- ✅ Java JDK 17+
- ✅ Node.js 18+ and npm
- ✅ MySQL 8.0+
- ✅ Maven 3.x
- ✅ Angular CLI
- ✅ Git

### Step 1: Clone the Repository (1 minute)

```bash
git clone https://github.com/konradRduch/Grocipes.git
cd Grocipes
```

### Step 2: Setup Database (3 minutes)

```sql
-- Connect to MySQL
mysql -u root -p

-- Create database
CREATE DATABASE grocipesdb;
EXIT;
```

### Step 3: Configure Backend (2 minutes)

Edit `Backend/grocipes/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:33062/grocipesdb
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

### Step 4: Install Backend Dependencies (2 minutes)

```bash
cd Backend/grocipes

# Linux/Mac
./mvnw clean install

# Windows
.\mvnw.cmd clean install
```

### Step 5: Run Backend (1 minute)

```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
.\mvnw.cmd spring-boot:run
```

✅ Backend running at: **http://localhost:8080**

### Step 6: Install Frontend Dependencies (3 minutes)

```bash
cd Frontend/Grocipes
npm install --legacy-peer-deps
```

### Step 7: Run Frontend (1 minute)

```bash
ng serve
```

✅ Frontend running at: **http://localhost:4200**

### Step 8: Verify Setup (2 minutes)

1. Open http://localhost:4200
2. Register a new account
3. Check that:
   - ✅ Registration works
   - ✅ Login works
   - ✅ Dashboard loads
   - ✅ No console errors

### Step 9: Start Coding! 🚀

Common development tasks:

**Create a new component:**
```bash
ng generate component components/my-component
```

**Create a new service:**
```bash
ng generate service service/my-service
```

**Run tests:**
```bash
# Backend
./mvnw test

# Frontend
ng test
```

**Build for production:**
```bash
# Backend
./mvnw clean package

# Frontend
ng build --configuration production
```

---

## Docker Quick Start (Alternative)

Prefer Docker? Even faster setup!

### Prerequisites
- ✅ Docker Desktop installed

### Step 1: Clone Repository

```bash
git clone https://github.com/konradRduch/Grocipes.git
cd Grocipes/Backend/grocipes
```

### Step 2: Start All Services

```bash
docker-compose up --build
```

That's it! 🎉

- **Frontend:** http://localhost
- **Backend:** http://localhost:8080
- **Database:** localhost:33062

### Stop Services

```bash
docker-compose down
```

---

## API Quick Start

### Step 1: Start the Backend

Follow [Developer Quick Start](#developer-quick-start) steps 1-5, or use Docker.

### Step 2: Register a Test User

**Endpoint:** `POST http://localhost:8080/auth/register`

**Request:**
```json
{
  "email": "test@example.com",
  "password": "Test123!",
  "name": "Test",
  "surname": "User",
  "birthday": "1990-01-01",
  "gender": "MALE"
}
```

**cURL:**
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "Test123!",
    "name": "Test",
    "surname": "User",
    "birthday": "1990-01-01",
    "gender": "MALE"
  }'
```

**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzUxMiJ9..."
}
```

💾 **Save this token!** You'll need it for authenticated requests.

### Step 3: Make Your First Authenticated Request

**Endpoint:** `GET http://localhost:8080/products`

**cURL:**
```bash
curl -X GET http://localhost:8080/products \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Chicken Breast",
    "calories": 165,
    "price": 5.99,
    ...
  }
]
```

### Step 4: Create a Recipe

**Endpoint:** `POST http://localhost:8080/recipes/add`

**Request:**
```json
{
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
```

**cURL:**
```bash
curl -X POST http://localhost:8080/recipes/add \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -H "Content-Type: application/json" \
  -d '{
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
  }'
```

### Step 5: Explore More Endpoints

Check the [Full API Documentation](./API_DOCUMENTATION.md) for:
- 32+ available endpoints
- Detailed request/response examples
- Error handling
- Data models

---

## Testing Tools

### Recommended API Testing Tools

**Postman**
1. Download from [postman.com](https://www.postman.com/)
2. Import Grocipes collection (create from API docs)
3. Set environment variable `{{token}}`

**Thunder Client (VS Code)**
1. Install Thunder Client extension
2. Create new request
3. Set Authorization header

**cURL (Command Line)**
- Already installed on most systems
- Examples provided in API docs

---

## Common Quick Issues

### ❌ Port 8080 already in use

**Solution:**
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

### ❌ Database connection failed

**Solution:**
1. Verify MySQL is running
2. Check credentials in `application.properties`
3. Ensure database `grocipesdb` exists

### ❌ Frontend compilation errors

**Solution:**
```bash
rm -rf node_modules package-lock.json
npm install --legacy-peer-deps
```

### ❌ Token invalid error

**Solution:**
- Token expired (24 hours)
- Login again to get new token

---

## Next Steps

### For Users
📖 Read the complete [User Guide](./USER_GUIDE.md) for:
- Advanced features
- Tips and best practices
- Troubleshooting

### For Developers
📖 Read the [Developer Guide](./DEVELOPER_GUIDE.md) for:
- Detailed architecture
- Creating new features
- Testing strategies
- Deployment

### For API Integration
📖 Read the [API Documentation](./API_DOCUMENTATION.md) for:
- All available endpoints
- Authentication details
- Error handling
- Data models

---

## 🎉 Congratulations!

You're now ready to use Grocipes!

**Need help?**
- Check the [FAQ](./USER_GUIDE.md#faq)
- Browse [Troubleshooting](./USER_GUIDE.md#troubleshooting)
- Open an [issue on GitHub](https://github.com/konradRduch/Grocipes/issues)

---

**Estimated Time to Complete:**
- ⏱️ User Quick Start: ~15 minutes
- ⏱️ Developer Quick Start: ~15 minutes
- ⏱️ Docker Quick Start: ~5 minutes
- ⏱️ API Quick Start: ~10 minutes

**Happy Grocipes-ing! 🥗🚀**
