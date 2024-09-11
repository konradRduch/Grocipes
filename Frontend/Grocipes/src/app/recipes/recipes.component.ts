import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
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
  @ViewChild('searchtext', { static: false }) searchtext!: ElementRef<HTMLInputElement>;
  searchedRecipe: String = "";
  searchedRecipes: Recipe[] = [];
  searchMode: boolean = false;

  constructor(private recipesService: RecipesService){
  }

  ngOnInit(): void {
    this.recipes = this.recipesService.getRecipes();
    console.log(this.recipes);
  }

  search(){
    const query = this.searchtext.nativeElement.value;
    this.searchedRecipes = this.recipesService.searchedRecipes(query);
    console.log(this.searchedRecipes);
    if(this.searchtext.nativeElement.value=="")this.searchMode = false;
    else this.searchMode = true;
    console.log(this.searchMode);
  }

  onSearchInput(){
  }
  exitSearchMode(){
    this.searchMode = false;
  }
  
}
