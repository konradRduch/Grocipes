import { Component, OnInit } from '@angular/core';
import { GroceriesService } from '../groceries.service';
import { Product } from '../product.model';

@Component({
  selector: 'app-groceries',
  templateUrl: './groceries.component.html',
  styleUrls: ['./groceries.component.css'],
  providers: [GroceriesService]
})
export class GroceriesComponent implements OnInit {
  groceries: Product[] = [];
  constructor(private groceriesService: GroceriesService){
  }

  ngOnInit(): void {
    this.groceries = this.groceriesService.getGroceries();
  }


}
