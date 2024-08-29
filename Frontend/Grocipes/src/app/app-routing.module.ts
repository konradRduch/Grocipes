import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { RecipesComponent } from "./recipes/recipes.component";
import { GroceriesComponent } from "./groceries/groceries.component";
import { NutritionScheduleComponent } from "./nutrition-schedule/nutrition-schedule.component";
import { ShoppingScheduleComponent } from "./shopping-schedule/shopping-schedule.component";
import { HomepageComponent } from "./homepage/homepage.component";

const appRoutes: Routes = [
    {path: '', component: HomepageComponent},
    {path: 'recipes', component: RecipesComponent},
    {path: 'groceries', component: GroceriesComponent},
    {path: 'nutritionSchedule', component: NutritionScheduleComponent},
    {path: 'shoppingSchedule', component: ShoppingScheduleComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule{

}