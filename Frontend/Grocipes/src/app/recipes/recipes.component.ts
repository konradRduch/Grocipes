import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { RecipesService } from '../service/recepies.service';
import { Recipe } from '../model/recepie.model';
import { DataStorageService } from '../service/data-storage.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
  //usunac provider
})
export class RecipesComponent implements OnInit{
  recipes: Recipe[] | undefined;
  subscription: Subscription = new Subscription;
  @ViewChild('searchtext', { static: false }) searchtext!: ElementRef<HTMLInputElement>;
  searchedRecipe: String = "";
  searchedRecipes: Recipe[] = [];
  searchMode: boolean = false;

  constructor(private recipesService: RecipesService, private dataStorageService: DataStorageService){
  }

  ngOnInit(): void {
    this.dataStorageService.fetchRecipes().subscribe();
    this.subscription = this.recipesService.recipesChanged
      .subscribe(
        (data: Recipe[])=>{
          this.recipes = data;
        }
      );
      this.recipes = this.recipesService.getRecipes();
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
