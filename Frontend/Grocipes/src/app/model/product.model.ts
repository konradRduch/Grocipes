import { NutritionFacts } from "./nutrition-facts.model";

export class Product{
    public id: number;
    public name: String;
    public weight: number;
    public calories: number;
    public price: number;
    public image_url: String;
    public unitName: String;
    public unitValue: number;
    public nutrient: NutritionFacts[];


    constructor(id: number, name: String, weight: number,calories: number, price: number, image_url: String, unitName: String, unitValue: number, nutrition_facts: NutritionFacts[]){
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.calories = calories;
        this.price = price;
        this.image_url = image_url;
        this.unitName = name;
        this.unitValue = unitValue;
        this.nutrient = nutrition_facts;
    }
}