import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { RecipesService } from 'src/app/service/recepies.service';
import { Recipe } from '../../../model/recepie.model';
import { Subscription } from 'rxjs';
import { DataStorageService } from 'src/app/service/data-storage.service';
import { ShoppingListService } from 'src/app/service/shopping-list.service';
import { NutritionalGoalService } from 'src/app/service/nutritional-goal.service';

@Component({
  selector: 'app-recipes-item-details',
  templateUrl: './recipes-item-details.component.html',
  styleUrls: ['./recipes-item-details.component.css']
})
export class RecipesItemDetailsComponent implements OnInit {
  recipe: Recipe | undefined;
  title: string | undefined;

  recipes: Recipe[] | undefined;
  subscription: Subscription = new Subscription;

  constructor(
    private route: ActivatedRoute, 
    private recipesService: RecipesService, 
    private dataStorageService: DataStorageService,
    private shoppingListService: ShoppingListService,
    private nutrtionalGoalService: NutritionalGoalService
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
    //adding to nutritional schedule

    //adding to shopping schedule 
  }

}
