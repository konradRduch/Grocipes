export class BodyParameter{
    public id: number;
    public weight: number;
    public height: number;
    public abdominal_circumference: number;
    public body_fat_leve: number;
    public physical_activity: string;
    public measurement_date: string;

    constructor(id: number, weight: number, height: number, abdominal_circumference:number, body_fat_leve: number, physical_activity: string, measurement_date: string){
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.abdominal_circumference = abdominal_circumference; 
        this.body_fat_leve = body_fat_leve;
        this.physical_activity = physical_activity;
        this.measurement_date = measurement_date;
    }

}