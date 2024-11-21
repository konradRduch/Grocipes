export class EatDeadline{
    public  id: number;
    public  recipeId: number;
    public  title:string;
    public  typeOfMeal:string;
    public  eatingDate:string;
    public  done: boolean;
    public  rate: number;
    public  comment:string;
    public  totalPrice: number;

    constructor(id: number, recipeId:number, title: string,typeOfMeal:string ,eatingDate: string, done: boolean, rate: number, comment: string, totalPrice: number){
        this.id = id;
        this.recipeId = recipeId;
        this.title = title;
        this.typeOfMeal = typeOfMeal;
        this.eatingDate = eatingDate;
        this.done = done;
        this.rate = rate;
        this.comment = comment;
        this.totalPrice = totalPrice;
    }

}