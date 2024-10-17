import { Component } from '@angular/core';
import { ShoppingList } from '../model/shopping-list.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent {

  constructor(private router: Router, private activatedRoute: ActivatedRoute){}

  shoppingList: ShoppingList[] = [ 
    new ShoppingList(1,"Daily product", 0),
    new ShoppingList(2,"BBQ product", 1),
    new ShoppingList(3,"Gym product", 2),
    new ShoppingList(4,"Birthday party product", 3),
    new ShoppingList(5,"Gym product", 4),
    new ShoppingList(6,"Gym product", 5)
  ];


  onAdd(){
    this.router.navigate(['shopping-list/shopping-list-add']);
  }

}
