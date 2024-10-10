import { Component, OnInit } from '@angular/core';
import { GroceriesService } from '../service/groceries.service';
import { Product } from '../model/product.model';
import { Subscription } from 'rxjs';
import { DataStorageService } from '../service/data-storage.service';

@Component({
  selector: 'app-groceries',
  templateUrl: './groceries.component.html',
  styleUrls: ['./groceries.component.css']
})
export class GroceriesComponent implements OnInit {
  groceries: Product[] | undefined;
  subscription: Subscription = new Subscription;
  constructor(private groceriesService: GroceriesService,private dataStorageService: DataStorageService){
  
  }

  ngOnInit(): void {
    this.dataStorageService.fetchGroceries().subscribe();
    this.subscription = this.groceriesService.groceriesChanged
    .subscribe(
      (data: Product[]) =>{
        this.groceries = data;  
      }
    );
    this.groceries = this.groceriesService.getGroceries();
  }
}
