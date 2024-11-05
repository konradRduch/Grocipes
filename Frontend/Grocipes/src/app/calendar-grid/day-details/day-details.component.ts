import { Component, OnInit } from '@angular/core';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-day-details',
  templateUrl: './day-details.component.html',
  styleUrls: ['./day-details.component.css']
})
export class DayDetailsComponent implements OnInit{
  active = 1;

  imageUrl: string | null = null;
  selectedFile: File | null = null;

  constructor(private imageService: ImageService) {}

  ngOnInit() {
    this.loadImage('cheese.jpg');
  }

  loadImage(name: string) {
    this.imageService.getImage(name).subscribe(blob => {
      const url = URL.createObjectURL(blob);
      this.imageUrl = url;
    });
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
