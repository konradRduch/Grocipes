import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({ providedIn: 'root' })
export class ImageService {

    private imageSubject = new BehaviorSubject<string | null>(null); // Przechowuje URL obrazu
    public image$ = this.imageSubject.asObservable(); // Observable do subskrypcji w komponentach

    constructor(private http: HttpClient){
    }

    getImageById(id: number): Observable<any>{
        return this.http.get(`http://localhost:8080/images/downloadImage/${id}`, { responseType: 'blob' });
    }

    uploadImageById(id: number, file: File): Observable<any> {
        const formData = new FormData();
        formData.append('image', file); // Dodaj obraz do FormData
        return this.http.post(`http://localhost:8080/images/upload/${id}`, formData); // Wysyłanie obrazu do backendu
    }
    deleteImageById(id:number){
        return this.http.delete(`http://localhost:8080/images/delete/${id}`);
    }

    // Funkcja aktualizująca URL obrazu
    updateImage(url: string | null): void {
        this.imageSubject.next(url); // Zaktualizowanie stanu obrazu
    }

}
