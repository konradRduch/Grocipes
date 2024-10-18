import { ProductShoppingLists } from "./productShoppingList.model";
import { ShoppingList } from "./shopping-list.model";

export class ShoppingSchedule{
    public id: number;
    public name: string;
    public shoppingList: ShoppingList[];
    public userId: number;

    constructor(id: number, name: string, shoppingList: ShoppingList[],userId: number)
    {
        this.id = id;
        this.name = name;
        this.shoppingList = shoppingList;
        this.userId = userId;
    }
}