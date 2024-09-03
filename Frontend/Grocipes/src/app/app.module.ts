import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { RecipesComponent } from './recipes/recipes.component';
import { GroceriesComponent } from './groceries/groceries.component';
import { NutritionScheduleComponent } from './nutrition-schedule/nutrition-schedule.component';
import { ShoppingScheduleComponent } from './shopping-schedule/shopping-schedule.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AppRoutingModule } from './app-routing.module';
import { HeaderComponent } from './header/header.component';
import { CalendarGridComponent } from './calendar-grid/calendar-grid.component';
import { ShopListItemComponent } from './shop-list-item/shop-list-item.component';
import { GroceriesItemComponent } from './groceries/groceries-item/groceries-item.component';
import { GroceriesItemDetailsComponent } from './groceries/groceries-item/groceries-item-details/groceries-item-details.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipesComponent,
    GroceriesComponent,
    NutritionScheduleComponent,
    ShoppingScheduleComponent,
    HomepageComponent,
    HeaderComponent,
    CalendarGridComponent,
    ShopListItemComponent,
    GroceriesItemComponent,
    GroceriesItemDetailsComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
