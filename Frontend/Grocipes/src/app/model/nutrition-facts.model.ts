export class NutritionFacts{
    public name: String;
    public amount: number;
    public dailyValue: number;

    constructor(name: String, amount: number, dailyValue: number){
        this.name = name;
        this.amount = amount;
        this.dailyValue = dailyValue;
    }
}