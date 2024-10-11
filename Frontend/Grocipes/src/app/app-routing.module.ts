import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { RecipesComponent } from "./recipes/recipes.component";
import { GroceriesComponent } from "./groceries/groceries.component";
import { NutritionScheduleComponent } from "./nutrition-schedule/nutrition-schedule.component";
import { ShoppingScheduleComponent } from "./shopping-schedule/shopping-schedule.component";
import { HomepageComponent } from "./homepage/homepage.component";
import { GroceriesItemDetailsComponent } from "./groceries/groceries-item/groceries-item-details/groceries-item-details.component";
import { RecipesItemDetailsComponent } from "./recipes/recipes-item/recipes-item-details/recipes-item-details.component";
import { ProfileComponent } from "./profile/profile.component";
import { ShopListItemDetailsComponent } from "./shop-list-item/shop-list-item-details/shop-list-item-details.component";
import { AuthenticationComponent } from "./authentication/authentication.component";
import { AuthGuard } from "./auth-guard";
import { AuthReverseGuard } from "./authReverse-guard";
import { ShoppingListComponent } from "./shopping-list/shopping-list.component";

const appRoutes: Routes = [
    {path: '', redirectTo: 'home',pathMatch: 'full'},
    {path: 'home', component: HomepageComponent, canActivate: [AuthGuard]},
    {path: 'recipes', component: RecipesComponent, canActivate: [AuthGuard]},
    {path: 'recipes/:title', component: RecipesItemDetailsComponent, canActivate: [AuthGuard]},
    {path: 'groceries', component: GroceriesComponent, canActivate: [AuthGuard]},
    {path: 'groceries/:name', component: GroceriesItemDetailsComponent, canActivate: [AuthGuard]},
    {path: 'shopping-list',component: ShoppingListComponent, canActivate: [AuthGuard]},
    {path: 'nutritionSchedule', component: NutritionScheduleComponent, canActivate: [AuthGuard]},
    {path: 'shoppingSchedule', component: ShoppingScheduleComponent, canActivate: [AuthGuard]},
    {path: 'shopListDetails', component: ShopListItemDetailsComponent, canActivate: [AuthGuard]},
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
    {path: 'auth', component: AuthenticationComponent, canActivate:[AuthReverseGuard] }
];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule{

}