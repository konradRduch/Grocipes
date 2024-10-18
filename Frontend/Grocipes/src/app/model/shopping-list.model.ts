import { Product } from "./product.model";
import { ProductShoppingLists } from "./productShoppingList.model";

export class ShoppingList{
    public id: number;
    public name: string;
    public shopping_date: string;
    public cardColor: number;
    public productShoppingLists: ProductShoppingLists[];

    constructor(id: number, name: string,shopping_date: string, cardColor: number, productShoppingLists: ProductShoppingLists[])
    {
        this.id = id;
        this.name = name;
        this.shopping_date = shopping_date;
        this.cardColor = cardColor;
        this.productShoppingLists = productShoppingLists;
    }
}