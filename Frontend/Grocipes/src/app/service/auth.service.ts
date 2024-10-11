import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, catchError, Subject, tap, throwError } from "rxjs";
import { User } from "../model/user.model";
import { jwtDecode } from "jwt-decode";
import { Router } from "@angular/router";

export interface AuthResponseData {
    accessToken: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
    user = new BehaviorSubject<User | null>(null);

    private tokenExpirationDate: any;

    constructor(private http: HttpClient, private router: Router) { }

    signUp(email: String, password: String) {
        const url = 'http://localhost:8080/auth/register';
        return this.http.post<AuthResponseData>(url,
            {
                "email": email,
                "password": password
            }
        ).pipe(catchError(this.handleError), tap(resData => {
            this.handleAuthentication(resData.accessToken);
        }));
    }

    autoLogin() {
        const userDataString = localStorage.getItem('userData');

        if (!userDataString) {
            return;
        }

        const userData: {
            email: string;
            roles: string;
            _token: string;
            _tokenExpirationDate: string;
        } = JSON.parse(userDataString);

        const loadedUser = new User(
            userData.email,
            userData.roles,
            userData._token,
            new Date(userData._tokenExpirationDate)
        );

        if (loadedUser.token) {
            this.user.next(loadedUser);
            const expirationDuration =
                new Date(userData._tokenExpirationDate).getTime() -
                new Date().getTime();
            this.autoLogout(expirationDuration);
        }

    }

    login(email: String, password: String) {
        const url = 'http://localhost:8080/auth/login';
        return this.http.post<AuthResponseData>(url,
            {
                "email": email,
                "password": password
            }
        ).pipe(catchError(this.handleError), tap(resData => {
            this.handleAuthentication(resData.accessToken);
        }));
    }

    logout() {
        this.user.next(null);
        this.router.navigate(['/auth']);
        localStorage.removeItem('userData')
        if (this.tokenExpirationDate) {
            clearTimeout(this.tokenExpirationDate);
        }
        this.tokenExpirationDate = null;
    }

    autoLogout(expirationDuration: number) {
        console.log(expirationDuration);
        this.tokenExpirationDate = setTimeout(() => {
            this.logout();
        }, expirationDuration);
    }

    private handleError(errorRes: HttpErrorResponse) {
        let errorMessage = 'An unknown error occurred!';
        if (!errorRes.error || !errorRes.error.error) {
            return throwError(errorMessage);
        }
        switch (errorRes.error.error.message) {
            case 'EMAIL_EXISTS':
                errorMessage = 'This email exists already';
                break;
            case 'EMAIL_NOT_FOUND':
                errorMessage = 'This email does not exist.';
                break;
            case 'INVALID_PASSWORD':
                errorMessage = 'This password is not correct.';
                break;
        }
        return throwError(errorMessage);
    }

    private handleAuthentication(accessToken: string) {
        const token: string = accessToken;

        const decodedToken: any = jwtDecode(token);
        const expirationTime = decodedToken.exp;
        const roles = decodedToken.roles;
        const email = decodedToken.sub;

        const expirationDate = new Date(decodedToken.exp * 1000);

        const user = new User(email, roles, token, expirationDate);
        this.user.next(user);
        console.log("decodedtoken time" + decodedToken.exp);
        const expirationDuration =
                expirationDate.getTime() -
                new Date().getTime();
        this.autoLogout(expirationDuration);
        localStorage.setItem('userData', JSON.stringify(user));
    }


   

}