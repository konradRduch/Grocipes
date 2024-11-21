import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { GroceriesService } from 'src/app/service/groceries.service';
import { Product } from 'src/app/model/product.model';
import { DataStorageService } from 'src/app/service/data-storage.service';
import { ProductUnit } from 'src/app/enums/product-unit.enum';


@Component({
  selector: 'app-groceries-item-details',
  templateUrl: './groceries-item-details.component.html',
  styleUrls: ['./groceries-item-details.component.css']
})
export class GroceriesItemDetailsComponent implements OnInit{
  product: Product | undefined;
  name: string | undefined;

  enum: ProductUnit | undefined;
  unit: string | undefined;

  constructor(private route: ActivatedRoute, private groceriesService: GroceriesService, private dataStorageService: DataStorageService){
  }

  ngOnInit(): void {
    this.route.params
      .subscribe(
        (params: Params) =>{
          this.name = params['name'];
          this.product = this.groceriesService.getProductByName(this.name!);

          if (!this.product) {
            this.dataStorageService.fetchGroceries().subscribe((recipes: Product[]) => {
              this.product = this.groceriesService.getProductByName(this.name!); 
            });
          }

        }
      );

      
   }


   getUnitLabel(unitId: ProductUnit): string {
    return ProductUnit.toLabel(unitId); // Używamy metody z ProductUnit
  }





}
