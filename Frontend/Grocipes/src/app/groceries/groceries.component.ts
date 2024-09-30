import { Component, OnInit } from '@angular/core';
import { GroceriesService } from '../service/groceries.service';
import { Product } from '../model/product.model';

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
    this.groceriesService.fetchData();
    this.groceriesService.getGroceries().subscribe(
      (data: Product[]) => {
        this.groceries = data;  
        // Przypisanie danych po subskrypcji Observable
      },
      (error) => {
        console.error('Error fetching groceries', error);
      }
    );
  }


}
