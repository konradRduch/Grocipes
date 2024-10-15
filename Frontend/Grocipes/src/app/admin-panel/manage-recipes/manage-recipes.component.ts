import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { Recipe } from 'src/app/model/recepie.model';
import { Recipe2Service } from 'src/app/service/recipes2.service';

@Component({
  selector: 'app-manage-recipes',
  templateUrl: './manage-recipes.component.html',
  styleUrls: ['./manage-recipes.component.css']
})
export class ManageRecipesComponent implements OnInit {

  recipes: Recipe[] | undefined
  show: boolean = true;

  constructor(
    private recipes2Service: Recipe2Service,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.router.events.subscribe(() => {
      this.show = !this.router.url.includes('add-recipe') && !this.router.url.includes('edit-recipe') && this.router.url.includes('manage-recipes');
    });
  }
  ngOnInit(): void {
    this.recipes2Service.fetchRecipes().subscribe(
      (data: Recipe[]) => {
        this.recipes = [...data];
      }
    );


    this.recipes2Service.recipesChanged$.subscribe(data => { 
      console.log("subscribtion")
      this.recipes = [...data]; })

  }

 
  addRecipe() {
    this.router.navigate(['./add-recipe'], { relativeTo: this.activatedRoute });
  }
  editRecipe(recipe: Recipe) {
    const recipeId = recipe.id;
    this.router.navigate(['./edit-recipe', recipeId], { relativeTo: this.activatedRoute });
  }
  deleteRecipe(id: number) {
    this.recipes2Service.deleteRecipe(id).pipe(
      switchMap(() => this.recipes2Service.fetchRecipes())
    ).subscribe();
  }
 

}
