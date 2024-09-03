import { Product } from "./product.model";

export class GroceriesService{
    
    groceries: Product[] =[
        new Product(1,'Cheese',100,4.99,'https://t3.ftcdn.net/jpg/05/66/02/98/360_F_566029808_X7praimuCQt0MsLCmw5d65Pp5KqmTS8e.jpg','bialkooo'),
        new Product(2,'Apple',50,0.99,'https://m.media-amazon.com/images/I/918YNa3bAaL.jpg','bialkooo'),
        new Product(3,'Banana',50,0.99,'https://target.scene7.com/is/image/Target/GUEST_cf4773e6-afec-4aa1-89e7-74b7dfc09973','bialkooo'),
        new Product(4,'Sugar',50,0.99,'https://m.media-amazon.com/images/I/810dK-eEBwL._SL1500_.jpg','bialkooo'),
        new Product(5,'Milk',50,0.99,'https://mlekpol.com.pl/wp-content/uploads/2018/08/happy-barn3_5_750x750-1.png','bialkooo'),
        new Product(6,'Bread',100,1.00,'https://i5.walmartimages.com/seo/Freshness-Guaranteed-French-Bread-14-oz_fd51f0c3-4eea-4ff1-8109-7770339b6d85.fdba2ce348744cde3840700f5e33f3d3.jpeg','bialkoo')
    ];

    getGroceries(){
        return this.groceries.slice();
    }

    getProductByName(productName: string): Product | undefined {
        return this.groceries.find(product => product.name.toLowerCase() === productName.toLowerCase());
    }

}