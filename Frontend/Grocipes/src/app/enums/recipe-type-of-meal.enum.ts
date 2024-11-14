export enum RecipeTypeOfMeal {
BREAKFAST = "Breakfast",
LUNCH = "Lunch",
DINNER = "Dinner"    
}

export namespace RecipeTypeOfMeal { 
    export function getAllTypeOfMeal(): RecipeTypeOfMeal[] {
        return [
            RecipeTypeOfMeal.BREAKFAST,
            RecipeTypeOfMeal.LUNCH,
            RecipeTypeOfMeal.DINNER
        ]
    }
    export function getTypeOfMeal(typeOfMeal: RecipeTypeOfMeal): string {
        return typeOfMeal;
    }
    
}