import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RecipesService } from 'src/app/service/recepies.service';
import { Recipe } from '../../../model/recepie.model';

@Component({
  selector: 'app-recipes-item-details',
  templateUrl: './recipes-item-details.component.html',
  styleUrls: ['./recipes-item-details.component.css'],
  providers: [RecipesService]
})
export class RecipesItemDetailsComponent implements OnInit{
  @Input() recipe: Recipe | undefined;

  constructor(private route: ActivatedRoute, private recipesService: RecipesService){
  }
  ngOnInit(): void {
    this.recipesService.fetchData();
    this.route.paramMap.subscribe(params => {
      const recipeTitle = params.get('title');
      if (recipeTitle) {
        this.recipesService.getRecipeByName(recipeTitle).subscribe((recipe) => {
          this.recipe = recipe;
          console.log(this.recipe);
        });
      }
    });
  }
}
