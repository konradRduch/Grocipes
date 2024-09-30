import { Product } from "../model/product.model";
import { Recipe } from "../model/recepie.model";


export class RecipesService{

    recepies: Recipe[] = [
        new Recipe(1,'Fruid salad', 'A fruit salad is a refreshing, colorful, and healthy dish that can be enjoyed at any time of the day. Whether youre looking for a nutritious breakfast, a light snack, or a vibrant side dish for a meal, fruit salad is a versatile option that brings together the natural sweetness and flavors of fresh fruits. This recipe combines a variety of seasonal fruits to create a well-balanced and flavorful salad, packed with vitamins, minerals, and antioxidants. Best of all, its quick and easy to prepare.',
        [
            new Product(2,'Apple',50,0.99,100,'https://m.media-amazon.com/images/I/918YNa3bAaL.jpg','bialkooo'),
            new Product(3,'Banana',25,0.99,100,'https://target.scene7.com/is/image/Target/GUEST_cf4773e6-afec-4aa1-89e7-74b7dfc09973','bialkooo'),
            new Product(4,'Sugar',10,0.99,100,'https://m.media-amazon.com/images/I/810dK-eEBwL._SL1500_.jpg','bialkooo')
        ]
         ,'Start by thoroughly washing and drying all the fruits. Once ready, core and cube the apples, tossing them in lemon juice to prevent browning. Peel the bananas and slice them into rounds. Peel the orange, separate the segments, and cut each one in half. Next, remove the leaves from the strawberries and cut them into halves or quarters. Peel the kiwi and slice it into rounds, then cut those in half. Prepare the grapes by halving them if they’re large, and leave the blueberries whole. Peel and cube the mango.Once all the fruits are prepared, gently combine them in a large bowl. If desired, drizzle a tablespoon of honey over the fruit and add a touch more lemon juice to enhance the flavors. After mixing, cover the bowl and refrigerate for 15 to 30 minutes to allow the salad to cool and the flavors to meld.Finally, garnish the salad with a few fresh mint leaves before serving. Enjoy your refreshing, chilled fruit salad!','https://images.themodernproper.com/billowy-turkey/production/posts/2022/FruitSalad_Shot4_20.jpg?w=960&h=960&q=82&fm=jpg&fit=crop&dm=1654019861&s=abb0af9baa0039bf1e91942aa34af247'),

         new Recipe(2,'Cheese toast', 'A simple yet delicious comfort food, cheese toast is a quick and satisfying snack or light meal. The combination of crunchy, toasted bread with melted cheese creates a rich, savory flavor thats hard to resist. Perfect for breakfast, lunch, or even a late-night snack, this cheese toast recipe offers a warm, gooey bite with a crispy texture. Its easy to prepare and customizable with your favorite toppings or types of cheese.',
        [
            new Product(1,'Cheese',100,4.99,100,'https://t3.ftcdn.net/jpg/05/66/02/98/360_F_566029808_X7praimuCQt0MsLCmw5d65Pp5KqmTS8e.jpg','bialkooo'),
            new Product(6,'Bread',100,1.00,100,'https://i5.walmartimages.com/seo/Freshness-Guaranteed-French-Bread-14-oz_fd51f0c3-4eea-4ff1-8109-7770339b6d85.fdba2ce348744cde3840700f5e33f3d3.jpeg','bialkoo')
         
        ]
            ,'Begin by preheating your oven or toaster oven to 375°F (190°C). Slice the bread, if needed, and place the slices on a baking tray. Layer each slice with your favorite type of cheese—cheddar, mozzarella, or even a mix of both for extra flavor. Optionally, you can add a pinch of seasoning, such as garlic powder or black pepper, to enhance the taste. Bake the toast in the oven for about 5-7 minutes, or until the cheese is melted and bubbly. For an extra crispy top, switch to broil for an additional minute, but be careful not to burn the edges. Once done, remove the toast from the oven and let it cool slightly before serving. Enjoy your warm and cheesy delight, possibly with a side of tomato soup or a sprinkle of fresh herbs!','https://i.ytimg.com/vi/PcFQnteNwvo/maxresdefault.jpg'),

         new Recipe(3,'Hamburger', 'A classic favorite, the hamburger is a hearty, flavorful meal thats loved worldwide. This recipe combines a juicy beef patty, melted cheese, and a soft bun, offering the perfect balance of textures and flavors. Whether youre grilling outdoors or cooking indoors, a homemade hamburger delivers a fresh, customizable experience. You can easily add your favorite toppings, like lettuce, tomato, onions, or pickles, to create your ideal burger.',
        [
            new Product(1,'Cheese',100,4.99,100,'https://t3.ftcdn.net/jpg/05/66/02/98/360_F_566029808_X7praimuCQt0MsLCmw5d65Pp5KqmTS8e.jpg','bialkooo'),
            new Product(6,'Bread',100,1.00,100,'https://i5.walmartimages.com/seo/Freshness-Guaranteed-French-Bread-14-oz_fd51f0c3-4eea-4ff1-8109-7770339b6d85.fdba2ce348744cde3840700f5e33f3d3.jpeg','bialkoo')
         
        ]
            ,'Start by shaping your ground beef into evenly sized patties, seasoning both sides with salt, pepper, and any additional seasonings like garlic powder or paprika. Heat a grill or a skillet over medium-high heat. Once hot, cook the patties for about 4-5 minutes per side, depending on your preferred doneness. In the final minute of cooking, place a slice of cheese on top of each patty, allowing it to melt. While the patties are cooking, lightly toast the hamburger buns on the grill or in a toaster. Once the patties are cooked and the buns are toasted, assemble the burgers by placing the patty on the bottom bun, adding any desired toppings (lettuce, tomato, onion, pickles), and finally placing the top bun. Serve with a side of fries, salad, or your favorite condiments.','https://images.anovaculinary.com/sous-vide-hamburger/header/sous-vide-hamburger-header-og.jpg'),
        
        

    ]

    getRecipes(){
        return this.recepies.slice();
    }

    getRecipeByName(recipeTitle: String): Recipe | undefined{
        return this.recepies.find(product => product.title.toLowerCase() === recipeTitle.toLowerCase());
    }

    searchedRecipes(searchedName: string): Recipe[] {
        return this.recepies.filter(recipe => recipe.title.toLowerCase().includes(searchedName.toLowerCase()));
    }
    

}