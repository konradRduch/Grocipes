import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { Product } from 'src/app/model/product.model';
import { Recipe } from 'src/app/model/recepie.model';
import { Groceries2Service } from 'src/app/service/groceries2.service';
import { Recipe2Service } from 'src/app/service/recipes2.service';

@Component({
  selector: 'app-edit-recipe',
  templateUrl: './edit-recipe.component.html',
  styleUrls: ['./edit-recipe.component.css']
})
export class EditRecipeComponent implements OnInit {
  recipeForm: FormGroup = new FormGroup({});
  availableProducts: Product[] = []; // Przy
  availableUnit: {id: number, name: string }[] = [];

  id: number = 0;

  constructor(
    private recipes2Service: Recipe2Service,
    private groceries2Service: Groceries2Service,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.loadAvailableProducts(); // Załaduj produkty z backendu
    this.loadAvailableUnit();
    this.initForm();

    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = +params['id'];
      console.log(this.id);
      this.recipes2Service.getRecipe(this.id).subscribe(
        (data: Recipe) => {
          console.log(data);
            this.initForm(data);
        }
      );
    });

    
  }

  private initForm(recipe?: Recipe) {
    this.recipeForm = new FormGroup({
      title: new FormControl(recipe?.title, Validators.required),
      description: new FormControl(recipe?.description, Validators.required),
      preparation_method: new FormControl(recipe?.preparation_method, Validators.required),
      image_url: new FormControl(recipe?.image_url, Validators.required),
      products: new FormArray(recipe?.products ? recipe.products.map(product => this.createProductGroup(product)) : [])
    });
  }
  private createProductGroup(product: Product): FormGroup {
    console.log(product);
    return new FormGroup({
      id: new FormControl(product?.id, Validators.required), // Użyj ID produktu lub pierwszego produktu jako domyślnego
      unitValue: new FormControl(product?.unitValue || 1, [Validators.required, Validators.min(1)]), // Użyj unitValue lub 1 jako domyślną ilość
      unit_id: new FormControl(product?.unit_id || this.availableUnit[0]?.id, Validators.required) // Użyj unit_id lub pierwszej jednostki jako domyślnej
    });
  }

  get products(): FormArray {
    return this.recipeForm.get('products') as FormArray;
  }
  addProduct() {
    const productGroup = new FormGroup({
      id: new FormControl(this.availableProducts[0]?.id, Validators.required), // Domyślnie wybierz pierwszy produkt
      unitValue: new FormControl(1, [Validators.required, Validators.min(1)]), // Domyślna ilość
      unit_id: new FormControl(this.availableUnit[0]?.id, Validators.required) // Domyślnie wybierz pierwszą jednostkę
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
      this.recipes2Service.editRecipe(this.id,this.recipeForm.value).pipe(
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