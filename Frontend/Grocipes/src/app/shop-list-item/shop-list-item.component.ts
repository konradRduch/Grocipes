import { Component, inject, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ShoppingList } from '../model/shopping-list.model';

enum Color {
  BLUE,
  GRAY,
  GREEN,
  YELLOW,
  TURQUOISE,
  BLACK
}
@Component({
  selector: 'app-shop-list-item',
  templateUrl: './shop-list-item.component.html',
  styleUrls: ['./shop-list-item.component.css']
})
export class ShopListItemComponent implements OnInit {
  @Input() shoppList: ShoppingList | undefined;
  enum: typeof Color = Color;
  cardColor: number| undefined;
  active: boolean = true;
  
  ngOnInit(): void {
  this.cardColor = this.shoppList?.cardColor;
  
  }


}
