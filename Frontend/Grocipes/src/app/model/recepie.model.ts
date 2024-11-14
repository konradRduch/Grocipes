import { Product } from "./product.model";


export class Recipe{
    public id: number;
    public title: String;
    public typeOfMeal: String;
    public description: String;
    public products: Product[];
    public preparation_method: String;
    public image_url: String;

    constructor(id: number, title: String,typeOfMeal: String, description: String, products: Product[], preparation_method: String, image_url: String){
        this.id = id;
        this.title = title;
        this.typeOfMeal = typeOfMeal;
        this.description = description;
        this.products = products;
        this.preparation_method = preparation_method;
        this.image_url = image_url;
    }
}