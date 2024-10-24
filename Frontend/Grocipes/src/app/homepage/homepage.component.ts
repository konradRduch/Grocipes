import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ShopListItemComponent } from '../shop-list-item/shop-list-item.component';
import { ActivatedRoute, Router } from '@angular/router';
import { ShoppingListService } from '../service/shopping-list.service';
import { UserData } from '../model/userData.model';
import { ShoppingList } from '../model/shopping-list.model';
import { ShoppingSchedule } from '../model/shoppingSchedule.model';
import { Subscription } from 'rxjs';
import { AuthService } from '../service/auth.service';
import { UserDataService } from '../service/user-data.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit, OnDestroy{

  shoppingList: ShoppingList[] | undefined;
  shoppingSchedule: ShoppingSchedule | undefined;
  constructor(private shoppingListService: ShoppingListService) { }


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


}
