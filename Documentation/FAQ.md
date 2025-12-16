# Frequently Asked Questions (FAQ) ❓

Common questions and answers about Grocipes.

---

## Table of Contents

1. [General Questions](#general-questions)
2. [Account & Security](#account--security)
3. [Products & Recipes](#products--recipes)
4. [Shopping Lists](#shopping-lists)
5. [Nutritional Goals](#nutritional-goals)
6. [Technical Questions](#technical-questions)
7. [Troubleshooting](#troubleshooting)

---

## General Questions

### What is Grocipes?

Grocipes is a comprehensive nutrition management application that helps you:
- Track groceries and nutritional information
- Create and manage recipes
- Generate smart shopping lists
- Set and monitor nutritional goals
- Track body measurements and progress

---

### Is Grocipes free to use?

Yes, Grocipes is an open-source project released under the MIT License. You can use, modify, and distribute it freely.

---

### What platforms does Grocipes support?

Grocipes is a web application that works on:
- **Desktop:** Windows, macOS, Linux
- **Browsers:** Chrome, Firefox, Safari, Edge (latest versions)
- **Mobile:** Responsive design works on mobile browsers

A dedicated mobile app is planned for future releases.

---

### Do I need an internet connection?

Yes, Grocipes requires an internet connection to:
- Sync data with the server
- Access product database
- Save recipes and shopping lists

Offline mode with limited functionality is planned for future releases.

---

## Account & Security

### How do I create an account?

1. Navigate to the Grocipes application
2. Click "Register" or "Sign Up"
3. Fill in required information:
   - Email (used for login)
   - Password
   - Name and surname
   - Birthday
   - Gender
4. Click "Create Account"

You'll be automatically logged in after registration.

---

### Is my data secure?

Yes, Grocipes implements several security measures:
- ✅ Passwords are encrypted with BCrypt
- ✅ JWT token-based authentication
- ✅ Secure HTTP-only communication
- ✅ Role-based access control
- ✅ Tokens expire after 24 hours

---

### I forgot my password. How do I reset it?

Password reset functionality is currently in development. Please contact your system administrator to manually reset your password.

**Planned for version 1.1.0**

---

### Can I change my email address?

Currently, email addresses cannot be changed after registration. This feature is planned for a future update.

**Workaround:** Create a new account with the desired email address.

---

### How do I delete my account?

Account deletion must be requested through your system administrator. They can permanently remove your account and all associated data.

Self-service account deletion is planned for a future release.

---

### What happens to my data if I don't log in for a long time?

Your data remains safe and accessible. There is no automatic account expiration or data deletion based on inactivity.

---

## Products & Recipes

### Where do the products come from?

Products are added by administrators and based on verified nutritional databases (USDA and similar). All nutritional information is per 100g/100ml.

---

### Can I add my own products?

Only users with Admin privileges can add products to the global database. This ensures data quality and accuracy.

**Regular users can:**
- Browse all products
- Search products
- Use products in recipes
- Request new products (contact admin)

---

### How are recipe calories calculated?

Recipe calories are automatically calculated by:
1. Taking each ingredient's amount
2. Calculating nutritional values based on product data
3. Summing all ingredients
4. Displaying total per serving

**Example:**
- 150g Chicken Breast (165 kcal/100g) = 248 kcal
- 100g Lettuce (15 kcal/100g) = 15 kcal
- **Total: 263 kcal**

---

### Can I share my recipes with others?

Currently, all recipes are visible to all users but only editable by their creators. Recipe sharing with privacy controls is planned for version 1.2.0.

---

### Can I import recipes from websites?

Recipe import from URLs is not currently available but is planned for version 1.1.0.

**Current workaround:** Manually create the recipe by copying ingredient information.

---

### Why can't I delete a recipe?

You can only delete recipes that you created. If you're unable to delete:
- Verify you're the recipe creator
- Check you're logged in with the correct account
- Contact admin if you believe there's an error

---

### Can I print recipes?

Recipe export to PDF is planned for version 1.1.0. Current workaround: Use browser print (Ctrl+P / Cmd+P).

---

## Shopping Lists

### How do I create a shopping list from recipes?

1. Navigate to Shopping Lists
2. Click "Create from Recipes"
3. Select one or more recipes
4. Specify number of servings
5. System automatically:
   - Combines ingredients
   - Calculates quantities
   - Groups by category
6. Review and adjust
7. Save the list

---

### Can I share shopping lists with family?

Shopping list sharing is not currently available but is planned for version 1.2.0.

**Current workaround:** Create lists on one account and share login credentials (not recommended for security).

---

### Why are some items duplicated in my shopping list?

Items appear separately if they use different units. For example:
- 200g Chicken (grams)
- 1 Chicken Breast (pieces)

**Solution:** Manually combine or adjust quantities when creating the list.

---

### Can I reorder items in my shopping list?

Items are currently organized by category/product. Manual reordering is planned for a future release.

---

### Do shopping lists sync across devices?

Yes! Shopping lists are stored on the server and accessible from any device where you're logged in.

---

## Nutritional Goals

### How are daily calorie needs calculated?

Grocipes uses the Harris-Benedict equation:

**For Men:**
BMR = 88.362 + (13.397 × weight in kg) + (4.799 × height in cm) - (5.677 × age)

**For Women:**
BMR = 447.593 + (9.247 × weight in kg) + (3.098 × height in cm) - (4.330 × age)

Then multiplies by activity level:
- Sedentary: 1.2
- Lightly Active: 1.375
- Moderately Active: 1.55
- Very Active: 1.725
- Extremely Active: 1.9

For weight loss, reduces by 500 kcal/day.
For weight gain, adds 300-500 kcal/day.

---

### Why isn't my weight changing despite following the goal?

Weight loss/gain takes time. Factors include:
- **Water retention:** Can fluctuate 1-2kg daily
- **Muscle gain:** Muscle weighs more than fat
- **Measurement timing:** Weigh at the same time daily
- **Accuracy:** Ensure you're tracking all food

**Recommendation:** Track for at least 2-3 weeks before adjusting.

---

### Can I have multiple nutritional goals?

Currently, only one active goal at a time is supported. Multi-goal support is planned for future releases.

---

### How often should I update my measurements?

**Recommended frequency:**
- **Weight:** Weekly (same day/time)
- **Body measurements:** Every 2-4 weeks
- **Photos:** Monthly

Avoid daily weighing due to natural fluctuations.

---

### What's a realistic weight loss goal?

**Safe and sustainable rates:**
- **Weight Loss:** 0.5-1kg (1-2 lbs) per week
- **Weight Gain:** 0.25-0.5kg (0.5-1 lb) per week
- **Muscle Gain:** Slower, 0.25-0.5kg per week

Faster rates may not be sustainable or healthy.

---

## Technical Questions

### What technologies does Grocipes use?

**Backend:**
- Java 17
- Spring Boot 3.3.3
- MySQL 8.0
- JWT Authentication

**Frontend:**
- Angular 16
- TypeScript 5.1
- Bootstrap 5
- Chart.js

**DevOps:**
- Docker
- Docker Compose
- Nginx

---

### Can I run Grocipes locally?

Yes! See the [Quick Start Guide](./QUICK_START.md) for instructions.

**Quickest method (Docker):**
```bash
git clone https://github.com/konradRduch/Grocipes.git
cd Grocipes/Backend/grocipes
docker-compose up --build
```

---

### Is there an API?

Yes! Grocipes provides a comprehensive REST API. See [API Documentation](./API_DOCUMENTATION.md) for details.

**Base URL:** `http://localhost:8080`
**Authentication:** JWT Bearer tokens

---

### Can I contribute to Grocipes?

Absolutely! Contributions are welcome. See [CONTRIBUTING.md](../CONTRIBUTING.md) for guidelines.

**Ways to contribute:**
- Report bugs
- Suggest features
- Improve documentation
- Submit code (pull requests)
- Test and provide feedback

---

### Where is the source code?

Grocipes is open source and available on GitHub:
**Repository:** https://github.com/konradRduch/Grocipes

---

### What's the license?

Grocipes is licensed under the MIT License. You can:
- ✅ Use commercially
- ✅ Modify
- ✅ Distribute
- ✅ Use privately

See [LICENSE](../LICENSE) for full terms.

---

## Troubleshooting

### I can't log in

**Possible causes and solutions:**

1. **Wrong email/password**
   - Verify credentials
   - Check Caps Lock
   - Try password reset (if available)

2. **Token expired**
   - Tokens last 24 hours
   - Clear browser cache
   - Try logging in again

3. **Account doesn't exist**
   - Verify you registered
   - Check email spelling
   - Create a new account if needed

---

### The application is slow

**Solutions:**

1. **Check internet connection**
   - Verify speed is adequate
   - Try different network

2. **Clear browser cache**
   - Chrome: Ctrl+Shift+Delete
   - Settings → Clear browsing data

3. **Try different browser**
   - Chrome (recommended)
   - Firefox
   - Edge

4. **Close other tabs**
   - Free up system resources

---

### Images aren't loading

**Solutions:**

1. **Refresh the page** (Ctrl+R / Cmd+R)
2. **Clear browser cache**
3. **Check internet connection**
4. **Wait a moment** (images may load slowly)
5. **Try different browser**

---

### "Token invalid" error

**Cause:** JWT token expired (24-hour lifespan)

**Solution:**
1. Log out
2. Log back in
3. New token will be generated

---

### Recipe calculations seem wrong

**Troubleshooting steps:**

1. **Verify ingredient quantities**
   - Check amounts are correct
   - Ensure proper units

2. **Check product data**
   - View product nutritional info
   - Verify it's correct

3. **Recalculate**
   - Edit recipe
   - Save (triggers recalculation)

4. **Still wrong?**
   - Report as a bug
   - Include recipe details

---

### Shopping list items missing

**Possible causes:**

1. **Products not in database**
   - Some recipe ingredients may not exist as products
   - Admin needs to add them

2. **Recipe not selected**
   - Verify you selected the recipe
   - Try recreating the list

3. **Bug**
   - Report with recipe details

---

### Can't add products (not admin)

Only admin users can add products. This is intentional to maintain data quality.

**Solution:** Request admin to add the product, or request admin privileges.

---

### BMI calculation seems off

**Verify:**
1. Height in centimeters
2. Weight in kilograms
3. Recent measurements

**BMI formula:** weight(kg) / (height(m))²

**Example:**
- Height: 180cm = 1.8m
- Weight: 80kg
- BMI: 80 / (1.8 × 1.8) = 24.7

---

### Database connection failed (developers)

**Solutions:**

1. **Verify MySQL is running**
   ```bash
   # Check MySQL service
   # Windows
   net start MySQL80
   
   # Linux/Mac
   sudo systemctl status mysql
   ```

2. **Check credentials**
   - Verify `application.properties`
   - Username/password correct
   - Database exists

3. **Check port**
   - Default: 33062
   - Verify not in use

4. **Test connection**
   ```bash
   mysql -u root -p -h localhost -P 33062
   ```

---

## Still Have Questions?

### Where to Get Help

1. **Check Documentation**
   - [User Guide](./USER_GUIDE.md)
   - [Developer Guide](./DEVELOPER_GUIDE.md)
   - [API Documentation](./API_DOCUMENTATION.md)

2. **Search Existing Issues**
   - [GitHub Issues](https://github.com/konradRduch/Grocipes/issues)

3. **Ask the Community**
   - [GitHub Discussions](https://github.com/konradRduch/Grocipes/discussions)

4. **Report a Bug**
   - [Create New Issue](https://github.com/konradRduch/Grocipes/issues/new)
   - Include details and steps to reproduce

5. **Contact Maintainer**
   - See repository for contact information

---

## Planned Features FAQ

### When will [feature X] be available?

Check the [CHANGELOG.md](../CHANGELOG.md) for planned features and tentative release dates.

**Upcoming features:**
- Password reset (v1.1.0)
- Recipe import from URL (v1.1.0)
- Recipe PDF export (v1.1.0)
- Recipe sharing (v1.2.0)
- Mobile app (v2.0.0)
- Barcode scanner (v2.0.0)

---

### Can I request a feature?

Yes! Feature requests are welcome:
1. Check if already requested
2. Open a new issue
3. Use "Feature Request" template
4. Describe your idea clearly

See [CONTRIBUTING.md](../CONTRIBUTING.md#suggesting-features)

---

## Common Misconceptions

### "I need to pay for Grocipes"

**False.** Grocipes is completely free and open-source under MIT License.

---

### "My data is sold to third parties"

**False.** Grocipes doesn't sell data. It's a self-hosted application where you control the data.

---

### "I need a smartphone to use Grocipes"

**False.** Grocipes works on any device with a modern web browser (desktop, laptop, tablet, or smartphone).

---

### "All products are from my country"

Products in the database are generic and nutritional values are standardized (per 100g). Admins can add region-specific products as needed.

---

## Tips & Best Practices

### For Weight Loss
- ✅ Set realistic goals (0.5-1kg/week)
- ✅ Track consistently
- ✅ Measure weekly, not daily
- ✅ Focus on trends, not daily fluctuations
- ✅ Combine with exercise

### For Meal Planning
- ✅ Plan weekly on weekends
- ✅ Include variety
- ✅ Prep ingredients in advance
- ✅ Leave room for flexibility
- ✅ Reuse favorite recipes

### For Shopping Lists
- ✅ Check pantry before shopping
- ✅ Combine weekly trips
- ✅ Buy seasonal produce
- ✅ Mark favorites for quick access
- ✅ Group by store section

---

**Can't find your answer? Ask in [GitHub Discussions](https://github.com/konradRduch/Grocipes/discussions)!**

---

**Last Updated:** December 16, 2025
