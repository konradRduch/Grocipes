import { Product } from "./product.model";
import { Recipe } from "./recepie.model";

export class RecipesService{

    recepies: Recipe[] = [
        new Recipe(1,'Fruid salad', 'A fruit salad is a refreshing, colorful, and healthy dish that can be enjoyed at any time of the day. Whether youre looking for a nutritious breakfast, a light snack, or a vibrant side dish for a meal, fruit salad is a versatile option that brings together the natural sweetness and flavors of fresh fruits. This recipe combines a variety of seasonal fruits to create a well-balanced and flavorful salad, packed with vitamins, minerals, and antioxidants. Best of all, its quick and easy to prepare.',
        [
            new Product(2,'Apple',50,0.99,'https://m.media-amazon.com/images/I/918YNa3bAaL.jpg','bialkooo'),
            new Product(3,'Banana',25,0.99,'https://target.scene7.com/is/image/Target/GUEST_cf4773e6-afec-4aa1-89e7-74b7dfc09973','bialkooo'),
            new Product(4,'Sugar',10,0.99,'https://m.media-amazon.com/images/I/810dK-eEBwL._SL1500_.jpg','bialkooo')
        ]
         ,'Start by thoroughly washing and drying all the fruits. Once ready, core and cube the apples, tossing them in lemon juice to prevent browning. Peel the bananas and slice them into rounds. Peel the orange, separate the segments, and cut each one in half. Next, remove the leaves from the strawberries and cut them into halves or quarters. Peel the kiwi and slice it into rounds, then cut those in half. Prepare the grapes by halving them if they’re large, and leave the blueberries whole. Peel and cube the mango.Once all the fruits are prepared, gently combine them in a large bowl. If desired, drizzle a tablespoon of honey over the fruit and add a touch more lemon juice to enhance the flavors. After mixing, cover the bowl and refrigerate for 15 to 30 minutes to allow the salad to cool and the flavors to meld.Finally, garnish the salad with a few fresh mint leaves before serving. Enjoy your refreshing, chilled fruit salad!','https://images.themodernproper.com/billowy-turkey/production/posts/2022/FruitSalad_Shot4_20.jpg?w=960&h=960&q=82&fm=jpg&fit=crop&dm=1654019861&s=abb0af9baa0039bf1e91942aa34af247'),

         new Recipe(2,'Cheese toast', 'This is cheese toast',
        [
            new Product(1,'Cheese',100,4.99,'https://t3.ftcdn.net/jpg/05/66/02/98/360_F_566029808_X7praimuCQt0MsLCmw5d65Pp5KqmTS8e.jpg','bialkooo'),
            new Product(6,'Bread',100,1.00,'https://i5.walmartimages.com/seo/Freshness-Guaranteed-French-Bread-14-oz_fd51f0c3-4eea-4ff1-8109-7770339b6d85.fdba2ce348744cde3840700f5e33f3d3.jpeg','bialkoo')
         
        ]
            ,'cheese + bread slice','https://i.ytimg.com/vi/PcFQnteNwvo/maxresdefault.jpg')
    ]

    getRecipes(){
        return this.recepies.slice();
    }

    getRecipeByName(recipeTitle: String): Recipe | undefined{
        return this.recepies.find(product => product.title.toLowerCase() === recipeTitle.toLowerCase());
    }
}