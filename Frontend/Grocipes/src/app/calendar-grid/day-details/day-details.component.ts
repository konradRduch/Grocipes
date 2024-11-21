import { formatDate } from '@angular/common';
import { ChangeDetectorRef, Component, Inject, LOCALE_ID, OnChanges, OnInit, SimpleChanges } from '@angular/core';
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

  currentMealId: number | undefined;
  currentRate: number | undefined;
  currentComment: string | undefined;
  
  constructor(
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
        }
      }
    );
  }

 
   // Metoda do obsługi zmiany oceny
   handleRateChange(event: {id: number, rate: number}): void {
    if (event.id === this.breakfast?.id) {
      this.breakfast.rate = event.rate;
    } else if (event.id === this.lunch?.id) {
      this.lunch.rate = event.rate;
    } else if (event.id === this.dinner?.id) {
      this.dinner.rate = event.rate;
    }

    this.eatDeadlineService.rateMeal(event.id,event.rate).subscribe();
    console.log('Updated rate for item', event);
  }

  // Metoda do obsługi zmiany komentarza
  handleCommentChange(event: {id: number, comment: string}): void {
    if (event.id === this.breakfast?.id) {
      this.breakfast.comment = event.comment;
    } else if (event.id === this.lunch?.id) {
      this.lunch.comment = event.comment;
    } else if (event.id === this.dinner?.id) {
      this.dinner.comment = event.comment;
    }
    this.eatDeadlineService.commentMeal(event.id,event.comment).subscribe();
    console.log('Updated comment for item', event);
  }


  handleEatDeadlineDelete(id: number): void {
    this.eatDeadlineService.deleteEatDeadline(id).subscribe(() => {
      // Usuń z lokalnej listy eatDeadlines
      this.eatDeadlines = this.eatDeadlines.filter(item => item.id !== id);
  
      // Zaktualizuj widok posiłków
      if (this.breakfast?.id === id) {
        this.breakfast = undefined;
      } else if (this.lunch?.id === id) {
        this.lunch = undefined;
      } else if (this.dinner?.id === id) {
        this.dinner = undefined;
      }
  
      console.log(`Deleted eat deadline with id: ${id}`);
    });
  }




}
