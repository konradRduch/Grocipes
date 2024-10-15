import { Component, inject, OnInit, TemplateRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Subscription, switchMap } from 'rxjs';
import { Product } from 'src/app/model/product.model';
import { DataStorageService } from 'src/app/service/data-storage.service';
import { Groceries2Service } from 'src/app/service/groceries2.service';
@Component({
  selector: 'app-manage-products',
  templateUrl: './manage-products.component.html',
  styleUrls: ['./manage-products.component.css']
})
export class ManageProductsComponent implements OnInit {
  groceries: Product[] | undefined;
  subscription: Subscription = new Subscription;
  show: boolean = true;

  newProduct: Product = {
    id: 0,
    name: '',
    weight: 0,
    calories: 0,
    price: 0,
    image_url: '',
    unitName: 'g',
    unitValue: 0,
    nutrient: []
  }


  constructor(private groceries2Service: Groceries2Service, private dataStorageService: DataStorageService,
    private router: Router, private activatedRoute: ActivatedRoute) {
    this.router.events.subscribe(() => {
      this.show = !this.router.url.includes('add-product') && !this.router.url.includes('edit-product') && this.router.url.includes('manage-products');

    });
  }

  ngOnInit(): void {
    console.log("ng on init")
    this.groceries2Service.fetchGroceries().subscribe(
      (data: Product[]) => {
        this.groceries = [...data];
      }
    );

    this.groceries2Service.groceriesChanged$.subscribe(products => { 
      console.log("subscribtion")
      this.groceries = [...products]; })
    // this.dataStorageService.fetchGroceries().subscribe();
    // this.subscription = this.groceriesService.groceriesChanged
    //   .subscribe(
    //     (data: Product[]) => {
    //       this.groceries = data;
    //     }
    //   );
    // this.groceries = this.groceriesService.getGroceries();
  }

  addProduct() {
    this.router.navigate(['./add-product'], { relativeTo: this.activatedRoute });
  }

  editProduct(product: Product) {
    const productId = product.id;
    this.router.navigate(['./edit-product', productId], { relativeTo: this.activatedRoute });
  }
  deleteProduct(id: number) {
    this.groceries2Service.deleteProduct(id).pipe(
      switchMap(() => this.groceries2Service.fetchGroceries())
    ).subscribe();
    // const index = this.groceries?.indexOf(product);
    // if (index !== undefined && index > -1 ) {
    //   this.groceriesService.deleteRecipe(index);
    // }
  }

}
