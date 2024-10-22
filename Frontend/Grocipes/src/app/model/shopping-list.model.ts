import { Product } from "./product.model";
import { ProductShoppingLists } from "./productShoppingList.model";

export class ShoppingList{
    public id: number;
    public name: string;
    public shopping_date: string;
    public cardColor: number;
    public likedList: boolean;
    public productShoppingLists: ProductShoppingLists[];

    constructor(id: number, name: string,shopping_date: string, cardColor: number, likedList: boolean,productShoppingLists: ProductShoppingLists[])
    {
        this.id = id;
        this.name = name;
        this.shopping_date = shopping_date;
        this.cardColor = cardColor;
        this.likedList = likedList;
        this.productShoppingLists = productShoppingLists;
    }
}