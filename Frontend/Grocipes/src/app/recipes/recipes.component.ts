import { Component, OnInit } from '@angular/core';
import { RecipesService } from '../recepies.service';
import { Recipe } from '../recepie.model';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css'],
  providers: [RecipesService]
})
export class RecipesComponent implements OnInit{
  recipes: Recipe[] = [];

  constructor(private recipesService: RecipesService){

  }
  ngOnInit(): void {
    this.recipes = this.recipesService.getRecipes();
    console.log(this.recipes);
  }


}
