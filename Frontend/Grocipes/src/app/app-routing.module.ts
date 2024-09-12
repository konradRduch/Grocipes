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

const appRoutes: Routes = [
    {path: '', component: HomepageComponent},
    {path: 'recipes', component: RecipesComponent},
    {path: 'recipes/:title', component: RecipesItemDetailsComponent},
    {path: 'groceries', component: GroceriesComponent},
    {path: 'groceries/:name', component: GroceriesItemDetailsComponent},
    {path: 'nutritionSchedule', component: NutritionScheduleComponent},
    {path: 'shoppingSchedule', component: ShoppingScheduleComponent},
    {path: 'profile', component: ProfileComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule{

}