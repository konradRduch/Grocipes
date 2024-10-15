import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { DataStorageService } from 'src/app/service/data-storage.service';
import { Groceries2Service } from 'src/app/service/groceries2.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  productForm: FormGroup = new FormGroup({});


  constructor(
    private groceries2Service: Groceries2Service,
    private router: Router,
  ) { }

  ngOnInit() {

    this.initForm();
  }

  private initForm() {
    const predefinedNutrients = [
      { id: 1, name: 'Fat', amount: 0, dailyValue: 0 },
      { id: 2, name: 'Cholesterol', amount: 0, dailyValue: 0 },
      { id: 3, name: 'Sodium', amount: 0, dailyValue: 0 },
      { id: 4, name: 'Carbohydrates', amount: 0, dailyValue: 0 },
      { id: 5, name: 'Protein', amount: 0, dailyValue: 0 },
      { id: 6, name: 'Potassium', amount: 0, dailyValue: 0 },
    ];
    console.log("predifined:" + predefinedNutrients);
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
      name: new FormControl(null, Validators.required),
      weight: new FormControl(null, [Validators.required, Validators.min(1)]),
      price: new FormControl(null, [Validators.required, Validators.min(0)]),
      image_url: new FormControl(null, Validators.required),
      calories: new FormControl(null, [Validators.required, Validators.min(0)]),
      nutrient: new FormArray(nutrientFormGroups),
    });
  }

  get nutrients() {
    return this.productForm.get('nutrient') as FormArray;
  }

  onSubmit() {
    if (this.productForm.valid) {
      this.groceries2Service.addProduct(this.productForm.value).pipe(
        switchMap(() => this.groceries2Service.fetchGroceries())
      ).subscribe();
      this.onCancel();
    } else {
      this.router.navigate(['admin-panel/manage-products']);
    }
  }

  onCancel() {
    this.productForm.reset();
    this.router.navigate(['admin-panel/manage-products']);
  }
}
