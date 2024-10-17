import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { ShoppingListItemComponent } from './shopping-list/shopping-list-item/shopping-list-item.component';
import { ShoppingListItemDetailsComponent } from './shopping-list/shopping-list-item/shopping-list-item-details/shopping-list-item-details.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { ManageProductsComponent } from './admin-panel/manage-products/manage-products.component';
import { ManageRecipesComponent } from './admin-panel/manage-recipes/manage-recipes.component';
import { AddProductComponent } from './admin-panel/manage-products/add-product/add-product.component';
import { EditProductComponent } from './admin-panel/manage-products/edit-product/edit-product.component';
import { EditRecipeComponent } from './admin-panel/manage-recipes/edit-recipe/edit-recipe.component';
import { AddRecipeComponent } from './admin-panel/manage-recipes/add-recipe/add-recipe.component';
import { ShoppingListAddComponent } from './shopping-list/shopping-list-add/shopping-list-add.component';
import { ShoppingListEditComponent } from './shopping-list/shopping-list-edit/shopping-list-edit.component';


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
    AuthenticationComponent,
    ShoppingListComponent,
    ShoppingListItemComponent,
    ShoppingListItemDetailsComponent,
    AdminPanelComponent,
    ManageProductsComponent,
    ManageRecipesComponent,
    AddProductComponent,
    EditProductComponent,
    EditRecipeComponent,
    AddRecipeComponent,
    ShoppingListAddComponent,
    ShoppingListEditComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [GroceriesService,RecipesService, {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
