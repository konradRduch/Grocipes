import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription, switchMap } from 'rxjs';
import { Product } from 'src/app/model/product.model';
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
  selector: 'app-shopping-list-add',
  templateUrl: './shopping-list-add.component.html',
  styleUrls: ['./shopping-list-add.component.css']
})
export class ShoppingListAddComponent implements OnInit, OnDestroy {
  
  shoppingListForm: FormGroup = new FormGroup({});
  availableProducts: Product[] = []; // Przy
  enum: typeof Color = Color;
  availableUnit: {id: number, name: string }[] = [];

  userData: UserData | undefined;
  userName: string | undefined;
  private userSub: Subscription = new Subscription();


  constructor(
    private groceries2Service: Groceries2Service, 
    private router: Router, 
    private shoppingListService: ShoppingListService,
    private authService: AuthService,
    private userService: UserDataService
  ) {

  }
 

  ngOnInit(): void {
    this.userSub = this.authService.user.subscribe(user => {
      this.userName = user?.email
    }
    );

    this.userService.fetchUser(this.userName!).subscribe(
      (user: UserData) => {
        this.userData = user;
      }
    );
    this.initForm();
    this.loadAvailableProducts();
    this.loadAvailableUnit();
  }
  ngOnDestroy(): void {
    console.log("list add component destruktor");
    this.userSub.unsubscribe();
  }

  getEnumKeys(): string[] {
    return Object.keys(this.enum).filter(key => isNaN(Number(key)));
  }

  getEnumValue(colorName: string): number {
    return this.enum[colorName as keyof typeof Color];
  }

  private initForm() {
    this.shoppingListForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      shoppingDate: new FormControl(null, Validators.required),
      shoppingTime: new FormControl(null, Validators.required),
      colorCard: new FormControl(null, Validators.required),
      likedList: new FormControl(false),
      products: new FormArray([])
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
      done: new FormControl(false)
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
      { id: 1, name: 'pc.' },
     // { id: 2, name: 'mg' },
     // { id: 3, name: 'L' },
    ];
  }

  onCancel() {
    this.shoppingListForm.reset();
    this.router.navigate(['shopping-list']);
  }

  // onSubmit() {
  //   console.log(this.shoppingListForm.value);
  //   if (this.shoppingListForm.valid) {
  //     const formValues = this.shoppingListForm.value;
  //     const date = formValues.shoppingDate;
  //     const time = formValues.shoppingTime;

  //     // Połącz datę i godzinę w LocalDateTime
  //     const localDateTime = `${date}T${time}`;

    
  //     formValues.shoppingDate = localDateTime;
  //     formValues.colorCard = this.getEnumValue(formValues.colorCard);
      
      
  //     this.shoppingListService.addShoppingList(this.userData?.id!,formValues).pipe(
  //       switchMap(() => this.shoppingListService.fetchShoppingLists(this.userData?.id!))
  //     ).subscribe();
  //     this.onCancel()
  //   } else {
  //     this.router.navigate(['shopping-list']);
  //   }
  // }


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
  
      this.shoppingListService.addShoppingList(this.userData?.id!, formValues).pipe(
        switchMap(() => this.shoppingListService.fetchShoppingLists(this.userData?.id!))
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
