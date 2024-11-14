import { Component, Input, OnInit } from '@angular/core';
import { EatDeadlineService } from '../service/eat-deadline.service';
import { EatDeadline } from '../model/eat-deadline.model';
import { ImageService } from '../service/image.service';
import { map, Observable } from 'rxjs';

@Component({
  selector: 'app-nutrition-schedule',
  templateUrl: './nutrition-schedule.component.html',
  styleUrls: ['./nutrition-schedule.component.css']
})
export class NutritionScheduleComponent implements OnInit{
  
  rating = 8;
  @Input() imageUrl: string | null = null;
  
  @Input() breakfast: EatDeadline| undefined;
  @Input() lunch: EatDeadline| undefined;
  @Input() dinner: EatDeadline| undefined;

  breakfastImageUrl: string | null = null;
  lunchImageUrl: string | null = null;
  dinnerImageUrl: string | null = null;
  selectedFile: File | null = null;

  constructor(private imageService: ImageService){}
  

  ngOnInit(): void {
    //this.loadImage2(4);
    this.loadImage2(4).subscribe({
      next: (url) => {
        this.lunchImageUrl = url;
      },
      error: (error) => {
        console.error('Error loading image:', error);
      }
    });
    this.loadImage2(3).subscribe({
      next: (url) => {
        this.breakfastImageUrl = url;
      },
      error: (error) => {
        console.error('Error loading image:', error);
      }
    });
  }
  
  exit(){

  }

  loadImage(name: string) {
    this.imageService.getImage(name).subscribe(blob => {
      const url = URL.createObjectURL(blob);
      this.imageUrl = url;
    });
  }

  loadImage2(id: number): Observable<string> { 
    return this.imageService.getImageById(id).pipe(
      map(blob => URL.createObjectURL(blob))
    );
  }


  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0]; // Przechwytywanie wybranego pliku
  }

  upload() {
    if (this.selectedFile) {
        this.imageService.uploadImage(this.selectedFile).subscribe(
            response => {
                console.log('Obraz został dodany:', response.message); // Oczekuj obiektu z kluczem "message"
                // Możesz dodać dodatkową logikę, np. resetować formularz
            },
            error => {
                console.error('Wystąpił błąd podczas dodawania obrazu:', error);
            }
        );
    } else {
        console.error('Proszę wybrać plik do przesłania.');
    }
}



}
