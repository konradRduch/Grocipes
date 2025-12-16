# Grocipes 🥗

**Grocipes** is a comprehensive full-stack web application designed to streamline the way users manage their nutrition. From tracking groceries and creating detailed recipes to generating automated shopping lists and monitoring nutritional goals, Grocipes offers an all-in-one solution for healthy living.

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.3-6DB33F?style=flat&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-16.1.0-DD0031?style=flat&logo=angular&logoColor=white)](https://angular.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=flat&logo=docker&logoColor=white)](https://www.docker.com/)
[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![TypeScript](https://img.shields.io/badge/TypeScript-5.1.3-3178C6?style=flat&logo=typescript&logoColor=white)](https://www.typescriptlang.org/)

---

## ⚡ Quick Start

**New to Grocipes?** Check out our [Quick Start Guide](./Documentation/QUICK_START.md) to get up and running in minutes!

- 👤 [User Quick Start](./Documentation/QUICK_START.md#user-quick-start) - Start using Grocipes in 15 minutes
- 💻 [Developer Quick Start](./Documentation/QUICK_START.md#developer-quick-start) - Set up your dev environment
- 🐳 [Docker Quick Start](./Documentation/QUICK_START.md#docker-quick-start-alternative) - Run with Docker in 5 minutes
- 🔌 [API Quick Start](./Documentation/QUICK_START.md#api-quick-start) - Make your first API call

---

## 📑 Table of Contents
1. [Features](#-features)
2. [Tech Stack](#-tech-stack)
3. [Project Structure](#-project-structure)
4. [Getting Started](#-getting-started)
   - [Prerequisites](#prerequisites)
   - [Installation](#installation)
   - [Running with Docker](#running-with-docker)
   - [Running Locally](#running-locally)
5. [API Documentation](#-api-documentation)
6. [User Guide](#-user-guide)
7. [Development](#-development)
8. [Testing](#-testing)
9. [Contributing](#-contributing)
10. [License](#-license)

---

## 🚀 Features

### 👤 User Authentication
*   **Secure Login/Registration:** JWT-based authentication with Spring Security
*   **Role-Based Access Control:**
    *   **User:** Can manage own recipes, lists, and goals
    *   **Admin:** Full access to manage global products and moderate content
*   **Token-based Authorization:** Secure API endpoints with Bearer tokens

### 🥦 Grocery Management
*   Add, update, and delete grocery items with full nutritional information
*   Advanced search and categorization of products
*   Track pantry inventory with weight and price tracking
*   Custom nutritional facts per 100g for each product

### 🍲 Recipe Management
*   Create detailed recipes with ingredients, preparation steps, and images
*   Calculate total nutritional value per serving automatically
*   Edit and delete personal recipes
*   Categorize recipes by meal type (breakfast, lunch, dinner, snack)
*   Associate multiple products with custom quantities

### 🛒 Shopping Lists
*   **Smart Generation:** Create shopping lists automatically based on selected recipes
*   **Manual Override:** Add or remove extra items manually
*   **Check-off System:** Mark items as bought while shopping
*   **Scheduling:** Plan shopping lists by date with color coding
*   **Liked Lists:** Mark favorite shopping lists for quick access

### 📊 Nutritional Tracking
*   Define personal nutritional goals (calories, protein, carbs, fat)
*   Log body measurements (weight, waist, etc.) and visualize progress over time
*   Calculate BMI and daily caloric needs based on activity level
*   Track progress toward weight goals with estimated completion time
*   Monitor macronutrient intake across meals

### 📅 Meal Planning
*   Plan meals by date with nutrition schedule
*   Track eating deadlines and meal completion
*   Rate and comment on completed meals
*   View nutritional summaries for planned meals

---

## 🛠 Tech Stack

### Backend
| Technology | Version | Purpose |
|------------|---------|---------|
| ![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white) | 17 | Programming Language |
| ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.3-6DB33F?style=flat&logo=spring-boot&logoColor=white) | 3.3.3 | REST API Framework |
| ![Spring Security](https://img.shields.io/badge/Spring_Security-6.x-6DB33F?style=flat&logo=spring-security&logoColor=white) | 6.x | Authentication & Authorization |
| ![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-3.3.3-6DB33F?style=flat&logo=spring&logoColor=white) | 3.3.3 | Database ORM |
| ![JWT](https://img.shields.io/badge/JWT-0.12.5-000000?style=flat&logo=json-web-tokens&logoColor=white) | 0.12.5 | Token-based Authentication |
| ![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat&logo=mysql&logoColor=white) | 8.0+ | Relational Database |
| ![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=flat&logo=apache-maven&logoColor=white) | 3.x | Build Tool |

### Frontend
| Technology | Version | Purpose |
|------------|---------|---------|
| ![Angular](https://img.shields.io/badge/Angular-16.1.0-DD0031?style=flat&logo=angular&logoColor=white) | 16.1.0 | Frontend Framework |
| ![TypeScript](https://img.shields.io/badge/TypeScript-5.1.3-3178C6?style=flat&logo=typescript&logoColor=white) | 5.1.3 | Programming Language |
| ![Bootstrap](https://img.shields.io/badge/Bootstrap-5.2.3-563D7C?style=flat&logo=bootstrap&logoColor=white) | 5.2.3 | UI Framework |
| ![ng-bootstrap](https://img.shields.io/badge/ng--bootstrap-15.1.2-563D7C?style=flat&logo=bootstrap&logoColor=white) | 15.1.2 | Angular Bootstrap Components |
| ![Chart.js](https://img.shields.io/badge/Chart.js-4.4.5-FF6384?style=flat&logo=chart.js&logoColor=white) | 4.4.5 | Data Visualization |
| ![RxJS](https://img.shields.io/badge/RxJS-7.8.0-B7178C?style=flat&logo=reactivex&logoColor=white) | 7.8.0 | Reactive Programming |

### DevOps
| Technology | Purpose |
|------------|---------|
| ![Docker](https://img.shields.io/badge/Docker-Containerization-2496ED?style=flat&logo=docker&logoColor=white) | Application Containerization |
| ![Docker Compose](https://img.shields.io/badge/Docker_Compose-Orchestration-2496ED?style=flat&logo=docker&logoColor=white) | Multi-container Management |
| ![Nginx](https://img.shields.io/badge/Nginx-Web_Server-009639?style=flat&logo=nginx&logoColor=white) | Frontend Web Server |

---

## 📂 Project Structure

The project is structured as a mono-repo containing both frontend and backend services:

```text
Grocipes/
├── Backend/
│   └── grocipes/                    # Spring Boot Application
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/com/grocipes_backend/grocipes/
│       │   │   │   ├── controllers/      # REST Controllers
│       │   │   │   ├── models/           # Entity Models & DTOs
│       │   │   │   ├── repositories/     # JPA Repositories
│       │   │   │   ├── services/         # Business Logic
│       │   │   │   └── security/         # Security & JWT Config
│       │   │   └── resources/
│       │   │       ├── application.properties
│       │   │       └── data.sql          # Initial Data
│       │   └── test/                     # Unit Tests
│       ├── pom.xml                       # Maven Dependencies
│       ├── Dockerfile                    # Backend Container Config
│       └── compose.yaml                  # Docker Compose Config
├── Frontend/
│   └── Grocipes/                    # Angular Application
│       ├── src/
│       │   ├── app/
│       │   │   ├── admin-panel/         # Admin Dashboard
│       │   │   ├── authentication/      # Login/Register
│       │   │   ├── recipes/             # Recipe Management
│       │   │   ├── groceries/           # Product Management
│       │   │   ├── shopping-list/       # Shopping Lists
│       │   │   ├── nutrition-schedule/  # Meal Planning
│       │   │   ├── nutritional-goal/    # Goal Tracking
│       │   │   ├── profile/             # User Profile
│       │   │   ├── service/             # API Services
│       │   │   └── model/               # TypeScript Models
│       │   └── assets/                  # Images & Static Files
│       ├── package.json                 # NPM Dependencies
│       ├── angular.json                 # Angular Config
│       └── Dockerfile                   # Frontend Container Config
├── Documentation/                   # Project Documentation
│   ├── Design/                      # Design Documents
│   ├── ERD/                         # Database Schemas
│   └── UML/                         # UML Diagrams
└── README.md                        # This File
```

---

## 🚀 Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

#### For Docker Deployment (Recommended)
- **Docker Desktop** (20.10+): [Download here](https://www.docker.com/products/docker-desktop)
- **Docker Compose** (v2.0+): Usually included with Docker Desktop

#### For Local Development
- **Java Development Kit (JDK)** 17 or higher: [Download here](https://www.oracle.com/java/technologies/downloads/)
- **Node.js** (18.x or higher) and **npm**: [Download here](https://nodejs.org/)
- **MySQL Server** (8.0+): [Download here](https://dev.mysql.com/downloads/mysql/)
- **Maven** (3.x): [Download here](https://maven.apache.org/download.cgi)
- **Angular CLI**: Install globally with `npm install -g @angular/cli`

### Installation

#### Clone the Repository

```bash
git clone https://github.com/konradRduch/Grocipes.git
cd Grocipes
```

---

### Running with Docker

The easiest way to run the entire application stack is using Docker Compose.

#### 1. Build and Start All Services

From the root directory:

```bash
cd Backend/grocipes
docker-compose up --build
```

This will:
- Build the Spring Boot backend
- Build the Angular frontend
- Start MySQL database
- Configure networking between services

#### 2. Access the Application

- **Frontend:** http://localhost
- **Backend API:** http://localhost:8080
- **MySQL Database:** localhost:33062 (from host machine)

#### 3. Stop the Services

```bash
docker-compose down
```

To remove volumes (clear database):

```bash
docker-compose down -v
```

---

### Running Locally

For development purposes, you may want to run services individually.

#### 1. Setup MySQL Database

```bash
# Start MySQL server and create database
mysql -u root -p
CREATE DATABASE grocipesdb;
EXIT;
```

#### 2. Configure Backend

Edit `Backend/grocipes/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:33062/grocipesdb
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

#### 3. Run Backend

```bash
cd Backend/grocipes
./mvnw clean install
./mvnw spring-boot:run
```

Backend will be available at: http://localhost:8080

#### 4. Install Frontend Dependencies

```bash
cd Frontend/Grocipes
npm install --legacy-peer-deps
```

#### 5. Run Frontend

```bash
ng serve
```

Frontend will be available at: http://localhost:4200

---

## 📖 API Documentation

Detailed API documentation is available in [API_DOCUMENTATION.md](./Documentation/API_DOCUMENTATION.md).

### Base URL

```
http://localhost:8080
```

### Authentication

Most endpoints require JWT authentication. Include the token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

### Quick Reference

| Endpoint | Method | Description | Auth Required |
|----------|--------|-------------|---------------|
| `/auth/register` | POST | Register new user | No |
| `/auth/login` | POST | Login user | No |
| `/products` | GET | Get all products | Yes |
| `/products/add` | POST | Add new product | Yes (Admin) |
| `/recipes` | GET | Get all recipes | Yes |
| `/recipes/add` | POST | Create recipe | Yes |
| `/shoppingList/getUserShoppingSchedules` | GET | Get shopping schedules | Yes |
| `/nutritionalGoal/getAll` | GET | Get nutritional goals | Yes |
| `/userData/getUser` | GET | Get user profile | Yes |

For complete API documentation with request/response examples, see [API_DOCUMENTATION.md](./Documentation/API_DOCUMENTATION.md).

---

## 👥 User Guide

For detailed end-user instructions, see [USER_GUIDE.md](./Documentation/USER_GUIDE.md).

### Quick Start for Users

1. **Register an Account**
   - Navigate to the registration page
   - Fill in your personal information
   - Set a secure password

2. **Set Up Your Profile**
   - Add body measurements (weight, height)
   - Define your nutritional goals
   - Specify activity level

3. **Manage Products**
   - Browse available products
   - Search and filter by category
   - Add custom products (if admin)

4. **Create Recipes**
   - Add recipe details and images
   - Select ingredients with quantities
   - Save preparation instructions

5. **Plan Your Meals**
   - Schedule recipes in your nutrition calendar
   - Track daily nutritional intake
   - Rate completed meals

6. **Generate Shopping Lists**
   - Select recipes for the week
   - Auto-generate shopping list
   - Check off items while shopping

---

## 💻 Development

### Development Setup

See [DEVELOPER_GUIDE.md](./Documentation/DEVELOPER_GUIDE.md) for comprehensive development instructions.

### Project Architecture

- **Backend:** Follows MVC pattern with layered architecture
  - Controllers: Handle HTTP requests
  - Services: Business logic
  - Repositories: Data access layer
  - Models: Entities and DTOs

- **Frontend:** Component-based architecture
  - Components: UI elements
  - Services: API communication
  - Guards: Route protection
  - Models: TypeScript interfaces

### Key Configuration Files

- `Backend/grocipes/pom.xml` - Maven dependencies
- `Backend/grocipes/src/main/resources/application.properties` - Backend config
- `Frontend/Grocipes/package.json` - NPM dependencies
- `Frontend/Grocipes/angular.json` - Angular configuration
- `Backend/grocipes/compose.yaml` - Docker orchestration

---

## 🧪 Testing

### Backend Tests

```bash
cd Backend/grocipes
./mvnw test
```

### Frontend Tests

```bash
cd Frontend/Grocipes
ng test
```

### End-to-End Tests

```bash
cd Frontend/Grocipes
ng e2e
```

---

## 🤝 Contributing

We welcome contributions! Whether it's bug fixes, new features, documentation improvements, or suggestions, your help is appreciated.

**Before contributing:**
- Read our [Contributing Guidelines](./CONTRIBUTING.md)
- Check the [Code of Conduct](./CONTRIBUTING.md#code-of-conduct)
- Review existing [issues](https://github.com/konradRduch/Grocipes/issues) and [pull requests](https://github.com/konradRduch/Grocipes/pulls)

**Quick contribution steps:**

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'feat: add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

For detailed information, see [CONTRIBUTING.md](./CONTRIBUTING.md).

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.

**In short:** You can use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the software.

---

## 📝 Changelog

See [CHANGELOG.md](./CHANGELOG.md) for a detailed history of changes, updates, and releases.

**Latest Version:** 1.0.0 (December 16, 2025)

---

## 👨‍💻 Author

**Konrad Rduch**
- GitHub: [@konradRduch](https://github.com/konradRduch)
- Repository: [Grocipes](https://github.com/konradRduch/Grocipes)

---

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Angular team for the powerful frontend platform
- Bootstrap team for the responsive UI components
- All contributors and testers

---

## 📞 Support

If you encounter any issues or have questions:
- Open an issue on [GitHub Issues](https://github.com/konradRduch/Grocipes/issues)
- Check the [Documentation](./Documentation/) folder for detailed guides

---

**Made with ❤️ and lots of ☕**
