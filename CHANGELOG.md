# Changelog

All notable changes to the Grocipes project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

### Planned Features
- User profile photo upload
- Recipe sharing between users
- Advanced meal plan templates
- Grocery price tracking and trends
- Barcode scanner for products
- Mobile application (iOS/Android)
- Recipe rating and reviews system
- Social features (follow users, share recipes)
- Meal prep recommendations
- Ingredient substitution suggestions

---

## [1.0.0] - 2025-12-16

### Added

#### Authentication & User Management
- JWT-based authentication system
- User registration with email validation
- Secure login functionality
- Role-based access control (USER, ADMIN)
- User profile management
- Password encryption with BCrypt

#### Product Management
- Browse all available products
- View detailed product information
- Add new products (Admin only)
- Edit existing products (Admin only)
- Delete products (Admin only)
- Comprehensive nutritional information per 100g
- Product images support
- Price tracking per unit

#### Recipe Management
- Create custom recipes with multiple ingredients
- Automatic nutritional calculation per recipe
- Recipe categorization (Breakfast, Lunch, Dinner, Snack)
- Recipe image upload
- Step-by-step preparation instructions
- Edit existing recipes
- Delete recipes
- View recipe nutritional breakdown

#### Shopping Lists
- Create shopping lists manually
- Auto-generate shopping lists from recipes
- Schedule shopping by date
- Color-coded list organization
- Mark items as purchased
- Edit shopping list items
- Delete shopping lists
- Mark lists as favorites

#### Nutritional Goals
- Set personalized nutritional goals
- Choose goal types:
  - Weight Loss
  - Weight Gain
  - Muscle Gain
  - Maintenance
- Activity level selection (5 levels)
- Automatic daily caloric needs calculation
- Macronutrient distribution calculation
- Estimated time to goal completion
- Track progress toward goals

#### Meal Planning
- Schedule meals on calendar
- View daily, weekly, and monthly meal plans
- Mark meals as eaten
- Rate completed meals (1-5 stars)
- Add comments to meals
- Automatic daily nutrition tracking
- View nutritional summaries

#### Progress Tracking
- Log body measurements:
  - Weight
  - Height
  - Waist
  - Chest
  - Hips
- View measurement history
- Automatic BMI calculation
- BMI category classification
- Visual progress indicators

#### Technical Features
- RESTful API architecture
- MySQL database with JPA/Hibernate
- Docker containerization support
- Docker Compose orchestration
- Responsive UI with Bootstrap 5
- Chart.js integration for data visualization
- Angular 16 single-page application
- Spring Boot 3.3.3 backend
- JWT token-based API security

### Documentation
- Comprehensive README with badges
- Detailed API Documentation (32+ endpoints)
- Complete Developer Guide
- User Guide with step-by-step instructions
- Quick Start Guide for all user types
- Contributing guidelines
- MIT License
- This Changelog

### Infrastructure
- Docker support for all services
- Docker Compose configuration
- MySQL database containerization
- Nginx for frontend serving
- Maven build configuration
- Angular build optimization

---

## Version History

### Version Numbering

Grocipes follows [Semantic Versioning](https://semver.org/):

**MAJOR.MINOR.PATCH**
- **MAJOR**: Incompatible API changes
- **MINOR**: New features (backward compatible)
- **PATCH**: Bug fixes (backward compatible)

---

## Release Notes

### [1.0.0] - Initial Release

**Release Date:** December 16, 2025

**Highlights:**
- 🎉 First stable release of Grocipes
- ✅ Complete nutrition management platform
- 📱 Responsive web application
- 🔐 Secure authentication system
- 📊 Comprehensive tracking features
- 🐳 Docker-ready deployment

**Statistics:**
- 32+ REST API endpoints
- 13 controller classes
- 15+ database entities
- 20+ Angular components
- 100% feature coverage documentation

**Known Issues:**
- None reported at release

**Breaking Changes:**
- None (initial release)

**Migration Guide:**
- None (initial release)

**Contributors:**
- Konrad Rduch (@konradRduch) - Lead Developer

---

## Future Roadmap

### Version 1.1.0 (Planned Q1 2026)
- [ ] Password reset functionality
- [ ] Email notifications
- [ ] Export recipes to PDF
- [ ] Import recipes from URL
- [ ] Recipe search with filters
- [ ] Favorite recipes feature

### Version 1.2.0 (Planned Q2 2026)
- [ ] Social features (user following)
- [ ] Recipe sharing
- [ ] Recipe comments and ratings
- [ ] Shopping list sharing
- [ ] Collaborative meal planning

### Version 2.0.0 (Planned Q3 2026)
- [ ] Mobile application (React Native)
- [ ] Barcode scanner
- [ ] Offline mode
- [ ] Advanced analytics
- [ ] AI meal suggestions
- [ ] Integration with fitness trackers

---

## Deprecations

None at this time.

---

## Security

### Security Advisories

None at this time.

### Reporting Security Issues

Please report security vulnerabilities by:
1. Opening a security advisory on GitHub
2. Contacting the maintainer directly
3. **Do not** create public issues for security problems

---

## Support

### Supported Versions

| Version | Supported          | End of Life |
| ------- | ------------------ | ----------- |
| 1.0.x   | :white_check_mark: | TBD         |

### Getting Help

- Check the [User Guide](./Documentation/USER_GUIDE.md)
- Review [API Documentation](./Documentation/API_DOCUMENTATION.md)
- Open an [issue](https://github.com/konradRduch/Grocipes/issues)
- Read [FAQ](./Documentation/USER_GUIDE.md#faq)

---

## Acknowledgments

### Special Thanks

- Spring Boot team for the excellent framework
- Angular team for the powerful frontend platform
- Bootstrap team for responsive components
- MySQL for reliable database system
- Docker for containerization technology
- All open-source contributors

### Technology Stack

Built with:
- ☕ Java 17
- 🍃 Spring Boot 3.3.3
- 🅰️ Angular 16
- 🐬 MySQL 8.0
- 🐳 Docker
- 📊 Chart.js
- 🎨 Bootstrap 5

---

## Links

- **Homepage:** [GitHub Repository](https://github.com/konradRduch/Grocipes)
- **Documentation:** [Documentation Folder](./Documentation/)
- **Issues:** [GitHub Issues](https://github.com/konradRduch/Grocipes/issues)
- **Discussions:** [GitHub Discussions](https://github.com/konradRduch/Grocipes/discussions)

---

**Note:** This changelog is manually maintained. For detailed commit history, see [GitHub Commits](https://github.com/konradRduch/Grocipes/commits/).

---

**Last Updated:** December 16, 2025
