export class NutritionalGoal {
    public id: number;
    public name: string;
    public typeOfGoal: string;
    public targetWeight: number;
    public targetAbdominalCircumference: number;
    public targetBodyFatLevel: number;
    public goalStartDate: string;
    public goalEndDate: string;
    public active: boolean;
    public progress: number;


    constructor(id: number, name: string, typeOfGoal: string, targetWeight: number, targetAbdominalCircumference: number,targetBodyFatLevel: number, goalStartDate: string, goalEndDate: string, active: boolean, progress: number){
        this.id = id;
        this.name = name;
        this.typeOfGoal = typeOfGoal;
        this.targetWeight = targetWeight;
        this.targetAbdominalCircumference = targetAbdominalCircumference;
        this.targetBodyFatLevel = targetBodyFatLevel;
        this.goalStartDate = goalStartDate;
        this.goalEndDate = goalEndDate;
        this.active = active; 
        this.progress = progress;
    }

}