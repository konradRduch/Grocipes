import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { RecipeTypeOfMeal } from 'src/app/enums/recipe-type-of-meal.enum';
import { Product } from 'src/app/model/product.model';
import { Groceries2Service } from 'src/app/service/groceries2.service';
import { Recipe2Service } from 'src/app/service/recipes2.service';

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit {
  recipeForm: FormGroup = new FormGroup({});
  availableProducts: Product[] = []; // Przy
  availableUnit: {id: number, name: string }[] = [];

  recipeTypes = RecipeTypeOfMeal.getAllTypeOfMeal();

  constructor(
    private recipes2Service: Recipe2Service,
    private groceries2Service: Groceries2Service,
    private router: Router,
  ) { }

  ngOnInit() {

    this.initForm();
    this.loadAvailableProducts(); // Załaduj produkty z backendu
    this.loadAvailableUnit();
  }

  private initForm() {
    this.recipeForm = new FormGroup({
      title: new FormControl(null, Validators.required),
      typeOfMeal: new FormControl(null, Validators.required),
      description: new FormControl(null, Validators.required),
      preparation_method: new FormControl(null, Validators.required),
      image_url: new FormControl(null, Validators.required),
      products: new FormArray([])
    });
  }

  get products(): FormArray {
    return this.recipeForm.get('products') as FormArray;
  }

  addProduct() {
    const productGroup = new FormGroup({
      id: new FormControl(null, Validators.required),
      unitValue: new FormControl(null, [Validators.required, Validators.min(1)]),
      unit_id: new FormControl(null, Validators.required)
    });
    this.products.push(productGroup);
  }
  removeProduct(index: number) {
    this.products.removeAt(index);
  }
  loadAvailableProducts() {
    // Załaduj produkty z backendu (przykład)
    this.groceries2Service.fetchGroceries().subscribe(
      (data: Product[]) => {
        this.availableProducts = [...data];
      }
    );
  }

  loadAvailableUnit(){
    this.availableUnit = [
      { id: 1, name: 'g' },
      { id: 2, name: 'mg' },
      { id: 3, name: 'L' },
    ];
  }

  onSubmit() {
    if (this.recipeForm.valid) {
      this.recipes2Service.addRecipe(this.recipeForm.value).pipe(
        switchMap(() => this.recipes2Service.fetchRecipes())
      ).subscribe();
      this.onCancel();
    } else {
      this.router.navigate(['admin-panel/manage-recipes']);
    }
  }

  onCancel() {
    this.recipeForm.reset();
    this.router.navigate(['admin-panel/manage-recipes']);
  }
}



