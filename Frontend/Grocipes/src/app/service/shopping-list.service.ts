import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ShoppingSchedule } from "../model/shoppingSchedule.model";

@Injectable({ providedIn: 'root' })
export class ShoppingListService {

    constructor(private http: HttpClient){

    }

    fetchShoppingLists(userId: number): Observable<ShoppingSchedule[]>{
        return this.http.get<ShoppingSchedule[]>(`http://localhost:8080/shoppingList/${userId}`);
    }

    getShoppingList(){

    }

    addShoppingList(){

    }

    deleteShoppingList(){

    }

    editShoppingList(){

    }

    addProductToShoppingList(){

    }
    
    deleteProductFromShoppingList(){
        
    }

}