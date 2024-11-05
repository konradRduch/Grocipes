export enum NutritionalGoalType{
WEIGHT_LOSE = "Weight loss",
WEIGHT_MAINTENANCE = "Weight maintenance",
WEIGHT_GAIN = "Weight gain"
}

export namespace NutritionalGoalType {
  
    // Metoda do zwrócenia wszystkich wartości enumeratora
    export function getAllGoals(): NutritionalGoalType[] {
      return Object.values(NutritionalGoalType).filter(value => typeof value === 'string') as NutritionalGoalType[];
    }
  
    // Metoda do sprawdzenia, czy dana wartość jest prawidłowa
    export function isValidGoal(goal: string): boolean {
      return getAllGoals().includes(goal as NutritionalGoalType);
    }
  
    // Metoda do uzyskania opisu dla każdego typu celu
    export function getDescription(goal: NutritionalGoalType): string {
      switch (goal) {
        case NutritionalGoalType.WEIGHT_LOSE:
          return "Goal focused on losing weight.";
        case NutritionalGoalType.WEIGHT_MAINTENANCE:
          return "Goal focused on maintaining current weight.";
        case NutritionalGoalType.WEIGHT_GAIN:
          return "Goal focused on gaining weight.";
        default:
          return "Unknown goal.";
      }
    }
  
    // Metoda do wygenerowania opcji do selecta
    export function getSelectOptions(): { value: NutritionalGoalType; label: string }[] {
      return getAllGoals().map(goal => ({
        value: goal,
        label: getDescription(goal)
      }));
    }

    export function getDisplayName(type: NutritionalGoalType): string {
      return type;
    }

  }