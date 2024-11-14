import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AuthService } from "./auth.service";
import { Recipe } from "../model/recepie.model";
import { BehaviorSubject, map, Observable, tap } from "rxjs";

@Injectable({ providedIn: 'root' })
export class Recipe2Service {

  recipesChanged = new BehaviorSubject<Recipe[]>([]);
  recipesChanged$ = this.recipesChanged.asObservable();
  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  fetchRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>('http://localhost:8080/recipes').pipe(
      tap(recipes => {
        // this.recipeService.setRecipes(recipes);
        this.recipesChanged.next(recipes);
      })
    )
  }

  getRecipe(id: number): Observable<Recipe>{
    return this.http.get<Recipe>(`http://localhost:8080/recipes/${id}`);
  }

  addRecipe(recipe: Recipe) {
    console.log(recipe);
    // Stwórz obiekt zgodny z wymaganą strukturą JSON
    const payload = {
      recipe: {
        title: recipe.title,
        typeOfMeal: recipe.typeOfMeal,
        description: recipe.description,
        preparation_method: recipe.preparation_method,
        image_url: recipe.image_url
      },
      products: recipe.products.map(product => ({
        unit_id: product.unit_id,
        product_id: product.id,
        quantity: product.unitValue
      }))
    };

    console.log(payload);
    // Wyślij zapytanie POST z utworzonym obiektem payload
    return this.http.post('http://localhost:8080/recipes/add', payload);
  }


  editRecipe(id: number, recipe: Recipe) {
    console.log(id);
    const updatedProduct = {
      recipe: {
        title: recipe.title,
        typeOfMeal: recipe.typeOfMeal,
        description: recipe.description,
        preparation_method: recipe.preparation_method,
        image_url: recipe.image_url
      },
      products: recipe.products.map(product => ({
        unit_id: product.unit_id,
        product_id: product.id,
        quantity: product.unitValue
      }))
    };
    return this.http.patch(`http://localhost:8080/recipes/edit/${id}`, updatedProduct);
  }



  deleteRecipe(id: number) {
    return this.http.delete(`http://localhost:8080/recipes/delete/${id}`);
  }
}