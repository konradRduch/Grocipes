import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

export interface AuthResponseData{
    accessToken: string;
}

@Injectable({providedIn: 'root'})
export class AuthService{
    constructor(private http: HttpClient){}

    signUp(email: String, password: String){
        const url = 'http://localhost:8080/auth/register'; 
        return this.http.post<AuthResponseData>(url,
            {
                "email": email,
                "password": password
            }
         );
    }

    login(email: String, password: String){
        const url = 'http://localhost:8080/auth/login'; 
        return this.http.post<AuthResponseData>(url,
            {
                "email": email,
                "password": password
            }
         );
    }


}