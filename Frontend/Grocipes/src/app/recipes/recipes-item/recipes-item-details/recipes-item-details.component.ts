import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { RecipesService } from 'src/app/service/recepies.service';
import { Recipe } from '../../../model/recepie.model';
import { Subscription } from 'rxjs';
import { DataStorageService } from 'src/app/service/data-storage.service';
import { ShoppingListService } from 'src/app/service/shopping-list.service';
import { NutritionalGoalService } from 'src/app/service/nutritional-goal.service';
import { EatDeadlineService } from 'src/app/service/eat-deadline.service';
import { RecipeTypeOfMeal } from 'src/app/enums/recipe-type-of-meal.enum';
import { Product } from 'src/app/model/product.model';

@Component({
  selector: 'app-recipes-item-details',
  templateUrl: './recipes-item-details.component.html',
  styleUrls: ['./recipes-item-details.component.css']
})
export class RecipesItemDetailsComponent implements OnInit  {
  selectedDate: string | null = null; 

  recipe: Recipe | undefined;
  title: string | undefined;

  recipes: Recipe[] | undefined;
  subscription: Subscription = new Subscription;

  productsCost: number = 0;

  constructor(
    private router: Router,
    private route: ActivatedRoute, 
    private recipesService: RecipesService, 
    private dataStorageService: DataStorageService,
    private shoppingListService: ShoppingListService,
    private nutrtionalGoalService: NutritionalGoalService,
    private eatDeadlineService: EatDeadlineService
  ) {
  }
  
  // ngOnInit(): void {
  //   this.dataStorageService.fetchRecipes().subscribe();
  //   this.subscription = this.recipesService.recipesChanged
  //     .subscribe(
  //       (data: Recipe[]) => {
  //         this.recipes = data;
  //       }
  //     );
  //   this.recipes = this.recipesService.getRecipes();


  //   this.route.params
  //     .subscribe(
  //       (params: Params) => {
  //         this.title = params['title'];
  //         this.recipe = this.recipesService.getRecipeByName(this.title!);
         
  //         if (!this.recipe) {
  //           this.dataStorageService.fetchRecipes().subscribe((recipes: Recipe[]) => {
  //             this.recipe = this.recipesService.getRecipeByName(this.title!);
              
  //             this.recipe?.products.forEach((product: Product) => {
  //               this.productsCost += product.price; // Dodanie ceny każdego produktu
  //             });
  //           });
  //         }
  //       }
  //     );
  // }

  ngOnInit(): void {
    // Pobranie wszystkich przepisów na początku
    this.dataStorageService.fetchRecipes().subscribe(() => {
      this.initializeRecipe();
    });

    // Nasłuchiwanie na zmianę parametrów URL
    this.route.params.subscribe((params: Params) => {
      this.title = params['title'];
      this.initializeRecipe();
    });
  }

  private initializeRecipe(): void {
    // Resetowanie kosztów przed ponownym obliczeniem
    this.productsCost = 0;

    // Pobranie przepisu z serwisu
    this.recipe = this.recipesService.getRecipeByName(this.title!);

    if (this.recipe) {
      this.calculateProductsCost();
    } else {
      // Jeśli przepisu nie ma w pamięci, pobierz go ponownie z serwera
      this.dataStorageService.fetchRecipes().subscribe(() => {
        this.recipe = this.recipesService.getRecipeByName(this.title!);
        if (this.recipe) {
          this.calculateProductsCost();
        }
      });
    }
  }

  private calculateProductsCost(): void {
    if (this.recipe?.products) {
      const totalCost = this.recipe.products.reduce(
        (total: number, product: Product) => total + product.price,
        0
      );
  
      // Zaokrąglamy wynik do dwóch miejsc po przecinku
      this.productsCost = parseFloat(totalCost.toFixed(2));
    }
  }



  addToCalendar(){
    console.log(this.recipe)
    let date = this.selectedDate;
    switch(this.recipe?.typeOfMeal){
      case RecipeTypeOfMeal.BREAKFAST:
        date += 'T08:00'
        break;
      case RecipeTypeOfMeal.LUNCH:
        date +='T12:00'
        break;
      case RecipeTypeOfMeal.DINNER:
        date += 'T17:00'
        break;
    }


    const recipeProductsShoppingList = {
      name: this.recipe?.title,
      shoppingDate: date,
      colorCard: 1,
      likedList: false,
      products: this.recipe?.products.map((data: any) => ({
        id: data.id,
        unitValue: data.unitValue,
        unit_id: 1,//data.unit_id,
        done: false
      }))
    }
    console.log(recipeProductsShoppingList)
    this.shoppingListService.addShoppingList(recipeProductsShoppingList).subscribe();


    const addToEatDeadline = {
        recipeId: this.recipe?.id,
        eatingDate: date,
        done: false,
        rate: 0,
        comment: ""
    }

    this.eatDeadlineService.addEatDeadline(addToEatDeadline).subscribe();

    this.router.navigate(['/recipes']);
  }

}
