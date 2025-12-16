# Developer Guide 💻

## Table of Contents
1. [Introduction](#introduction)
2. [Development Environment Setup](#development-environment-setup)
3. [Project Architecture](#project-architecture)
4. [Backend Development](#backend-development)
5. [Frontend Development](#frontend-development)
6. [Database Management](#database-management)
7. [Docker Development](#docker-development)
8. [Testing](#testing)
9. [Code Style & Conventions](#code-style--conventions)
10. [Debugging](#debugging)
11. [Common Issues](#common-issues)
12. [Deployment](#deployment)

---

## Introduction

This guide is intended for developers who want to contribute to or customize the Grocipes application. It covers setup, architecture, development workflows, and best practices.

### Prerequisites

Before starting development, ensure you have:

- **Git** (2.30+): Version control
- **Java JDK** (17): Backend development
- **Node.js** (18.x) & npm: Frontend development
- **MySQL** (8.0+): Database
- **Maven** (3.x): Java build tool
- **Angular CLI** (16.x): Frontend CLI
- **Docker Desktop** (optional): Container testing
- **IDE**: IntelliJ IDEA, VS Code, or Eclipse

---

## Development Environment Setup

### 1. Clone the Repository

```bash
git clone https://github.com/konradRduch/Grocipes.git
cd Grocipes
```

### 2. Backend Setup

#### Install Java Dependencies

```bash
cd Backend/grocipes
./mvnw clean install
```

On Windows:
```powershell
cd Backend\grocipes
.\mvnw.cmd clean install
```

#### Configure Database

Create MySQL database:

```sql
CREATE DATABASE grocipesdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:33062/grocipesdb
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

#### JWT Configuration

The JWT secret is auto-generated. To customize, add to `application.properties`:

```properties
jwt.secret=YOUR_SECRET_KEY_HERE_MINIMUM_256_BITS
jwt.expiration=86400000
```

### 3. Frontend Setup

#### Install Node Dependencies

```bash
cd Frontend/Grocipes
npm install --legacy-peer-deps
```

*Note: `--legacy-peer-deps` is required due to some peer dependency conflicts in Angular 16*

#### Configure API Endpoint

Update `src/environments/environment.ts`:

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080'
};
```

### 4. Running the Application

#### Start Backend

```bash
cd Backend/grocipes
./mvnw spring-boot:run
```

Backend runs on: http://localhost:8080

#### Start Frontend

```bash
cd Frontend/Grocipes
ng serve
```

Frontend runs on: http://localhost:4200

#### Verify Setup

Navigate to http://localhost:4200 and verify:
1. Application loads without errors
2. Can register a new account
3. Can login successfully
4. Dashboard displays correctly

---

## Project Architecture

### Backend Architecture

The backend follows a layered architecture pattern:

```
┌─────────────────────────────────────┐
│         Controllers                 │  ← REST API Layer
├─────────────────────────────────────┤
│         Services                    │  ← Business Logic
├─────────────────────────────────────┤
│         Repositories                │  ← Data Access
├─────────────────────────────────────┤
│         Entities/Models             │  ← Domain Models
├─────────────────────────────────────┤
│         MySQL Database              │  ← Persistence
└─────────────────────────────────────┘
```

#### Key Components

**Controllers** (`controllers/`)
- Handle HTTP requests
- Validate input
- Return responses
- Example: `ProductController.java`, `RecipeController.java`

**Services** (`services/`)
- Implement business logic
- Transaction management
- Data transformation
- Example: `ProductService.java`, `RecipeService.java`

**Repositories** (`repositories/`)
- JPA data access
- Custom queries
- Database operations
- Example: `ProductRepository.java`, `RecipeRepository.java`

**Models** (`models/`)
- Entity classes (database tables)
- DTOs (Data Transfer Objects)
- Example: `Product.java`, `ProductDTO.java`

**Security** (`security/`)
- JWT token generation/validation
- Authentication filters
- Security configuration
- Example: `JWTGenerator.java`, `SecurityConfig.java`

### Frontend Architecture

The frontend follows Angular's component-based architecture:

```
┌─────────────────────────────────────┐
│         Components                  │  ← UI Layer
├─────────────────────────────────────┤
│         Services                    │  ← API Communication
├─────────────────────────────────────┤
│         Guards                      │  ← Route Protection
├─────────────────────────────────────┤
│         Models                      │  ← TypeScript Interfaces
└─────────────────────────────────────┘
```

#### Key Directories

- `app/components/` - UI components
- `app/service/` - HTTP services
- `app/model/` - TypeScript interfaces
- `app/guards/` - Route guards (auth)
- `assets/` - Static resources

---

## Backend Development

### Creating a New Entity

1. **Create Entity Class** (`models/`)

```java
package com.grocipes_backend.grocipes.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    // Relationships
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
```

2. **Create Repository** (`repositories/`)

```java
package com.grocipes_backend.grocipes.repositories;

import com.grocipes_backend.grocipes.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
}
```

3. **Create DTO** (`models/DTO/`)

```java
package com.grocipes_backend.grocipes.models.DTO;

import lombok.Data;

@Data
public class CategoryDTO {
    private Integer id;
    private String name;
    private String description;
}
```

4. **Create Service** (`services/`)

```java
package com.grocipes_backend.grocipes.services;

import com.grocipes_backend.grocipes.models.Category;
import com.grocipes_backend.grocipes.models.DTO.CategoryDTO;
import com.grocipes_backend.grocipes.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    @Transactional
    public CategoryDTO createCategory(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        
        Category saved = categoryRepository.save(category);
        return convertToDTO(saved);
    }
    
    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}
```

5. **Create Controller** (`controllers/`)

```java
package com.grocipes_backend.grocipes.controllers;

import com.grocipes_backend.grocipes.models.DTO.CategoryDTO;
import com.grocipes_backend.grocipes.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    private final CategoryService categoryService;
    
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
    
    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO created = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(created);
    }
}
```

### Adding Security to Endpoints

Use `@PreAuthorize` annotation:

```java
import org.springframework.security.access.prepost.PreAuthorize;

@GetMapping("/admin-only")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<String> adminEndpoint() {
    return ResponseEntity.ok("Admin access granted");
}
```

### Custom Queries

Add to repository:

```java
@Query("SELECT p FROM Product p WHERE p.calories < :maxCalories")
List<Product> findLowCalorieProducts(@Param("maxCalories") int maxCalories);

@Query(value = "SELECT * FROM products WHERE name LIKE %:keyword%", nativeQuery = true)
List<Product> searchByKeyword(@Param("keyword") String keyword);
```

---

## Frontend Development

### Creating a New Component

```bash
ng generate component components/meal-planner
```

This creates:
- `meal-planner.component.ts` - Component logic
- `meal-planner.component.html` - Template
- `meal-planner.component.css` - Styles
- `meal-planner.component.spec.ts` - Tests

### Creating a Service

```bash
ng generate service service/category
```

Example service:

```typescript
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Category {
  id: number;
  name: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiUrl = `${environment.apiUrl}/categories`;

  constructor(private http: HttpClient) { }

  getAllCategories(): Observable<Category[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<Category[]>(this.apiUrl, { headers });
  }

  createCategory(category: Partial<Category>): Observable<Category> {
    const headers = this.getAuthHeaders();
    return this.http.post<Category>(`${this.apiUrl}/add`, category, { headers });
  }

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
  }
}
```

### Route Guards

Protect routes with authentication:

```typescript
import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  
  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  canActivate(): boolean {
    if (this.authService.isLoggedIn()) {
      return true;
    }
    
    this.router.navigate(['/login']);
    return false;
  }
}
```

Use in routing:

```typescript
const routes: Routes = [
  { 
    path: 'dashboard', 
    component: DashboardComponent,
    canActivate: [AuthGuard]
  }
];
```

### Using Bootstrap Components

Example modal:

```typescript
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

constructor(private modalService: NgbModal) { }

openModal(content: any) {
  this.modalService.open(content, { size: 'lg' });
}
```

```html
<ng-template #myModal let-modal>
  <div class="modal-header">
    <h4 class="modal-title">Add Category</h4>
    <button type="button" class="btn-close" (click)="modal.dismiss()"></button>
  </div>
  <div class="modal-body">
    <!-- Form content -->
  </div>
</ng-template>
```

---

## Database Management

### Schema Updates

The application uses `spring.jpa.hibernate.ddl-auto=update` which automatically updates the schema based on entity changes.

For production, set to `validate` and use migration tools like Flyway or Liquibase.

### Initial Data

Add initial data in `src/main/resources/data.sql`:

```sql
-- Insert default roles
INSERT IGNORE INTO roles (name) VALUES ('USER');
INSERT IGNORE INTO roles (name) VALUES ('ADMIN');

-- Insert sample products
INSERT IGNORE INTO products (name, weight, price, calories, unit_id) 
VALUES ('Chicken Breast', 100, 5.99, 165, 1);
```

### Database Backup

```bash
# Backup
mysqldump -u root -p grocipesdb > backup_$(date +%Y%m%d).sql

# Restore
mysql -u root -p grocipesdb < backup_20251216.sql
```

---

## Docker Development

### Building Images

Build backend image:

```bash
cd Backend/grocipes
docker build -t grocipes-backend .
```

Build frontend image:

```bash
cd Frontend/Grocipes
docker build -t grocipes-frontend .
```

### Docker Compose for Development

Create `docker-compose.dev.yml`:

```yaml
version: '3.8'

services:
  mysql:
    image: mysql:latest
    environment:
      MYSQL_DATABASE: grocipesdb
      MYSQL_ROOT_PASSWORD: 1234
    ports:
      - "33062:3306"
    volumes:
      - mysql-data:/var/lib/mysql

  backend:
    build: ./Backend/grocipes
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/grocipesdb
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: 1234
    ports:
      - "8080:8080"
    depends_on:
      - mysql
    volumes:
      - ./Backend/grocipes/src:/app/src

  frontend:
    build: ./Frontend/Grocipes
    ports:
      - "4200:80"
    depends_on:
      - backend

volumes:
  mysql-data:
```

Run with:

```bash
docker-compose -f docker-compose.dev.yml up
```

---

## Testing

### Backend Tests

#### Unit Tests

```java
package com.grocipes_backend.grocipes.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    
    @Mock
    private CategoryRepository categoryRepository;
    
    @InjectMocks
    private CategoryService categoryService;
    
    @Test
    void testGetAllCategories() {
        // Arrange
        List<Category> mockCategories = Arrays.asList(
            new Category(1, "Vegetables"),
            new Category(2, "Fruits")
        );
        when(categoryRepository.findAll()).thenReturn(mockCategories);
        
        // Act
        List<CategoryDTO> result = categoryService.getAllCategories();
        
        // Assert
        assertEquals(2, result.size());
        verify(categoryRepository, times(1)).findAll();
    }
}
```

Run tests:

```bash
./mvnw test
```

### Frontend Tests

#### Component Tests

```typescript
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CategoryListComponent } from './category-list.component';
import { CategoryService } from '../../service/category.service';
import { of } from 'rxjs';

describe('CategoryListComponent', () => {
  let component: CategoryListComponent;
  let fixture: ComponentFixture<CategoryListComponent>;
  let mockCategoryService: jasmine.SpyObj<CategoryService>;

  beforeEach(async () => {
    mockCategoryService = jasmine.createSpyObj('CategoryService', ['getAllCategories']);
    
    await TestBed.configureTestingModule({
      declarations: [ CategoryListComponent ],
      providers: [
        { provide: CategoryService, useValue: mockCategoryService }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CategoryListComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load categories on init', () => {
    const mockCategories = [
      { id: 1, name: 'Vegetables', description: 'Fresh veggies' }
    ];
    mockCategoryService.getAllCategories.and.returnValue(of(mockCategories));
    
    component.ngOnInit();
    
    expect(component.categories.length).toBe(1);
    expect(mockCategoryService.getAllCategories).toHaveBeenCalled();
  });
});
```

Run tests:

```bash
ng test
```

---

## Code Style & Conventions

### Backend (Java)

- Follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- Use meaningful variable names
- Keep methods short and focused
- Add JavaDoc for public APIs

```java
/**
 * Retrieves all categories from the database.
 * 
 * @return List of CategoryDTO objects
 * @throws DatabaseException if database connection fails
 */
public List<CategoryDTO> getAllCategories() {
    // Implementation
}
```

### Frontend (TypeScript)

- Follow [Angular Style Guide](https://angular.io/guide/styleguide)
- Use TypeScript strict mode
- Prefer interfaces over classes for models
- Use async/await or RxJS operators

```typescript
// Good
async loadCategories(): Promise<void> {
  try {
    this.categories = await this.categoryService.getAllCategories().toPromise();
  } catch (error) {
    console.error('Failed to load categories', error);
  }
}

// Also Good (RxJS)
loadCategories(): void {
  this.categoryService.getAllCategories()
    .subscribe({
      next: (data) => this.categories = data,
      error: (err) => console.error('Failed to load categories', err)
    });
}
```

### Naming Conventions

| Type | Convention | Example |
|------|-----------|---------|
| Java Classes | PascalCase | `ProductService` |
| Java Methods | camelCase | `getAllProducts()` |
| Java Constants | UPPER_SNAKE_CASE | `MAX_RETRY_COUNT` |
| TypeScript Classes | PascalCase | `CategoryService` |
| TypeScript Methods | camelCase | `loadCategories()` |
| Components | kebab-case | `product-list` |
| Files | kebab-case | `product-list.component.ts` |

---

## Debugging

### Backend Debugging

#### IntelliJ IDEA

1. Set breakpoints in code
2. Run → Debug 'GrocipesApplication'
3. Use Debug Console and Variables view

#### VS Code

Add to `.vscode/launch.json`:

```json
{
  "type": "java",
  "name": "Debug Spring Boot",
  "request": "attach",
  "hostName": "localhost",
  "port": 5005
}
```

Run app with debug:

```bash
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

### Frontend Debugging

#### Chrome DevTools

1. Open Chrome DevTools (F12)
2. Sources tab → Filesystem → Add workspace folder
3. Set breakpoints in TypeScript files
4. Use Console for logging

#### VS Code

Angular debugging is automatically configured. Press F5 to start debugging.

### Logging

Backend logging levels in `application.properties`:

```properties
logging.level.root=INFO
logging.level.com.grocipes_backend=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate=INFO
```

---

## Common Issues

### Issue: Port Already in Use

**Backend (8080)**

```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

**Frontend (4200)**

```bash
# Use different port
ng serve --port 4201
```

### Issue: Database Connection Failed

Check:
1. MySQL is running
2. Database exists: `SHOW DATABASES;`
3. Credentials in `application.properties`
4. Port is correct (33062)

### Issue: JWT Token Invalid

- Token expired: Re-login
- Token malformed: Clear localStorage
- Secret changed: Re-generate tokens

### Issue: CORS Errors

Add CORS configuration to backend:

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

### Issue: NPM Install Fails

```bash
# Clear cache
npm cache clean --force

# Delete node_modules
rm -rf node_modules package-lock.json

# Reinstall
npm install --legacy-peer-deps
```

---

## Deployment

### Production Build

#### Backend

```bash
cd Backend/grocipes
./mvnw clean package -DskipTests
```

JAR file: `target/grocipes-0.0.1-SNAPSHOT.jar`

#### Frontend

```bash
cd Frontend/Grocipes
ng build --configuration production
```

Output: `dist/grocipes/`

### Environment Variables

Create `.env` file:

```bash
# Database
DB_HOST=localhost
DB_PORT=3306
DB_NAME=grocipesdb
DB_USER=root
DB_PASSWORD=your_password

# JWT
JWT_SECRET=your_secret_key_minimum_256_bits
JWT_EXPIRATION=86400000

# Server
SERVER_PORT=8080
```

### Docker Production

```bash
docker-compose up --build -d
```

### Health Check

Backend health endpoint:

```
GET http://localhost:8080/actuator/health
```

Add to `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

---

## Performance Optimization

### Backend

1. **Enable caching**
```java
@Cacheable("products")
public List<ProductDTO> getAllProducts() {
    // ...
}
```

2. **Use pagination**
```java
@GetMapping
public Page<ProductDTO> getProducts(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "20") int size
) {
    return productService.getProducts(PageRequest.of(page, size));
}
```

3. **Optimize queries** (use JOIN FETCH for eager loading)

### Frontend

1. **Lazy loading modules**
```typescript
const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  }
];
```

2. **OnPush change detection**
```typescript
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush
})
```

3. **TrackBy for ngFor**
```typescript
trackByFn(index: number, item: any): any {
  return item.id;
}
```

---

## Contributing Guidelines

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/awesome-feature
   ```
3. **Make changes and commit**
   ```bash
   git commit -m "Add awesome feature"
   ```
4. **Write tests** for new features
5. **Push to branch**
   ```bash
   git push origin feature/awesome-feature
   ```
6. **Open Pull Request** with description

---

## Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Angular Documentation](https://angular.io/docs)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Docker Documentation](https://docs.docker.com/)
- [JWT Introduction](https://jwt.io/introduction)

---

**Happy Coding! 🚀**
