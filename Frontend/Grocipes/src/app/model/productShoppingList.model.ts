export class ProductShoppingLists{
    public id: number;
    public productId: number;
    public name: string;
    public weight: number;
    public price: number;
    public image_url: string;
    public calories: number;
    public shopListId: number;
    public quantity: number;
    public unitId: number;
    public unitName: string;
    public done: boolean;

    constructor(id: number,productId: number, name: string,weight: number,price: number,image_url: string,calories: number,
        shopListId: number,quantity: number,unitId: number,unitName: string,done: boolean
    ){

    this.id = id;
    this.productId = productId;
    this.name = name ;
    this.weight = weight;
    this.price = price;
    this.image_url = image_url; 
    this.calories = calories;
    this.shopListId = shopListId;
    this.quantity = quantity;
    this.unitId = unitId;
    this.unitName = unitName;
    this.done = done;

    }
}