import { formatDate } from '@angular/common';
import { Component, Inject, LOCALE_ID, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ShoppingList } from 'src/app/model/shopping-list.model';
import { ShoppingSchedule } from 'src/app/model/shoppingSchedule.model';
import { ImageService } from 'src/app/service/image.service';
import { ShoppingListService } from 'src/app/service/shopping-list.service';

@Component({
  selector: 'app-day-details',
  templateUrl: './day-details.component.html',
  styleUrls: ['./day-details.component.css']
})
export class DayDetailsComponent implements OnInit{
  active = 1;

  imageUrl: string | null = null;
  selectedFile: File | null = null;
  date: string | undefined;
  shoppingLists: ShoppingList[] = [];
  
  constructor(
    private imageService: ImageService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private shoppingListService: ShoppingListService,
    @Inject(LOCALE_ID) private locale: string // Inject dynamic locale
  ) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      (params: Params)=>{
        console.log(params);
        const routeDate = params['date'];
        this.date = typeof routeDate === 'string' ? routeDate : formatDate(routeDate, 'yyyy-MM-dd', this.locale);
      }
    );
    this.shoppingListService.fetchShoppingLists().subscribe(
      (data: ShoppingSchedule[]) => {
        if (this.date) {
          const filteredList = data[0].shoppingList.filter((item) => {
            const itemDate = formatDate(item.shopping_date, 'yyyy-MM-dd', this.locale); // Dynamic locale
            return itemDate === this.date;
          });
          console.log(filteredList);
          this.shoppingLists = filteredList;
          
        }
      }
    );
    this.loadImage('cheese.jpg');
  }

  loadImage(name: string) {
    this.imageService.getImage(name).subscribe(blob => {
      const url = URL.createObjectURL(blob);
      this.imageUrl = url;
    });
  }


  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0]; // Przechwytywanie wybranego pliku
  }

  upload() {
    if (this.selectedFile) {
        this.imageService.uploadImage(this.selectedFile).subscribe(
            response => {
                console.log('Obraz został dodany:', response.message); // Oczekuj obiektu z kluczem "message"
                // Możesz dodać dodatkową logikę, np. resetować formularz
            },
            error => {
                console.error('Wystąpił błąd podczas dodawania obrazu:', error);
            }
        );
    } else {
        console.error('Proszę wybrać plik do przesłania.');
    }
}



}
