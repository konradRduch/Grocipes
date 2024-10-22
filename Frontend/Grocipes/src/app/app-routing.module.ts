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
import { AdminPanelComponent } from "./admin-panel/admin-panel.component";
import { ManageProductsComponent } from "./admin-panel/manage-products/manage-products.component";
import { ManageRecipesComponent } from "./admin-panel/manage-recipes/manage-recipes.component";
import { AddProductComponent } from "./admin-panel/manage-products/add-product/add-product.component";
import { EditProductComponent } from "./admin-panel/manage-products/edit-product/edit-product.component";
import { AddRecipeComponent } from "./admin-panel/manage-recipes/add-recipe/add-recipe.component";
import { EditRecipeComponent } from "./admin-panel/manage-recipes/edit-recipe/edit-recipe.component";
import { ShoppingListItemComponent } from "./shopping-list/shopping-list-item/shopping-list-item.component";
import { ShoppingListItemDetailsComponent } from "./shopping-list/shopping-list-item/shopping-list-item-details/shopping-list-item-details.component";
import { ShoppingListEditComponent } from "./shopping-list/shopping-list-edit/shopping-list-edit.component";
import { ShoppingListAddComponent } from "./shopping-list/shopping-list-add/shopping-list-add.component";
import { BodyParametersHistoryComponent } from "./profile/body-parameters-history/body-parameters-history.component";
import { UpdateBodyParametersComponent } from "./profile/update-body-parameters/update-body-parameters.component";

const appRoutes: Routes = [
    {path: '', redirectTo: 'home',pathMatch: 'full'},
    {
        path: 'admin-panel',
        component: AdminPanelComponent,
        canActivate: [AuthGuard],
        children: [
          { path: 'manage-products', component: ManageProductsComponent, canActivate: [AuthGuard], 
            children: [
              {path: 'add-product', component: AddProductComponent,canActivate: [AuthGuard]},
              {path: 'edit-product/:id', component: EditProductComponent,canActivate: [AuthGuard]}
            ]
          },
          { path: 'manage-recipes', component: ManageRecipesComponent, canActivate: [AuthGuard], 
            children: [
              {path: 'add-recipe', component: AddRecipeComponent,canActivate: [AuthGuard]},
              {path: 'edit-recipe/:id', component: EditRecipeComponent,canActivate: [AuthGuard]}
            ]
           }
        ]
      },
    {path: 'home', component: HomepageComponent, canActivate: [AuthGuard]},
    {path: 'recipes', component: RecipesComponent, canActivate: [AuthGuard]},
    {path: 'recipes/:title', component: RecipesItemDetailsComponent, canActivate: [AuthGuard]},
    {path: 'groceries', component: GroceriesComponent, canActivate: [AuthGuard]},
    {path: 'groceries/:name', component: GroceriesItemDetailsComponent, canActivate: [AuthGuard]},
    {path: 'shopping-list',component: ShoppingListComponent, canActivate: [AuthGuard]},
    {path: 'shopping-list/shopping-list-item-details/:id', component: ShoppingListItemDetailsComponent, canActivate: [AuthGuard]},
    {path: 'shopping-list/shopping-list-edit/:id', component: ShoppingListEditComponent, canActivate: [AuthGuard]},
    {path: 'shopping-list/shopping-list-add', component: ShoppingListAddComponent, canActivate: [AuthGuard]},
    {path: 'nutritionSchedule', component: NutritionScheduleComponent, canActivate: [AuthGuard]},
    {path: 'shoppingSchedule', component: ShoppingScheduleComponent, canActivate: [AuthGuard]},
    {path: 'shopListDetails', component: ShopListItemDetailsComponent, canActivate: [AuthGuard]},
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
    {path: 'profile/body-parameters-history', component: BodyParametersHistoryComponent, canActivate: [AuthGuard]},
    {path: 'profile/body-parameters-update', component: UpdateBodyParametersComponent, canActivate: [AuthGuard]},
    {path: 'auth', component: AuthenticationComponent, canActivate:[AuthReverseGuard] }
];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule{

}