export class Product{
    public id: number;
    public name: String;
    public weight: number;
    public calories: number;
    public price: number;
    public image_url: String;
    public nutrition_facts: String;


    constructor(id: number, name: String, weight: number,calories: number, price: number, image_url: String, nutrition_facts: String){
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.calories = calories;
        this.price = price;
        this.image_url = image_url;
        this.nutrition_facts = nutrition_facts;
    }
}