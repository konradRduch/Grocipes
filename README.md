# Grocipes 🥗

**Grocipes** is a comprehensive full-stack web application designed to streamline the way users manage their nutrition. From tracking groceries and creating detailed recipes to generating automated shopping lists and monitoring nutritional goals, Grocipes offers an all-in-one solution for healthy living.

---

## 📑 Table of Contents
1. [Features](#-features)
2. [Tech Stack](#-tech-stack)
3. [Project Architecture](#-project-structure)
---

## 🚀 Features

### 👤 User Authentication
*   **Secure Login/Registration:** JWT-based authentication.
*   **Role-Based Access Control:**
    *   **User:** Can manage own recipes, lists, and goals.
    *   **Admin:** Full access to manage global products and moderate content.

### 🥦 Grocery Management
*   Add, update, and delete grocery items.
*   Advanced search and categorization of products.
*   Track pantry inventory.

### 🍲 Recipe Management
*   Create detailed recipes with ingredients, steps, and images.
*   Calculate total nutritional value per serving automatically.
*   Edit and delete personal recipes.

### 🛒 Shopping Lists
*   **Smart Generation:** Create shopping lists automatically based on selected recipes.
*   **Manual Override:** Add or remove extra items manually.
*   **Check-off System:** Mark items as bought while shopping.

### 📊 Nutritional Tracking
*   Define personal nutritional goals (calories, protein, carbs, fat).
*   Log body measurements (weight, waist, etc.) and visualize progress.

---

## 🛠 Tech Stack

| Component | Technology | Description |
| :--- | :--- | :--- |
| **Frontend** | ![Angular](https://img.shields.io/badge/Angular-DD0031?style=flat&logo=angular&logoColor=white) | Single Page Application (SPA) |
| **Styling** | ![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=flat&logo=bootstrap&logoColor=white) | Responsive UI components |
| **Backend** | ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white) | REST API & Business Logic |
| **Database** | ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white) | Relational Database |
| **DevOps** | ![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white) | Containerization & Orchestration |

---

## 📂 Project structure

The project is structured as a mono-repo containing both frontend and backend services:

```text
Grocipes/
├── Backend/
     └── grocipes/             # Spring Boot Application
        └──docker-compose.yml  # Container orchestration
├── Frontend/
│   └── Grocipes/       # Angular Application
└── README.md
