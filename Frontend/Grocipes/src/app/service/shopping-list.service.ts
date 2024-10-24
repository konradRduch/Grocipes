import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { ShoppingSchedule } from "../model/shoppingSchedule.model";
import { ShoppingList } from "../model/shopping-list.model";

@Injectable({ providedIn: 'root' })
export class ShoppingListService {

    shoppingListChanged =  new BehaviorSubject<ShoppingSchedule[]>([]);
    // groceriesChanged$ = this.groceriesChanged.asObservable();
    shoppingListChanged$ = this.shoppingListChanged.asObservable();
    constructor(private http: HttpClient){

    }

    fetchShoppingLists(): Observable<ShoppingSchedule[]>{
        return this.http.get<ShoppingSchedule[]>(`http://localhost:8080/shoppingList/getUserShoppingSchedules`).pipe(
            tap(data =>{
                this.shoppingListChanged.next(data);
            })
        );
    }

    getShoppingList(shoppingListId: number): Observable<ShoppingList>{
        return this.http.get<ShoppingList>(`http://localhost:8080/shoppingList/${shoppingListId}`);
    }

    addShoppingList(dto: any){
        console.log(dto);
        const addShoppingListDTO = {
              name: dto.name,
              shopping_date: dto.shoppingDate,
              cardColor: dto.colorCard,
              likedList: false,
              productShoppingLists: dto.products.map((data: any) => ({
                product_id: data.id,
                quantity: data.unitValue,
                unit_id: data.unit_id,
                done: false
              }))
        };
 
        console.log("po");
        console.log(addShoppingListDTO);
        return this.http.post("http://localhost:8080/shoppingList/add",addShoppingListDTO);
    }

    deleteShoppingList(shoppingListId: number){
        return this.http.delete(`http://localhost:8080/shoppingList/delete/${shoppingListId}`);
    }

    editShoppingList(shoppingListId: number,dto: any){

        console.log(dto);
        const editShoppingListDTO = {
              id: shoppingListId,
              name: dto.name,
              shopping_date: dto.shoppingDate,
              cardColor: dto.colorCard,
              likedList: dto.likedList,
              productShoppingLists: dto.products.map((data: any) => ({
                product_id: data.id,
                quantity: data.unitValue,
                unit_id: data.unit_id,
                done: data.done
              }))
        };

        console.log("edit!!!!!!!!!!!");
        console.log(editShoppingListDTO);
        return this.http.patch(`http://localhost:8080/shoppingList/edit/${shoppingListId}`,editShoppingListDTO);
    }

    addProductToShoppingList(){

    }
    
    deleteProductFromShoppingList(){
        
    }

    toggleProduct(shoppingListId: number, productshoppinglistId: number){
        const emptyBody ={}
      return this.http.patch(`http://localhost:8080/shoppingList/toggle/${shoppingListId}/${productshoppinglistId}`, emptyBody);  
    }

    likeShoppingList(shoppingListId: number){
        const emptyBody ={}
        return this.http.patch(`http://localhost:8080/shoppingList/like/${shoppingListId}`, emptyBody);  
    }
}