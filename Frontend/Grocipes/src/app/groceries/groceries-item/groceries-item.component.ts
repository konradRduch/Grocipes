import { Component, Input } from '@angular/core';
import { Product } from 'src/app/product.model';

@Component({
  selector: 'app-groceries-item',
  templateUrl: './groceries-item.component.html',
  styleUrls: ['./groceries-item.component.css']
})
export class GroceriesItemComponent {
  @Input() product!: Product;
  



}
