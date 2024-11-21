import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";
import { Product } from "../model/product.model";
import { BehaviorSubject, map, Observable, Subject, tap } from "rxjs";

@Injectable({ providedIn: 'root' })
export class Groceries2Service {
  groceriesChanged = new BehaviorSubject<Product[]>([]);
  groceriesChanged$ = this.groceriesChanged.asObservable();

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }


  fetchGroceries(): Observable<Product[]> {
    return this.http.get<Product[]>('http://localhost:8080/products').pipe(
      tap(products => {
        console.log('From service');
        console.log(products);
        this.groceriesChanged.next(products);
      })
    )
  }

  getProduct(id: number): Observable<Product>{
    return this.http.get<Product>(`http://localhost:8080/products/${id}`);
  }

  addProduct(product: Product) {
    console.log(product);

    // Stwórz obiekt zgodny z wymaganą strukturą JSON
    const payload = {
      productDTO: {
        name: product.name,
        weight: product.weight,
        price: product.price,
        image_url: product.image_url,
        calories: product.calories,
        unitId: product.unit_id
      },
      nutritionFactNutrientDTO: product.nutrient.map(nutrient => ({
        nutrientId: nutrient.id,
        amount: nutrient.amount,
        dailyValue: nutrient.dailyValue
      }))
    };

    console.log(payload);
    // Wyślij zapytanie POST z utworzonym obiektem payload
    return this.http.post('http://localhost:8080/products/add', payload);
  }

  editProduct(id: number, product: Product) {


    const updatedProduct = {
      productDTO: {
        name: product.name,
        weight: product.weight,
        price: product.price,
        image_url: product.image_url,
        calories: product.calories,
        unitId: product.unit_id
      },
      nutritionFactNutrientDTO: product.nutrient.map(nutrient => ({
        nutrientId: nutrient.id,
        amount: nutrient.amount,
        dailyValue: nutrient.dailyValue
      }))
    };

    return this.http.patch(`http://localhost:8080/products/edit/${id}`, updatedProduct);
  }

  deleteProduct(id: number) {
    return this.http.delete(`http://localhost:8080/products/delete/${id}`);
  }

}