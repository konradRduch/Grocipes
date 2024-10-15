import { Injectable } from "@angular/core";
import { RecipesService } from "./recepies.service";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth.service";
import { GroceriesService } from "./groceries.service";
import { Product } from "../model/product.model";
import { map, tap } from "rxjs";
import { Recipe } from "../model/recepie.model";

@Injectable({ providedIn: 'root' })
export class DataStorageService {
  constructor(
    private http: HttpClient,
    private recipeService: RecipesService,
    private groceriesService: GroceriesService,
    private authService: AuthService
  ) { }

  // storeRecipes() { }

  fetchRecipes() {
    return this.http.get<Recipe[]>('http://localhost:8080/recipes').pipe(
      map(recipes => {
        return recipes.map(recipe => {
          return {
            ...recipe
          };
        });
      }),
      tap(recipes => {
        this.recipeService.setRecipes(recipes);
      })
    )
  }

  // storeGroceries() { }

  fetchGroceries() {
    return this.http.get<Product[]>('http://localhost:8080/products').pipe(
      map(groceries => {
        return groceries.map(product => {
          return {
            ...product
          };
        });
      }),
      tap(products => {
        this.groceriesService.setGroceries(products);
      })
    )
  }

 
  fetchProduct(product: Product) {
 console.log(product);

    // Stwórz obiekt zgodny z wymaganą strukturą JSON
    const payload = {
      productDTO: {
        name: product.name,
        weight: product.weight,
        price: product.price,
        image_url: product.image_url,
        calories: product.calories
      },
      nutritionFactNutrientDTO: product.nutrient.map(nutrient => ({
        nutrientId: nutrient.id,
        amount: nutrient.amount,
        dailyValue: nutrient.dailyValue
      }))
    };
    
    console.log(payload);
    // Wyślij zapytanie POST z utworzonym obiektem payload
    return this.http.post<Product>('http://localhost:8080/products/add', payload).pipe(
    );
  }



}