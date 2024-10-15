export class NutritionFacts{
    public id: number;
    public name: String;
    public amount: number;
    public dailyValue: number;

    constructor(id: number,name: String, amount: number, dailyValue: number){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.dailyValue = dailyValue;
    }
}