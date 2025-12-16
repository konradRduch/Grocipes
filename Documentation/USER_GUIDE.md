# User Guide 👥

## Table of Contents
1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
3. [Account Management](#account-management)
4. [Managing Products](#managing-products)
5. [Creating Recipes](#creating-recipes)
6. [Meal Planning](#meal-planning)
7. [Shopping Lists](#shopping-lists)
8. [Nutritional Goals](#nutritional-goals)
9. [Tracking Progress](#tracking-progress)
10. [Tips & Best Practices](#tips--best-practices)
11. [FAQ](#faq)
12. [Troubleshooting](#troubleshooting)

---

## Introduction

Welcome to **Grocipes**! This comprehensive guide will help you make the most of the application's features to manage your nutrition, plan meals, and track your health goals.

### What is Grocipes?

Grocipes is an all-in-one nutrition management platform that helps you:
- 🥗 Track your food and nutritional intake
- 🍲 Create and manage custom recipes
- 🛒 Generate smart shopping lists
- 📊 Set and monitor nutritional goals
- 📈 Track body measurements and progress

---

## Getting Started

### System Requirements

**Browser Compatibility:**
- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+

**Internet Connection:**
- Required for initial setup and sync
- Offline mode: Limited functionality

### First Time Login

1. **Open the Application**
   - Navigate to the Grocipes URL provided by your administrator
   - Default: http://localhost (if running locally)

2. **Create Your Account**
   - Click "Register" or "Sign Up"
   - You'll be redirected to the registration page

---

## Account Management

### Registration

#### Step 1: Fill in Your Details

```
┌────────────────────────────────────┐
│      Create Your Account          │
├────────────────────────────────────┤
│ Email:    [your@email.com       ] │
│ Password: [****************     ] │
│ Name:     [John                 ] │
│ Surname:  [Doe                  ] │
│ Birthday: [1990-05-15           ] │
│ Gender:   [Male ▼               ] │
│                                    │
│ [  Create Account  ]               │
└────────────────────────────────────┘
```

**Email:** Your unique identifier (required)
- Must be a valid email format
- Will be used for login

**Password:** Secure your account (required)
- Minimum 8 characters recommended
- Use a mix of letters, numbers, and symbols

**Name & Surname:** Your personal information
- Used for personalization

**Birthday:** Your date of birth (required)
- Used for age-based calculations
- Format: YYYY-MM-DD

**Gender:** Select your gender
- Options: Male, Female, Other
- Used for nutritional calculations

#### Step 2: Automatic Setup

Upon registration, Grocipes automatically:
- Creates your default Shopping Schedule
- Creates your default Nutrition Schedule
- Generates your JWT authentication token
- Logs you in automatically

#### Step 3: Complete Your Profile

After registration:
1. Navigate to **Profile** section
2. Add your body measurements (highly recommended)
   - Current weight
   - Height
   - Optional: waist, chest, hips measurements

### Login

#### Standard Login

1. Navigate to the login page
2. Enter your email and password
3. Click "Login"

```
┌────────────────────────────────────┐
│          Welcome Back!            │
├────────────────────────────────────┤
│ Email:    [your@email.com       ] │
│ Password: [****************     ] │
│                                    │
│ [ ] Remember me                    │
│                                    │
│ [       Login       ]              │
│                                    │
│ Don't have an account? Register    │
└────────────────────────────────────┘
```

#### Logout

- Click your profile icon (top-right)
- Select "Logout"

**Security Note:** Your session automatically expires after 24 hours of inactivity.

---

## Managing Products

Products are the building blocks of recipes and shopping lists. Browse the existing product database or, if you're an admin, add custom products.

### Viewing Products

1. **Navigate to Products**
   - Click "Products" or "Groceries" in the main menu

2. **Browse Products**
   - View all available products
   - Each product shows:
     - Name
     - Image
     - Price
     - Calories per 100g
     - Nutritional information

3. **Search Products**
   - Use the search bar to find specific items
   - Filter by category or nutritional content

### Product Details

Click on any product to view detailed information:

```
┌──────────────────────────────────────────┐
│  🍗 Chicken Breast                       │
├──────────────────────────────────────────┤
│  Price: $5.99 / 100g                     │
│  Calories: 165 kcal                      │
│                                          │
│  Nutritional Facts (per 100g):          │
│  • Protein:        31.0g                 │
│  • Fat:            3.6g                  │
│  • Carbohydrates:  0.0g                  │
│  • Fiber:          0.0g                  │
│  • Sugar:          0.0g                  │
│                                          │
│  [Add to Recipe] [Add to Shopping List]  │
└──────────────────────────────────────────┘
```

### Adding Products (Admin Only)

If you have admin privileges:

1. **Click "Add Product"**

2. **Fill in Product Information**
   - Name (required)
   - Weight/Unit (e.g., 100g, 1 piece)
   - Price
   - Upload image
   - Calories per 100g

3. **Add Nutritional Facts**
   - Protein
   - Fat
   - Carbohydrates
   - Fiber
   - Sugar
   - Vitamins and minerals (optional)

4. **Save Product**
   - Click "Create Product"
   - Product is now available to all users

### Editing Products (Admin Only)

1. Open product details
2. Click "Edit"
3. Modify information
4. Click "Save Changes"

### Deleting Products (Admin Only)

⚠️ **Warning:** Deleting a product will remove it from all recipes and shopping lists.

1. Open product details
2. Click "Delete"
3. Confirm deletion

---

## Creating Recipes

Recipes combine multiple products with preparation instructions. They automatically calculate total nutritional values.

### Creating a New Recipe

#### Step 1: Basic Information

1. **Navigate to Recipes**
   - Click "Recipes" in the main menu

2. **Click "Add Recipe"**

3. **Fill in Details**
   - Title (e.g., "Grilled Chicken Salad")
   - Description (brief overview)
   - Meal Type:
     - Breakfast
     - Lunch
     - Dinner
     - Snack
   - Upload image (optional)

```
┌────────────────────────────────────────┐
│      Create New Recipe                │
├────────────────────────────────────────┤
│ Title:       [Grilled Chicken Salad ] │
│                                        │
│ Description: [A healthy, protein-   ] │
│              [packed salad perfect  ] │
│              [for lunch             ] │
│                                        │
│ Meal Type:   [Lunch ▼              ] │
│                                        │
│ Image:       [Upload Image]            │
└────────────────────────────────────────┘
```

#### Step 2: Add Ingredients

1. **Click "Add Ingredient"**

2. **Search for Product**
   - Type product name
   - Select from dropdown

3. **Specify Quantity**
   - Amount (e.g., 150)
   - Unit (grams, pieces, ml, etc.)

4. **Repeat for all ingredients**

Example:
```
Ingredients:
• Chicken Breast - 150g
• Lettuce - 100g
• Tomatoes - 80g
• Olive Oil - 10ml
• Balsamic Vinegar - 5ml
```

#### Step 3: Preparation Method

Write step-by-step instructions:

```
Preparation:

1. Season chicken breast with salt and pepper
2. Grill chicken for 6-7 minutes per side
3. Let chicken rest for 5 minutes, then slice
4. Chop lettuce and tomatoes
5. Arrange greens on a plate
6. Top with sliced chicken
7. Drizzle with olive oil and balsamic vinegar
8. Serve immediately
```

#### Step 4: Review Nutritional Information

Grocipes automatically calculates:
- Total calories
- Protein (g)
- Fat (g)
- Carbohydrates (g)
- Other nutrients

```
┌────────────────────────────────────┐
│  Nutritional Summary              │
├────────────────────────────────────┤
│  Total Calories:    350 kcal      │
│  Protein:           42g            │
│  Fat:               12g            │
│  Carbohydrates:     18g            │
│                                    │
│  Servings:          1              │
└────────────────────────────────────┘
```

#### Step 5: Save Recipe

Click "Create Recipe" to save.

### Viewing Recipes

1. **Browse Recipe List**
   - View all your recipes
   - Filter by meal type
   - Search by name

2. **View Recipe Details**
   - Click on any recipe
   - See full ingredients list
   - Read preparation method
   - View nutritional breakdown

### Editing Recipes

1. Open recipe details
2. Click "Edit Recipe"
3. Modify information
4. Click "Save Changes"

### Deleting Recipes

1. Open recipe details
2. Click "Delete Recipe"
3. Confirm deletion

---

## Meal Planning

Plan your meals in advance using the Nutrition Schedule.

### Scheduling a Meal

1. **Navigate to Nutrition Schedule**
   - Click "Meal Planning" or "Calendar"

2. **Select a Date**
   - Click on the calendar date
   - Or use date picker

3. **Add Meal**
   - Click "Add Meal"
   - Select a recipe
   - Choose meal time

```
┌────────────────────────────────────┐
│  Schedule Meal                    │
├────────────────────────────────────┤
│ Date:    [2025-12-20 ▼         ] │
│ Time:    [12:00     ▼          ] │
│ Recipe:  [Grilled Chicken Salad▼] │
│                                    │
│ [     Schedule Meal     ]          │
└────────────────────────────────────┘
```

### Viewing Your Meal Schedule

**Daily View:**
- See all meals for a specific day
- View total daily nutrition

**Weekly View:**
- Overview of the week's meals
- Plan ahead efficiently

**Monthly View:**
- Big picture of your meal planning
- Identify patterns

### Marking Meals as Eaten

1. **Find scheduled meal**
2. **Click "Mark as Eaten"**
3. **Rate the meal (optional)**
   - 1-5 stars
4. **Add comments (optional)**
   - "Delicious! Will make again"
   - "Too spicy for my taste"

### Daily Nutritional Summary

View your daily intake:

```
┌────────────────────────────────────┐
│  Daily Nutrition - Dec 20, 2025   │
├────────────────────────────────────┤
│  Calories:   1850 / 2000 kcal     │
│  ████████████████░░░░░  92%       │
│                                    │
│  Protein:    145 / 150g            │
│  ████████████████░░░░░  97%       │
│                                    │
│  Carbs:      180 / 200g            │
│  ██████████████░░░░░░░  90%       │
│                                    │
│  Fat:        58 / 65g              │
│  ███████████████░░░░░░  89%       │
└────────────────────────────────────┘
```

---

## Shopping Lists

Generate smart shopping lists based on your meal plan or create custom lists.

### Creating a Shopping List

#### Method 1: From Recipes (Smart Generation)

1. **Navigate to Shopping Lists**

2. **Click "Create from Recipes"**

3. **Select Recipes**
   - Choose multiple recipes
   - Specify servings for each

4. **Auto-Generate List**
   - System combines ingredients
   - Calculates total quantities
   - Groups by category

#### Method 2: Manual Creation

1. **Click "New Shopping List"**

2. **Name Your List**
   - e.g., "Weekly Groceries"

3. **Set Shopping Date**
   - When you plan to shop

4. **Choose Color Tag**
   - Organize visually

5. **Add Items Manually**
   - Search products
   - Specify quantities

```
┌────────────────────────────────────┐
│  Create Shopping List             │
├────────────────────────────────────┤
│ Name:        [Weekly Groceries  ] │
│ Date:        [2025-12-22 ▼      ] │
│ Color:       [🟢 🔵 🟡 🔴 🟣  ] │
│                                    │
│ Items:                             │
│ ☐ Chicken Breast - 500g           │
│ ☐ Eggs - 12 pieces                │
│ ☐ Milk - 2L                        │
│ ☐ Bread - 1 loaf                  │
│                                    │
│ [Add Item] [Save List]             │
└────────────────────────────────────┘
```

### Using the Shopping List

1. **View Your Lists**
   - All lists organized by date
   - Upcoming lists highlighted

2. **While Shopping**
   - Open list on mobile
   - Check off items as you buy them
   - System tracks what's purchased

3. **Mark Items as Bought**
   ```
   ☑ Chicken Breast - 500g
   ☑ Eggs - 12 pieces
   ☐ Milk - 2L
   ☐ Bread - 1 loaf
   ```

4. **View Progress**
   - See completion percentage
   - Estimated total cost

### Managing Lists

**Favorite Lists:**
- Mark frequently used lists as favorites
- Quick access for weekly shopping

**Edit List:**
- Add/remove items
- Change quantities
- Update dates

**Delete List:**
- Remove completed lists
- Clean up old lists

**Share List (Future Feature):**
- Share with family members
- Collaborate on shopping

---

## Nutritional Goals

Set personalized goals and track your progress toward better health.

### Setting a Nutritional Goal

#### Step 1: Navigate to Goals

1. Click "Nutritional Goals" or "Goals"
2. Click "Create New Goal"

#### Step 2: Choose Goal Type

Select your primary objective:

**Weight Loss**
- Reduce body weight
- Caloric deficit plan
- Recommended: 0.5-1kg per week

**Weight Gain**
- Increase body weight
- Caloric surplus plan
- Recommended: 0.25-0.5kg per week

**Muscle Gain**
- Build muscle mass
- High protein focus
- Moderate caloric surplus

**Maintenance**
- Maintain current weight
- Balanced nutrition
- Sustain healthy habits

#### Step 3: Set Target Weight

```
┌────────────────────────────────────┐
│  Set Your Goal                    │
├────────────────────────────────────┤
│ Goal Type:   [Weight Loss ▼     ] │
│                                    │
│ Current:     [82.5 kg           ] │
│ Target:      [75.0 kg           ] │
│                                    │
│ Start Date:  [2025-12-20 ▼      ] │
│                                    │
│ Estimated:   ~150 days             │
└────────────────────────────────────┘
```

#### Step 4: Activity Level

Select your typical activity level:

**Sedentary**
- Office job, little exercise
- Multiplier: 1.2

**Lightly Active**
- Light exercise 1-3 days/week
- Multiplier: 1.375

**Moderately Active**
- Moderate exercise 3-5 days/week
- Multiplier: 1.55

**Very Active**
- Hard exercise 6-7 days/week
- Multiplier: 1.725

**Extremely Active**
- Very hard exercise & physical job
- Multiplier: 1.9

#### Step 5: Review Calculations

Grocipes calculates your daily requirements:

```
┌────────────────────────────────────┐
│  Daily Requirements               │
├────────────────────────────────────┤
│  Calories:        1850 kcal       │
│  Protein:         140g (30%)      │
│  Carbohydrates:   185g (40%)      │
│  Fat:             62g (30%)       │
│                                    │
│  Based on:                         │
│  • Age: 35                         │
│  • Height: 180cm                   │
│  • Current Weight: 82.5kg          │
│  • Activity: Moderate              │
│  • Goal: Weight Loss               │
└────────────────────────────────────┘
```

#### Step 6: Save Goal

Click "Create Goal" to activate.

### Tracking Your Goal

**Daily Progress:**
- Compare actual intake vs. target
- Visual progress bars
- Nutritional breakdown

**Weekly Summary:**
- Average daily calories
- Weight changes
- Adherence percentage

**Overall Progress:**
```
┌────────────────────────────────────┐
│  Weight Loss Goal Progress        │
├────────────────────────────────────┤
│  Start:    82.5 kg                │
│  Current:  80.0 kg  (-2.5 kg)     │
│  Target:   75.0 kg                 │
│                                    │
│  Progress: ████████░░░░░  33%     │
│                                    │
│  Days Elapsed:  50 / 150           │
│  On Track:      ✓ Yes              │
│  Est. Completion: Apr 15, 2026     │
└────────────────────────────────────┘
```

### Adjusting Your Goal

Goals can be modified:
- Update target weight
- Change activity level
- Adjust timeline
- System recalculates requirements

---

## Tracking Progress

Monitor your journey with comprehensive tracking tools.

### Body Measurements

#### Adding a Measurement

1. **Navigate to Profile → Body Measurements**

2. **Click "Add Measurement"**

3. **Enter Data**
   - Weight (required)
   - Height (required)
   - Waist (optional)
   - Chest (optional)
   - Hips (optional)
   - Date is auto-set to today

```
┌────────────────────────────────────┐
│  Add Body Measurement             │
├────────────────────────────────────┤
│ Date:    [2025-12-20] (auto)      │
│                                    │
│ Weight:  [80.0    ] kg            │
│ Height:  [180     ] cm            │
│ Waist:   [84      ] cm            │
│ Chest:   [100     ] cm            │
│ Hips:    [94      ] cm            │
│                                    │
│ [  Save Measurement  ]             │
└────────────────────────────────────┘
```

#### Viewing Measurement History

**Table View:**
- Chronological list
- All measurements
- Compare changes

**Graph View:**
- Weight trend over time
- Visual progress
- Identify plateaus

```
Weight Trend
│
85kg ┤        ●
    │      ●   ●
80kg ┤    ●       ●
    │  ●           ●
75kg ┤                ●
    └─────────────────────
     Jan  Feb  Mar  Apr
```

### BMI Calculation

Automatically calculated from height and weight:

```
┌────────────────────────────────────┐
│  Body Mass Index (BMI)            │
├────────────────────────────────────┤
│  Current BMI:  24.7                │
│  Category:     Normal Weight       │
│                                    │
│  Underweight:  < 18.5              │
│  Normal:       18.5 - 24.9 ●       │
│  Overweight:   25.0 - 29.9         │
│  Obese:        ≥ 30.0              │
└────────────────────────────────────┘
```

### Progress Photos (Future Feature)

- Upload progress photos
- Side-by-side comparisons
- Track visual changes

---

## Tips & Best Practices

### For Meal Planning

1. **Plan Weekly:** Set aside time each week to plan meals
2. **Variety:** Include different food groups
3. **Prep Ahead:** Consider meal prep on weekends
4. **Flexibility:** Leave room for spontaneous meals
5. **Leftovers:** Plan recipes that work for leftovers

### For Shopping Lists

1. **Combine Trips:** Create weekly lists to reduce shopping frequency
2. **Check Pantry:** Review what you have before shopping
3. **Seasonal:** Take advantage of seasonal produce
4. **Bulk Items:** Buy staples in bulk for savings
5. **Organize:** Group items by store section

### For Nutritional Goals

1. **Realistic Targets:** Set achievable goals (0.5-1kg per week)
2. **Consistency:** Track daily for best results
3. **Don't Obsess:** Allow occasional treats
4. **Hydration:** Don't forget to drink water
5. **Sleep:** Get adequate rest for recovery
6. **Patience:** Sustainable change takes time

### For Tracking

1. **Regular Weigh-ins:** Same time of day (morning, after bathroom)
2. **Weekly Measurements:** Don't weigh daily (water fluctuations)
3. **Multiple Metrics:** Don't rely on scale alone
4. **Photos:** Take progress photos monthly
5. **Journal:** Note how you feel, energy levels

---

## FAQ

### General Questions

**Q: Is my data secure?**
A: Yes, passwords are encrypted, and data is stored securely. JWT tokens expire after 24 hours.

**Q: Can I use Grocipes offline?**
A: Limited functionality. Full features require internet connection.

**Q: How accurate are the nutritional calculations?**
A: Based on USDA database. Actual values may vary by brand/preparation.

**Q: Can I export my data?**
A: Currently not available. Feature planned for future release.

### Account Questions

**Q: I forgot my password. How do I reset it?**
A: Password reset feature is planned. Contact administrator for manual reset.

**Q: Can I change my email address?**
A: Currently not supported. You'll need to create a new account.

**Q: How do I delete my account?**
A: Contact administrator for account deletion.

### Product & Recipe Questions

**Q: Can I add custom products?**
A: Only admin users can add products to the global database.

**Q: Why can't I delete a product?**
A: Only admins can delete products, and only if not used in recipes.

**Q: Can I make recipes private?**
A: Currently, all recipes are visible to all users.

**Q: How do I adjust recipe servings?**
A: Edit the recipe and update ingredient quantities proportionally.

### Goal & Tracking Questions

**Q: Why isn't my weight changing?**
A: Weight loss takes time. Ensure caloric deficit and give it 2-3 weeks.

**Q: Can I have multiple active goals?**
A: Currently, only one active goal at a time is supported.

**Q: How are calories calculated?**
A: Based on Harris-Benedict equation with activity multiplier.

**Q: What if I miss a day of tracking?**
A: Just resume tracking. Consistency matters more than perfection.

---

## Troubleshooting

### Login Issues

**Problem:** "Invalid credentials" error

**Solutions:**
1. Verify email and password are correct
2. Check Caps Lock is off
3. Clear browser cache
4. Try password reset (if available)

---

**Problem:** "Token expired" message

**Solutions:**
1. Log out and log back in
2. Token expires after 24 hours of inactivity

---

### Display Issues

**Problem:** Page doesn't load or displays incorrectly

**Solutions:**
1. Refresh the page (Ctrl+R or Cmd+R)
2. Clear browser cache
3. Try incognito/private mode
4. Update browser to latest version
5. Try different browser

---

**Problem:** Images not showing

**Solutions:**
1. Check internet connection
2. Images may be loading slowly
3. Try refreshing page

---

### Data Issues

**Problem:** Recipe calculations seem wrong

**Solutions:**
1. Verify ingredient quantities
2. Check product nutritional data
3. Recalculate by editing and saving recipe

---

**Problem:** Shopping list missing items

**Solutions:**
1. Ensure recipes were selected
2. Check that products exist in database
3. Try recreating the list

---

### Performance Issues

**Problem:** Application is slow

**Solutions:**
1. Check internet speed
2. Close other browser tabs
3. Clear browser cache
4. Restart browser

---

## Getting Help

### Support Channels

**Documentation:**
- Check this user guide
- Review API documentation
- Read developer guide

**Community:**
- GitHub Issues: Report bugs
- Discussions: Ask questions
- Forum: Share tips

**Contact:**
- See repository for contact information
- Response time: 1-3 business days

---

## Conclusion

Congratulations on taking control of your nutrition journey with Grocipes! Remember:

✅ Set realistic goals
✅ Track consistently
✅ Plan ahead
✅ Stay flexible
✅ Celebrate progress

**Happy meal planning! 🥗🍲🛒**

---

**Document Version:** 1.0
**Last Updated:** December 16, 2025
