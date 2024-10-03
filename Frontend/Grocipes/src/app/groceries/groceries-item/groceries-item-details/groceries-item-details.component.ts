import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { GroceriesService } from 'src/app/service/groceries.service';
import { Product } from 'src/app/model/product.model';

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
    // Wywołujemy pobranie danych przed próbą wyświetlenia szczegółów
    this.groceriesService.fetchData();

    this.route.paramMap.subscribe(params => {
      const productName = params.get('name');
      if (productName) {
        // Subskrybujemy Observable, aby pobrać produkt po załadowaniu danych
        this.groceriesService.getProductByName(productName).subscribe(product => {
          this.product = product;
          console.log(this.product)
        });
      }
    });
  }
}
