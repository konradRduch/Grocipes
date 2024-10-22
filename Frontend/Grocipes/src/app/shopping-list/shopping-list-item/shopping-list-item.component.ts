import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ProductShoppingLists } from 'src/app/model/productShoppingList.model';
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
  selector: 'app-shopping-list-item',
  templateUrl: './shopping-list-item.component.html',
  styleUrls: ['./shopping-list-item.component.css']
})
export class ShoppingListItemComponent implements OnInit{
  @Input() shoppList: ShoppingList | undefined;
  id: number | undefined;
  cardColor: number| undefined;
  active: boolean = true;
  enum: typeof Color = Color;

  @Output() shoppingListDeleted = new EventEmitter<number>();

  productShoppingLists: ProductShoppingLists[] |undefined;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private shoppingListService: ShoppingListService){

  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
      }
    )
    this.cardColor = this.shoppList?.cardColor;
    this.id = this.shoppList?.id;
  }

  
  onEdit(){
    this.router.navigate(['shopping-list/shopping-list-edit/',`${this.id}`]);
  }
  onDelete(){
    this.shoppingListService.deleteShoppingList(this.id!).subscribe(() => {
      this.shoppingListDeleted.emit(this.id!);
    });
  }

}
