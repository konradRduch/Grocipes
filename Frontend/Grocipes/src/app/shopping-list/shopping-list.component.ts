import { Component, OnDestroy, OnInit } from '@angular/core';
import { ShoppingList } from '../model/shopping-list.model';
import { ActivatedRoute, Router } from '@angular/router';
import { UserDataService } from '../service/user-data.service';
import { Subscription } from 'rxjs';
import { AuthService } from '../service/auth.service';
import { UserData } from '../model/userData.model';
import { ShoppingListService } from '../service/shopping-list.service';
import { ShoppingSchedule } from '../model/shoppingSchedule.model';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit, OnDestroy {

  id: number | undefined;
  shoppingList: ShoppingList[] | undefined;
  shoppingSchedule: ShoppingSchedule | undefined;

  constructor(private router: Router, private shoppingListService: ShoppingListService) { }


  ngOnInit(): void {
    this.shoppingListService.fetchShoppingLists().subscribe(
      (data: ShoppingSchedule[]) => {
        this.shoppingSchedule = data[0];
        this.shoppingList = [...this.shoppingSchedule.shoppingList];
      }
    );

    if(this.shoppingList !==undefined){
      this.shoppingListService.shoppingListChanged$.subscribe(
        (data: ShoppingSchedule[]) => {
          this.shoppingSchedule = data[0];
          this.shoppingList = [...this.shoppingSchedule.shoppingList];
        }
      );
    }
  }

  ngOnDestroy(): void {

  }
  onAdd() {
    this.router.navigate(['shopping-list/shopping-list-add']);
  }

  onShoppingListDeleted(id: number) {
    this.shoppingList = this.shoppingList?.filter(list => list.id !== id);
  }


}
