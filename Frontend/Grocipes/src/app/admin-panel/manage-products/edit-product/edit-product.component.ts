import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { NutritionFacts } from 'src/app/model/nutrition-facts.model';
import { Product } from 'src/app/model/product.model';
import { GroceriesService } from 'src/app/service/groceries.service';
import { Groceries2Service } from 'src/app/service/groceries2.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  productForm: FormGroup = new FormGroup({});
  id: number = 0;

  constructor(
    private groceries2Service: Groceries2Service,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
 // Inicjalizacja formularza w konstruktorze
  }

  ngOnInit() {
    this.initForm();
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.groceries2Service.getProduct(this.id).subscribe(
        (data: Product) => {
            this.initForm(data);
        }
      );
    });
  }

  private initForm(product?: Product) {
    console.log(product);
    const predefinedNutrients = [
      { id: 1, name: 'Fat', amount: product?.nutrient.at(0)?.amount, dailyValue: product?.nutrient.at(0)?.dailyValue},
      { id: 2, name: 'Cholesterol', amount: product?.nutrient.at(1)?.amount, dailyValue: product?.nutrient.at(1)?.dailyValue },
      { id: 3, name: 'Sodium', amount: product?.nutrient.at(2)?.amount, dailyValue: product?.nutrient.at(2)?.dailyValue },
      { id: 4, name: 'Carbohydrates', amount: product?.nutrient.at(3)?.amount, dailyValue: product?.nutrient.at(3)?.dailyValue },
      { id: 5, name: 'Protein', amount: product?.nutrient.at(4)?.amount, dailyValue: product?.nutrient.at(4)?.dailyValue },
      { id: 6, name: 'Potassium', amount: product?.nutrient.at(5)?.amount, dailyValue: product?.nutrient.at(5)?.dailyValue },
    ];
    const nutrientFormGroups = predefinedNutrients.map(nutrients => 
      new FormGroup({
        id: new FormControl(nutrients.id),
        name: new FormControl(nutrients.name, Validators.required),
        amount: new FormControl(nutrients.amount, [Validators.required, Validators.min(0)]),
        dailyValue: new FormControl(nutrients.dailyValue, [Validators.required, Validators.min(0)]),
      })
    );

    console.log(nutrientFormGroups);
 
    this.productForm = new FormGroup({
      name: new FormControl(product?.name, [Validators.required, Validators.min(0)]),
      weight: new FormControl(product?.weight, [Validators.required, Validators.min(0)]),
      price: new FormControl(product?.price, [Validators.required, Validators.min(0)]),
      image_url: new FormControl(product?.image_url, Validators.required),
      calories: new FormControl(product?.calories, [Validators.required, Validators.min(0)]),
      nutrient: new FormArray(nutrientFormGroups),
    });
    console.log(this.productForm);
  }

  get nutrients() {
    return this.productForm.get('nutrient') as FormArray; // Upewnij się, że używasz tej samej nazwy
  }

  onSubmit() {
    console.log(this.productForm.value);
    if (this.productForm.valid) {
      this.groceries2Service.editProduct(this.id,this.productForm.value).pipe(
        switchMap(() => this.groceries2Service.fetchGroceries())
      ).subscribe();
      this.onCancel();
    } else {
      this.router.navigate(['../'], { relativeTo: this.activatedRoute });
    }
  }

  onCancel() {
    this.productForm.reset();
    this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
  }
}
