import { Component, Input } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ShoppingList } from 'src/app/model/shopping-list.model';

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
export class ShoppingListItemDetailsComponent {
onAddProductToShhopingList() {
throw new Error('Method not implemented.');
}

  shoppList: ShoppingList = new ShoppingList(1,"Daily product", 0);
  id: number | undefined;
  cardColor: number| undefined;
  active: boolean = true;
  enum: typeof Color = Color;

  constructor(private router: Router, private activatedRoute: ActivatedRoute){

  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
        
      }
    )
    this.cardColor = this.shoppList?.color;
  }

}
