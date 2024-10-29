import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-nutrition-day-details',
  templateUrl: './nutrition-day-details.component.html',
  styleUrls: ['./nutrition-day-details.component.css']
})
export class NutritionDayDetailsComponent {

  selectedDate: string | undefined;

constructor(private route: ActivatedRoute) {}

ngOnInit() {
  this.selectedDate = this.route.snapshot.paramMap.get('date')!;
  this.loadDetails();
}

loadDetails() {
  // Metoda do pobrania szczegółów na podstawie `selectedDate`
}
}
