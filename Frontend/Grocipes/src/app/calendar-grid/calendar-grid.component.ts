import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';

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

  constructor() {
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
}
