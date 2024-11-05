import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ShoppingList } from 'src/app/model/shopping-list.model';
import { ShoppingListService } from 'src/app/service/shopping-list.service';

enum Color {
  BLUE,
  GRAY,
  GREEN,
  YELLOW,
  TURQUOISE,
  BLACK
}
@Component({
  selector: 'app-shopping-list-item-details',
  templateUrl: './shopping-list-item-details.component.html',
  styleUrls: ['./shopping-list-item-details.component.css']
})
export class ShoppingListItemDetailsComponent implements OnInit{


  shoppList: ShoppingList |undefined;
  
  id: number | undefined;
  cardColor: number| undefined;
  active: boolean = true;
  enum: typeof Color = Color;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private shoppingListService: ShoppingListService){

  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
        this.shoppingListService.getShoppingList(this.id).subscribe(
          (data: ShoppingList)=>{
            this.shoppList = data;
            console.log(this.shoppList)
          }
        );
      }
    )
    this.cardColor = this.shoppList?.cardColor;
    console.log(this.shoppList);
  }

  exit(){
    this.router.navigate(['shopping-list']);
  }

  onToggleDone(id: number, productShoppingList: any) {
    this.shoppingListService.toggleProduct(id, productShoppingList.id).subscribe(() => {
      this.refreshShoppingList();
    });
  }

  likeShoppingList(productShoppingList: any){
    this.shoppingListService.likeShoppingList(productShoppingList.id).subscribe(() => {
      this.refreshShoppingList();
    });
  }

  private refreshShoppingList() {
    if (this.id) {
      this.shoppingListService.getShoppingList(this.id).subscribe((data: ShoppingList) => {
        this.shoppList = data;
      });
    }
  }
}
