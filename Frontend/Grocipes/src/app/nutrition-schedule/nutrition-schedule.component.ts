import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { EatDeadlineService } from '../service/eat-deadline.service';
import { EatDeadline } from '../model/eat-deadline.model';
import { ImageService } from '../service/image.service';
import { map, Observable } from 'rxjs';

@Component({
  selector: 'app-nutrition-schedule',
  templateUrl: './nutrition-schedule.component.html',
  styleUrls: ['./nutrition-schedule.component.css']
})
export class NutritionScheduleComponent implements  OnChanges {
  newComment: string = '';
  rating = 8;
  @Input() breakfast: EatDeadline | undefined;
  @Input() lunch: EatDeadline | undefined;
  @Input() dinner: EatDeadline | undefined;

  breakfastImageUrl: string | null = null;
  lunchImageUrl: string | null = null;
  dinnerImageUrl: string | null = null;
  selectedFile: File | null = null;

  @Output() rateChange = new EventEmitter<{id: number, rate: number}>();
  @Output() commentChange = new EventEmitter<{id: number, comment: string}>();
  @Output() eatDeadlineDelete = new EventEmitter<number>();
  
  
  constructor(private imageService: ImageService) { }
  
  sendRate(id: number ,newRate: number): void {
    const obj = {
      id: id,
      rate: newRate
    }
    this.rateChange.emit(obj);
  }

  sendComment(id: number ,newComment: string): void {
    const obj = {
      id: id,
      comment: newComment
    }
    this.commentChange.emit(obj);
  }

  ngOnChanges(changes: SimpleChanges): void {
    // Sprawdź, który posiłek zmienił się i załaduj odpowiedni obraz
    if (changes['breakfast']?.currentValue) {
      this.loadImageForMeal(this.breakfast!.id, 'Breakfast');
    }

    if (changes['lunch']?.currentValue) {
      this.loadImageForMeal(this.lunch!.id, 'Lunch');
    }

    if (changes['dinner']?.currentValue) {
      this.loadImageForMeal(this.dinner!.id, 'Dinner');
    }
  }

  loadImageForMeal(id: number, meal: string): void {
    this.loadImage(id).subscribe({
      next: (url) => {
        if (meal === 'Breakfast') {
          this.breakfastImageUrl = url;
        } else if (meal === 'Lunch') {
          this.lunchImageUrl = url;
        } else if (meal === 'Dinner') {
          this.dinnerImageUrl = url;
        }
      },
      error: (error) => {
        console.error(`Error loading ${meal} image:`, error);
        if (meal === 'Breakfast') {
          this.breakfastImageUrl = null;
        } else if (meal === 'Lunch') {
          this.lunchImageUrl = null;
        } else if (meal === 'Dinner') {
          this.dinnerImageUrl = null;
        }
      },
    });
  }


  deletePhoto(id: number, meal: string) {
    this.imageService.deleteImageById(id).subscribe(() => {
      if (meal === 'Breakfast') {
        this.breakfastImageUrl = null;
      } else if (meal === 'Lunch') {
        this.lunchImageUrl = null;
      } else if (meal === 'Dinner') {
        this.dinnerImageUrl = null;
      }
      this.imageService.updateImage(null); // Po usunięciu obrazu, aktualizujemy stan na null
    });
  }

  

  loadImage(id: number): Observable<string> {
    return this.imageService.getImageById(id).pipe(
      map(blob => URL.createObjectURL(blob))
    );
  }


  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0]; // Przechwytywanie wybranego pliku
  }

  upload(id: number, meal: string) {
    if (this.selectedFile) {
      this.imageService.uploadImageById(id, this.selectedFile).subscribe({
        next: () => {
          this.loadImageForMeal(id, meal); // Po przesłaniu obrazu odśwież widok
        },
        error: (error) => {
          console.error('Błąd podczas przesyłania obrazu:', error);
        }
      });
    } else {
      console.error('Proszę wybrać plik do przesłania.');
    }
  }
  


  deleteMeal(id: number){
    this.eatDeadlineDelete.emit(id);
  }

  removeOpinion(id: number): void {
    const obj = {
      id: id,
      comment: ""
    };
    this.commentChange.emit(obj); // Emituj zdarzenie do rodzica
    console.log('Submitted comment:', obj);

    // Wyczyść pole po wysłaniu
    this.newComment = '';
  }

  submitComment(id: number): void {
    const obj = {
      id: id,
      comment: this.newComment
    };
    this.commentChange.emit(obj); // Emituj zdarzenie do rodzica
    console.log('Submitted comment:', obj);

    // Wyczyść pole po wysłaniu
    this.newComment = '';
  }

 

}
