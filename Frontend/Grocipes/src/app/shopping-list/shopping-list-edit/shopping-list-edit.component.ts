import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription, switchMap } from 'rxjs';
import { Product } from 'src/app/model/product.model';
import { ShoppingList } from 'src/app/model/shopping-list.model';
import { ShoppingSchedule } from 'src/app/model/shoppingSchedule.model';
import { UserData } from 'src/app/model/userData.model';
import { AuthService } from 'src/app/service/auth.service';
import { Groceries2Service } from 'src/app/service/groceries2.service';
import { ShoppingListService } from 'src/app/service/shopping-list.service';
import { UserDataService } from 'src/app/service/user-data.service';



enum Color {
  BLUE,
  GRAY,
  GREEN,
  YELLOW,
  TURQUOISE,
  BLACK
}

@Component({
  selector: 'app-shopping-list-edit',
  templateUrl: './shopping-list-edit.component.html',
  styleUrls: ['./shopping-list-edit.component.css']
})
export class ShoppingListEditComponent implements OnInit, OnDestroy {

  shoppingListForm: FormGroup = new FormGroup({});
  availableProducts: Product[] = []; // Przy
  enum: typeof Color = Color;
  availableUnit: { id: number, name: string }[] = [];

  id: number | undefined;
  shoppingList: ShoppingList | undefined;



  constructor(
    private groceries2Service: Groceries2Service,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private shoppingListService: ShoppingListService,
  ) {

  }


  ngOnInit(): void {

    this.initForm();
    this.loadAvailableProducts();
    this.loadAvailableUnit();

    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = +params['id'];
      this.shoppingListService.getShoppingList(this.id).subscribe(
        (data: ShoppingList) => {
          this.shoppingList = data;
          this.initForm(data);
        }
      );
    });


  }
  ngOnDestroy(): void {

  }

  getEnumKeys(): string[] {
    return Object.keys(this.enum).filter(key => isNaN(Number(key)));
  }

  getEnumValue(colorName: string): number {
    return this.enum[colorName as keyof typeof Color];
  }
  getEnumNumberValue(colorNumber: number | undefined): string | undefined {
    return colorNumber !== undefined ? Color[colorNumber] : undefined;
  }

  private initForm(shoppingList?: ShoppingList) {
    this.shoppingListForm = new FormGroup({
      name: new FormControl(shoppingList?.name, Validators.required),
      shoppingDate: new FormControl(
        shoppingList?.shopping_date ? shoppingList.shopping_date.split('T')[0] : null,
        Validators.required
      ),
      shoppingTime: new FormControl(
        shoppingList?.shopping_date ? shoppingList.shopping_date.split('T')[1] : null,
        Validators.required
      ),
      colorCard: new FormControl(this.getEnumNumberValue(shoppingList?.cardColor), Validators.required),
      likedList: new FormControl(shoppingList?.likedList),
      products: new FormArray(shoppingList?.productShoppingLists ? shoppingList.productShoppingLists.map(product => this.createProductGroup(product)) : [])
    });
  }

  get products(): FormArray {
    return this.shoppingListForm.get('products') as FormArray;
  }

  addProduct() {
    const productGroup = new FormGroup({
      id: new FormControl(null, Validators.required),
      unitValue: new FormControl(null, [Validators.required, Validators.min(1)]),
      unit_id: new FormControl(null, Validators.required),
      done: new FormControl(null)
    });
    this.products.push(productGroup);
  }


  private createProductGroup(product: any): FormGroup {
    console.log(product);
    return new FormGroup({
      id: new FormControl(product?.productId, Validators.required), // Użyj ID produktu lub pierwszego produktu jako domyślnego
      unitValue: new FormControl(product?.quantity ,[Validators.required, Validators.min(1)]), // Użyj unitValue lub 1 jako domyślną ilość
      unit_id: new FormControl(product?.unitId , Validators.required), // Użyj unit_id lub pierwszej jednostki jako domyślnej
      done: new FormControl(product?.done)
    });
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

  loadAvailableUnit() {
    this.availableUnit = [
      { id: 1, name: 'pc.' },
      // { id: 2, name: 'mg' },
      // { id: 3, name: 'L' },
    ];
  }

  onCancel() {
    this.shoppingListForm.reset();
    this.router.navigate(['shopping-list']);
  }

  onSubmit() {
    console.log(this.shoppingListForm.value);
    if (this.shoppingListForm.valid) {
      const formValues = this.shoppingListForm.value;
      const date = formValues.shoppingDate;
      const time = formValues.shoppingTime;

      // Połącz datę i godzinę w LocalDateTime
      const localDateTime = `${date}T${time}`;

      formValues.shoppingDate = localDateTime;
      formValues.colorCard = this.getEnumValue(formValues.colorCard);

      this.shoppingListService.editShoppingList(this.id!, formValues).pipe(
        switchMap(() => this.shoppingListService.fetchShoppingLists())
      ).subscribe(
        (updatedShoppingSchedules: ShoppingSchedule[]) => {
          this.shoppingListService.shoppingListChanged.next(updatedShoppingSchedules);
          this.onCancel();
        }
      );
    } else {
      this.router.navigate(['shopping-list']);
    }
  }

}