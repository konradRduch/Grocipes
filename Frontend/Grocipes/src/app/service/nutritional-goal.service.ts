import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { NutritionalGoal } from "../model/nutritional-goal.model";
import { HttpClient } from "@angular/common/http";

@Injectable({providedIn: "root"})
export class NutritionalGoalService{

    nutritionalGoalChanged =  new BehaviorSubject<NutritionalGoal[]>([]);
    nutritionalGoalChanged$ = this.nutritionalGoalChanged.asObservable();


    constructor(private http: HttpClient){

    }
    //pobierz wszystkie cele
    getNutritionalGoal(id: number): Observable<NutritionalGoal>{
        return this.http.get<NutritionalGoal>(`http://localhost:8080/nutritionalGoal/${id}`);
    }
    //pobierz konkretny cel po id
    getNutritionalGoals(): Observable<NutritionalGoal[]>{
        return this.http.get<NutritionalGoal[]>(`http://localhost:8080/nutritionalGoal/getAll`).pipe(
            tap(data =>{
                this.nutritionalGoalChanged.next(data);
            })
        );
    }

    getGoalDailyDemands(): Observable<{protein: number, carbs: number, fats: number, calories: number}>{
        return this.http.get<{protein: number, carbs: number, fats: number, calories: number}>(`http://localhost:8080/nutritionalGoal/getGoalDailyDemand`);
    }

    //dodaj cel
    addNutritionalGoal(dto: any){
        console.log(dto);
        const addNutritionalGoal = {
            name: dto.name,
            typeOfGoal: dto.typeOfGoal,
            goalStartDate: dto.goalStartDate,
            goalEndDate: dto.goalEndDate,
            targetWeight: dto.targetWeight,
            targetAbdominalCircumference: dto.targetAbdominalCircumference,
            targetBodyFatLevel: dto.targetBodyFatLevel,
            isActive: false
        }
        console.log(addNutritionalGoal);
        return this.http.post<any>(`http://localhost:8080/nutritionalGoal/add`,addNutritionalGoal);
    }
    //usun cel
    deleteNutritionalGoal(id: number){
        return this.http.delete<NutritionalGoal>(`http://localhost:8080/nutritionalGoal/delete/${id}`);
    }
    //edytuj cel
    editNutritionalGoal(id: number, dto: any){
        const editNutritionalGoal = {

        }
        return this.http.patch<any>(`http://localhost:8080/nutritionalGoal/edit/${id}`,editNutritionalGoal);
    }
    //realizuj cel - zmiana z false na true
    
    //zakończ cel

    getEstimatedTimeToAchieveGoal(currentWeight: number,targetWeight: number, typeOfGoal: string){
        console.log(currentWeight);
        console.log(targetWeight);
        console.log(typeOfGoal);
        const estimateTime = {
            currentWeight: currentWeight,
            targetWeight: targetWeight,
            typeOfGoal: typeOfGoal
        }
        return this.http.post<any>(`http://localhost:8080/nutritionalGoal/getEstimatedTimeToAchieveGoal`, estimateTime);
    }
  
}