import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-shopping-day-details',
  templateUrl: './shopping-day-details.component.html',
  styleUrls: ['./shopping-day-details.component.css']
})
export class ShoppingDayDetailsComponent {

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
