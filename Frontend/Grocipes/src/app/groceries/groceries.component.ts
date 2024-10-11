import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
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

  @ViewChild('searchtext', { static: false }) searchtext!: ElementRef<HTMLInputElement>;
  searchedProduct: String = "";
  searchedProduts: Product[] = [];
  searchMode: boolean = false;

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

  search(){
    const query = this.searchtext.nativeElement.value;
    this.searchedProduts = this.groceriesService.searchedProduts(query);
    if(this.searchtext.nativeElement.value=="")this.searchMode = false;
    else this.searchMode = true;
    console.log(this.searchMode);
  }

  onSearchInput(){
  }
  exitSearchMode(){
    this.searchMode = false;
  }

}
