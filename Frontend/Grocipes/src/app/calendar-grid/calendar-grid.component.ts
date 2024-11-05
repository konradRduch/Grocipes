import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-calendar-grid',
  templateUrl: './calendar-grid.component.html',
  styleUrls: ['./calendar-grid.component.css']
})
export class CalendarGridComponent {
  weeks: number[] = Array(6).fill(0); // 6 tygodni
  days: number[] = Array(7).fill(0);  // 7 dni w tygodniu

  today: Date = new Date(); // Obecna data jako obiekt Date

  currentMonthDays: number[] = [];  // Dni bieżącego miesiąca
  previousMonthDays: number[] = []; // Dni poprzedniego miesiąca
  nextMonthDays: number[] = [];     // Dni następnego miesiąca
  firstDayOffset: number |any;

  monthOffset: number = 0; // Offset do przewijania miesięcy
  @Input() scheduleType: any|string;
  show: boolean = true;
  constructor(private router: Router, private activatedRoute: ActivatedRoute) {

    this.router.events.subscribe(() => {
      this.show = !this.router.url.includes('day-details')
    
  });
    this.generateCalendar();
  }

  trackByIndex(index: number, item: any): number {
    return index;
  }

  next() {
    this.monthOffset++;
    this.generateCalendar();
  }

  previous() {
    this.monthOffset--;
    this.generateCalendar();
  }

  generateCalendar() {
    const currentDate = new Date();

    // Ustalenie miesiąca i roku na podstawie monthOffset
    const year = currentDate.getFullYear();
    const month = currentDate.getMonth() + this.monthOffset;

    // Pierwszy dzień aktualnego miesiąca
    const firstDay = new Date(year, month, 1);

    // Ostatni dzień aktualnego miesiąca
    const lastDay = new Date(year, month + 1, 0);

    // Przeliczenie firstDayOffset dla poniedziałku jako pierwszego dnia tygodnia
    this.firstDayOffset = (firstDay.getDay() + 6) % 7;

    // Generowanie dni bieżącego miesiąca
    this.currentMonthDays = Array.from({ length: lastDay.getDate() }, (_, i) => i + 1);

    // Dni poprzedniego miesiąca
    const prevMonthLastDay = new Date(year, month, 0).getDate();
    this.previousMonthDays = Array.from({ length: this.firstDayOffset }, (_, i) => prevMonthLastDay - this.firstDayOffset + i + 1);

    // Obliczanie liczby dni potrzebnych z następnego miesiąca
    const remainingCells = 42 - (this.firstDayOffset + this.currentMonthDays.length); // 6 tygodni * 7 dni = 42 komórki
    this.nextMonthDays = Array.from({ length: remainingCells }, (_, i) => i + 1);

    // Aktualizacja bieżącej daty, aby uwzględnić przeskok w kalendarzu
    this.today = new Date(year, month);

  }


  // getDayDate(weekIndex: number, dayIndex: number): string {
  //   // Obliczamy dzień miesiąca na podstawie tygodnia, dnia i przesunięcia pierwszego dnia.
  //   let dayNumber: number;
  
  //   if (weekIndex === 0 && dayIndex < this.firstDayOffset) {
  //     // Dni z poprzedniego miesiąca
  //     dayNumber = this.previousMonthDays[dayIndex];
  //     return new Date(this.today.getFullYear(), this.today.getMonth() - 1, dayNumber).toISOString().split('T')[0];
  //   } else if (weekIndex === 0 && dayIndex >= this.firstDayOffset) {
  //     // Dni bieżącego miesiąca, pierwszy tydzień
  //     dayNumber = this.currentMonthDays[dayIndex - this.firstDayOffset];
  //   } else if ((weekIndex * this.days.length + dayIndex - this.firstDayOffset) < this.currentMonthDays.length) {
  //     // Dni bieżącego miesiąca, kolejne tygodnie
  //     dayNumber = this.currentMonthDays[weekIndex * this.days.length + dayIndex - this.firstDayOffset];
  //   } else {
  //     // Dni następnego miesiąca
  //     dayNumber = this.nextMonthDays[(weekIndex * this.days.length + dayIndex - this.firstDayOffset) - this.currentMonthDays.length];
  //     return new Date(this.today.getFullYear(), this.today.getMonth() + 1, dayNumber).toISOString().split('T')[0];
  //   }
  
  //   // Zwracamy datę w formacie 'YYYY-MM-DD'
  //   return new Date(this.today.getFullYear(), this.today.getMonth(), dayNumber).toISOString().split('T')[0];
  // }
  getDayDate(weekIndex: number, dayIndex: number): string {
    let year = this.today.getFullYear();
    let month = this.today.getMonth();
    let dayNumber: number;
  
    if (weekIndex === 0 && dayIndex < this.firstDayOffset) {
      // Dni z poprzedniego miesiąca
      dayNumber = this.previousMonthDays[dayIndex];
      month -= 1;
      if (month < 0) {
        month = 11; // Poprzedni miesiąc to grudzień
        year -= 1;  // Poprzedni rok
      }
    } else if (weekIndex === 0 && dayIndex >= this.firstDayOffset) {
      // Dni bieżącego miesiąca, pierwszy tydzień
      dayNumber = this.currentMonthDays[dayIndex - this.firstDayOffset];
    } else if ((weekIndex * this.days.length + dayIndex - this.firstDayOffset) < this.currentMonthDays.length) {
      // Dni bieżącego miesiąca, kolejne tygodnie
      dayNumber = this.currentMonthDays[weekIndex * this.days.length + dayIndex - this.firstDayOffset];
    } else {
      // Dni następnego miesiąca
      dayNumber = this.nextMonthDays[(weekIndex * this.days.length + dayIndex - this.firstDayOffset) - this.currentMonthDays.length];
      month += 1;
      if (month > 11) {
        month = 0; // Następny miesiąc to styczeń
        year += 1;  // Następny rok
      }
    }
  
    // Tworzymy datę w formacie 'YYYY-MM-DD' bez przesunięcia czasowego
    const formattedMonth = (month + 1).toString().padStart(2, '0'); // Miesiące są indeksowane od 0, więc dodajemy 1
    const formattedDay = dayNumber.toString().padStart(2, '0');
  
    return `${year}-${formattedMonth}-${formattedDay}`;
  }


  isToday(weekIndex: number, dayIndex: number): boolean {
    const today = new Date();

    const dayDateString = this.getDayDate(weekIndex, dayIndex); // Uzyskaj wynik jako string
    const dayDate = new Date(dayDateString); // Konwertuj na Date
    
    return (
      dayDate.getDate() === today.getDate() &&
      dayDate.getMonth() === today.getMonth() &&
      dayDate.getFullYear() === today.getFullYear()
    );
  }

}
