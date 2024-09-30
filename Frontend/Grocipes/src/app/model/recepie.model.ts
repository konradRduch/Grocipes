import { Product } from "./product.model";


export class Recipe{
    public id: number;
    public title: String;
    public description: String;
    public ingredients: Product[] = [];
    public preparation_methhod: String;
    public image_url: String;

    constructor(id: number, title: String, description: String, ingredients: Product[], preparation_methhod: String, image_url: String){
        this.id = id;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.preparation_methhod = preparation_methhod;
        this.image_url = image_url;
    }
}