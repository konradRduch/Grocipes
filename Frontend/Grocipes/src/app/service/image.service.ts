import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root' })
export class ImageService {

    constructor(private http: HttpClient){
    }


    getImage(name: string){
        return this.http.get(`http://localhost:8080/images/${name}`, { responseType: 'blob' });
    }

    uploadImage(file: File): Observable<any> {
        const formData = new FormData();
        formData.append('image', file); // Dodaj obraz do FormData
        return this.http.post(`http://localhost:8080/images`, formData); // Wysyłanie obrazu do backendu
    }

    getImageById(id: number){
        return this.http.get(`http://localhost:8080/images/downloadImage/${id}`, { responseType: 'blob' });
    }


}
