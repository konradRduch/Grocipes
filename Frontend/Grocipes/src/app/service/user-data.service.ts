import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UserData } from "../model/userData.model";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "./auth.service";

@Injectable({providedIn: 'root'})
export class UserDataService{


    constructor(private http: HttpClient){
        
    }
    fetchUser(email: string): Observable<UserData>{
        return this.http.get<UserData>(`http://localhost:8080/userData/${email}`);
    }

}