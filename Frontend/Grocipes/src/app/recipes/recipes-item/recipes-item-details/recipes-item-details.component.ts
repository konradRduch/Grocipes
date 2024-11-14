import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { RecipesService } from 'src/app/service/recepies.service';
import { Recipe } from '../../../model/recepie.model';
import { Subscription } from 'rxjs';
import { DataStorageService } from 'src/app/service/data-storage.service';
import { ShoppingListService } from 'src/app/service/shopping-list.service';
import { NutritionalGoalService } from 'src/app/service/nutritional-goal.service';
import { EatDeadlineService } from 'src/app/service/eat-deadline.service';

@Component({
  selector: 'app-recipes-item-details',
  templateUrl: './recipes-item-details.component.html',
  styleUrls: ['./recipes-item-details.component.css']
})
export class RecipesItemDetailsComponent implements OnInit {
  selectedDate: string | null = null; 

  recipe: Recipe | undefined;
  title: string | undefined;

  recipes: Recipe[] | undefined;
  subscription: Subscription = new Subscription;

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
  ngOnInit(): void {
    this.dataStorageService.fetchRecipes().subscribe();
    this.subscription = this.recipesService.recipesChanged
      .subscribe(
        (data: Recipe[]) => {
          this.recipes = data;
        }
      );
    this.recipes = this.recipesService.getRecipes();


    this.route.params
      .subscribe(
        (params: Params) => {
          this.title = params['title'];
          this.recipe = this.recipesService.getRecipeByName(this.title!);
          
          if (!this.recipe) {
            this.dataStorageService.fetchRecipes().subscribe((recipes: Recipe[]) => {
              this.recipe = this.recipesService.getRecipeByName(this.title!);
            });
          }
        }
      );
  }

  addToCalendar(){
    console.log(this.recipe)
    const recipeProductsShoppingList = {
      name: this.recipe?.title,
      shoppingDate:`${this.selectedDate}T10:00`,
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
        eatingDate: `${this.selectedDate}T10:00`,
        done: false,
        rate: 0,
        comment: ""
    }

    this.eatDeadlineService.addEatDeadline(addToEatDeadline).subscribe();

    this.router.navigate(['/recipes']);
  }

}
