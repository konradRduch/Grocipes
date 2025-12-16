# Contributing to Grocipes 🤝

Thank you for your interest in contributing to Grocipes! This document provides guidelines and instructions for contributing to the project.

---

## 📋 Table of Contents

1. [Code of Conduct](#code-of-conduct)
2. [Getting Started](#getting-started)
3. [How to Contribute](#how-to-contribute)
4. [Development Workflow](#development-workflow)
5. [Coding Standards](#coding-standards)
6. [Commit Guidelines](#commit-guidelines)
7. [Pull Request Process](#pull-request-process)
8. [Reporting Bugs](#reporting-bugs)
9. [Suggesting Features](#suggesting-features)
10. [Community](#community)

---

## Code of Conduct

### Our Pledge

We are committed to providing a welcoming and inspiring community for everyone. Please be respectful and constructive in all interactions.

### Our Standards

**Examples of behavior that contributes to a positive environment:**
- Using welcoming and inclusive language
- Being respectful of differing viewpoints
- Gracefully accepting constructive criticism
- Focusing on what is best for the community
- Showing empathy towards other community members

**Unacceptable behavior includes:**
- Harassment, trolling, or discriminatory comments
- Public or private harassment
- Publishing others' private information
- Other conduct which could reasonably be considered inappropriate

---

## Getting Started

### Prerequisites

Before contributing, ensure you have:
- Read the [Developer Guide](./Documentation/DEVELOPER_GUIDE.md)
- Set up your development environment
- Familiarized yourself with the codebase

### First Time Contributors

Look for issues labeled:
- `good first issue` - Simple issues perfect for newcomers
- `help wanted` - Issues where we need community help
- `documentation` - Documentation improvements

---

## How to Contribute

### Types of Contributions

We welcome various types of contributions:

**Code Contributions**
- Bug fixes
- New features
- Performance improvements
- Code refactoring

**Documentation**
- Fixing typos or clarifying existing docs
- Adding examples
- Translating documentation
- Creating tutorials

**Testing**
- Writing unit tests
- Creating integration tests
- Manual testing and reporting issues

**Design**
- UI/UX improvements
- Creating mockups
- Improving accessibility

---

## Development Workflow

### 1. Fork the Repository

```bash
# Click "Fork" on GitHub
# Then clone your fork
git clone https://github.com/YOUR_USERNAME/Grocipes.git
cd Grocipes
```

### 2. Create a Branch

```bash
# Create a descriptive branch name
git checkout -b feature/add-meal-export
git checkout -b fix/shopping-list-bug
git checkout -b docs/improve-api-docs
```

**Branch Naming Convention:**
- `feature/` - New features
- `fix/` - Bug fixes
- `docs/` - Documentation changes
- `refactor/` - Code refactoring
- `test/` - Test additions or changes
- `style/` - Code style changes

### 3. Make Your Changes

- Follow the [coding standards](#coding-standards)
- Write meaningful commit messages
- Add tests for new features
- Update documentation as needed

### 4. Test Your Changes

**Backend:**
```bash
cd Backend/grocipes
./mvnw test
```

**Frontend:**
```bash
cd Frontend/Grocipes
ng test
ng lint
```

### 5. Commit Your Changes

```bash
git add .
git commit -m "feat: add meal export functionality"
```

See [Commit Guidelines](#commit-guidelines) for proper commit message format.

### 6. Push to Your Fork

```bash
git push origin feature/add-meal-export
```

### 7. Create a Pull Request

- Go to your fork on GitHub
- Click "New Pull Request"
- Fill in the PR template
- Submit the PR

---

## Coding Standards

### Backend (Java/Spring Boot)

**Style Guide:**
- Follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- Use 4 spaces for indentation
- Maximum line length: 120 characters

**Best Practices:**
```java
// Good: Descriptive names
public List<RecipeDTO> getUserRecipes(Integer userId) {
    return recipeRepository.findByUserId(userId)
        .stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
}

// Bad: Unclear names
public List<RecipeDTO> get(Integer id) {
    return repo.find(id).stream().map(this::conv).collect(Collectors.toList());
}
```

**Documentation:**
```java
/**
 * Retrieves all recipes for a specific user.
 * 
 * @param userId the ID of the user
 * @return list of recipe DTOs
 * @throws UserNotFoundException if user doesn't exist
 */
public List<RecipeDTO> getUserRecipes(Integer userId) {
    // Implementation
}
```

### Frontend (TypeScript/Angular)

**Style Guide:**
- Follow [Angular Style Guide](https://angular.io/guide/styleguide)
- Use 2 spaces for indentation
- Use single quotes for strings

**Best Practices:**
```typescript
// Good: Strongly typed
interface Recipe {
  id: number;
  title: string;
  calories: number;
}

loadRecipes(): void {
  this.recipeService.getRecipes()
    .subscribe({
      next: (recipes: Recipe[]) => this.recipes = recipes,
      error: (err) => this.handleError(err)
    });
}

// Bad: Using 'any'
loadRecipes(): void {
  this.recipeService.getRecipes()
    .subscribe((data: any) => {
      this.recipes = data;
    });
}
```

**Component Structure:**
```typescript
@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {
  recipes: Recipe[] = [];
  loading = false;
  error: string | null = null;

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.loadRecipes();
  }

  loadRecipes(): void {
    // Implementation
  }
}
```

### Database

**Naming Conventions:**
- Table names: plural, snake_case (e.g., `user_entities`, `shopping_lists`)
- Column names: snake_case (e.g., `created_at`, `user_id`)
- Foreign keys: `{referenced_table}_id` (e.g., `user_id`, `recipe_id`)

---

## Commit Guidelines

We follow [Conventional Commits](https://www.conventionalcommits.org/) specification.

### Commit Message Format

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types

- `feat` - A new feature
- `fix` - A bug fix
- `docs` - Documentation changes
- `style` - Code style changes (formatting, missing semicolons, etc.)
- `refactor` - Code refactoring without changing functionality
- `test` - Adding or updating tests
- `chore` - Maintenance tasks (dependencies, build, etc.)
- `perf` - Performance improvements

### Examples

**Feature:**
```
feat(recipes): add export to PDF functionality

Implemented PDF export for recipes with nutritional information.
Users can now download recipes as PDF files.

Closes #123
```

**Bug Fix:**
```
fix(shopping-list): correct quantity calculation

Fixed a bug where quantities were doubled when creating
shopping lists from multiple recipes.

Fixes #456
```

**Documentation:**
```
docs(api): update authentication endpoint examples

Added more examples for JWT token usage in API documentation.
```

**Refactoring:**
```
refactor(services): simplify recipe service methods

Extracted common logic into helper methods to reduce code duplication.
```

---

## Pull Request Process

### Before Submitting

**Checklist:**
- [ ] Code follows project coding standards
- [ ] All tests pass locally
- [ ] New features include tests
- [ ] Documentation is updated
- [ ] Commit messages follow guidelines
- [ ] No merge conflicts with main branch

### PR Template

When creating a PR, include:

```markdown
## Description
Brief description of the changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Documentation update
- [ ] Code refactoring

## Testing
How was this tested?

## Screenshots (if applicable)
Add screenshots for UI changes

## Checklist
- [ ] My code follows the project's style guidelines
- [ ] I have performed a self-review
- [ ] I have commented my code where needed
- [ ] I have updated the documentation
- [ ] My changes generate no new warnings
- [ ] I have added tests
- [ ] All tests pass locally

## Related Issues
Closes #123
```

### Review Process

1. **Automated Checks:**
   - CI/CD pipeline runs tests
   - Code quality checks
   - Linting validation

2. **Code Review:**
   - At least one maintainer reviews
   - Address all feedback
   - Re-request review after changes

3. **Approval & Merge:**
   - Once approved, maintainer will merge
   - Your contribution will be in the next release!

---

## Reporting Bugs

### Before Reporting

- Check existing issues to avoid duplicates
- Verify the bug exists in the latest version
- Collect relevant information

### Bug Report Template

```markdown
**Describe the bug**
A clear description of what the bug is.

**To Reproduce**
Steps to reproduce:
1. Go to '...'
2. Click on '...'
3. See error

**Expected behavior**
What you expected to happen.

**Screenshots**
If applicable, add screenshots.

**Environment:**
- OS: [e.g., Windows 10, macOS 12.0]
- Browser: [e.g., Chrome 96, Firefox 95]
- Version: [e.g., 1.0.0]

**Additional context**
Any other relevant information.
```

### Submitting

1. Go to [Issues](https://github.com/konradRduch/Grocipes/issues)
2. Click "New Issue"
3. Choose "Bug Report" template
4. Fill in all sections
5. Add appropriate labels

---

## Suggesting Features

### Feature Request Template

```markdown
**Is your feature request related to a problem?**
Describe the problem you're trying to solve.

**Describe the solution you'd like**
Clear description of what you want to happen.

**Describe alternatives you've considered**
Other solutions or features you've thought about.

**Additional context**
Mockups, examples, or references.

**Would you like to implement this feature?**
- [ ] Yes, I'd like to work on this
- [ ] No, just suggesting
```

### Evaluation Process

Feature requests are evaluated based on:
- Alignment with project goals
- User impact and demand
- Implementation complexity
- Maintenance burden

---

## Community

### Communication Channels

**GitHub:**
- [Issues](https://github.com/konradRduch/Grocipes/issues) - Bug reports and feature requests
- [Discussions](https://github.com/konradRduch/Grocipes/discussions) - General questions and ideas
- [Pull Requests](https://github.com/konradRduch/Grocipes/pulls) - Code contributions

### Getting Help

**Questions?**
- Check the [User Guide](./Documentation/USER_GUIDE.md)
- Review the [Developer Guide](./Documentation/DEVELOPER_GUIDE.md)
- Search existing issues and discussions
- Create a new discussion if needed

---

## Recognition

### Contributors

All contributors are recognized in:
- GitHub Contributors list
- Project README (for significant contributions)
- Release notes

### Types of Recognition

- **Code Contributors:** Listed in README
- **Bug Reporters:** Mentioned in issue resolution
- **Documentation Improvers:** Credited in docs
- **Reviewers:** Acknowledged in PRs

---

## Development Resources

### Helpful Links

- [Project README](./README.md)
- [Quick Start Guide](./Documentation/QUICK_START.md)
- [API Documentation](./Documentation/API_DOCUMENTATION.md)
- [Developer Guide](./Documentation/DEVELOPER_GUIDE.md)

### Technology Documentation

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Angular](https://angular.io/docs)
- [MySQL](https://dev.mysql.com/doc/)
- [Docker](https://docs.docker.com/)

---

## License

By contributing to Grocipes, you agree that your contributions will be licensed under the [MIT License](./LICENSE).

---

## Questions?

If you have questions about contributing:
- Open a discussion on GitHub
- Check existing documentation
- Reach out to maintainers

---

**Thank you for contributing to Grocipes! Your efforts help make nutrition management accessible to everyone.** 🙏

**Happy Contributing! 🚀**
