import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UserData } from "../model/userData.model";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth.service";
import { BodyParameter } from "../model/body-parameter.model";

@Injectable({providedIn: 'root'})
export class UserDataService{

    //user data i  bodymeasuements

    constructor(private http: HttpClient){
        
    }
    fetchUser(): Observable<UserData>{
        return this.http.get<UserData>(`http://localhost:8080/userData/getUser`);
    }

    getBodyMeasurements(): Observable<BodyParameter[]>{
        return this.http.get<any>(`http://localhost:8080/userData/getAll/bodyMeasurment`);
    }

    getUserProfileInfo(): Observable<any>{
        return this.http.get<any>('http://localhost:8080/userData/getUserProfileInfo')
    }

    addBodyMeasurement(formValues: any){
        const bodyMeasurementDTO = {
            weight: formValues.weight,
            height: formValues.height,
            abdominal_circumference: formValues.abdominal_circumference,
            body_fat_leve: formValues.body_fat_level,
            physical_activity: formValues.physical_activity
        }
        return this.http.post('http://localhost:8080/userData/add/bodyMeasurment', bodyMeasurementDTO);
    }
}