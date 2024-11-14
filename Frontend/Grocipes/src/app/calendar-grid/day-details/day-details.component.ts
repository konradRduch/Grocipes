import { formatDate } from '@angular/common';
import { Component, Inject, LOCALE_ID, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { RecipeTypeOfMeal } from 'src/app/enums/recipe-type-of-meal.enum';
import { EatDeadline } from 'src/app/model/eat-deadline.model';
import { ShoppingList } from 'src/app/model/shopping-list.model';
import { ShoppingSchedule } from 'src/app/model/shoppingSchedule.model';
import { EatDeadlineService } from 'src/app/service/eat-deadline.service';
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

  eatDeadlines: EatDeadline[] =[];
  breakfast: EatDeadline| undefined;
  lunch: EatDeadline| undefined;
  dinner: EatDeadline| undefined;

  breakfastImageUrl: string | null = null;
  lunchImageUrl: string | null = null;
  dinnerImageUrl: string | null = null;

  
  constructor(
    private imageService: ImageService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private shoppingListService: ShoppingListService,
    private eatDeadlineService: EatDeadlineService,
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

    this.eatDeadlineService.getAllEatDeadline().subscribe(
      (data: EatDeadline[]) =>{
        if(this.date){
          const filteredList = data.filter((item) => {
            const itemDate = formatDate(item.eatingDate, 'yyyy-MM-dd', this.locale); // Dynamic locale
            return itemDate === this.date;
          });
          this.eatDeadlines = filteredList;
          console.log(this.eatDeadlines)

          this.breakfast = this.eatDeadlines.find(item => item.typeOfMeal === RecipeTypeOfMeal.BREAKFAST);
          this.lunch = this.eatDeadlines.find(item => item.typeOfMeal === RecipeTypeOfMeal.LUNCH);
          this.dinner = this.eatDeadlines.find(item => item.typeOfMeal === RecipeTypeOfMeal.DINNER);
          console.log('daads')
          console.log(this.lunch)
        }
      }
    );

    this.loadImage('cheese.jpg');
  }

  loadImage(name: string) {
    this.imageService.getImage(name).subscribe(blob => {
      const url = URL.createObjectURL(blob);
      this.imageUrl =null;
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
