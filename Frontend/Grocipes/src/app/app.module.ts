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
import { RecipesItemComponent } from './recipes/recipes-item/recipes-item.component';
import { RecipesItemDetailsComponent } from './recipes/recipes-item/recipes-item-details/recipes-item-details.component';
import { ProfileComponent } from './profile/profile.component';
import { ShopListItemDetailsComponent } from './shop-list-item/shop-list-item-details/shop-list-item-details.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthenticationComponent } from './authentication/authentication.component';
import { AuthInterceptorService } from './service/auth-interceptor.service';
import { GroceriesService } from './service/groceries.service';
import { RecipesService } from './service/recepies.service';


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
    GroceriesItemDetailsComponent,
    RecipesItemComponent,
    RecipesItemDetailsComponent,
    ProfileComponent,
    ShopListItemDetailsComponent,
    AuthenticationComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [GroceriesService,RecipesService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
