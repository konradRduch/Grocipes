import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GroceriesService } from 'src/app/groceries.service';
import { Product } from 'src/app/product.model';

@Component({
  selector: 'app-groceries-item-details',
  templateUrl: './groceries-item-details.component.html',
  styleUrls: ['./groceries-item-details.component.css'],
  providers: [GroceriesService]
})
export class GroceriesItemDetailsComponent implements OnInit{
  @Input() product: Product | undefined;

  constructor(private route: ActivatedRoute, private groceriesService: GroceriesService){
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const productName = params.get('name');
      if (productName) {
        this.product = this.groceriesService.getProductByName(productName);
      }
    });
  }
}
