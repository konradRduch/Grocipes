import { Component, Input } from '@angular/core';
import { ShoppingList } from '../model/shopping-list.model';
import { ShoppingListItemComponent } from "../shopping-list/shopping-list-item/shopping-list-item.component";

@Component({
  selector: 'app-shopping-schedule',
  templateUrl: './shopping-schedule.component.html',
  styleUrls: ['./shopping-schedule.component.css']
})
export class ShoppingScheduleComponent {
  @Input() shoppingLists: ShoppingList[]|undefined



  onShoppingListDeleted(id: number) {
    this.shoppingLists = this.shoppingLists?.filter(list => list.id !== id);
  }
}
