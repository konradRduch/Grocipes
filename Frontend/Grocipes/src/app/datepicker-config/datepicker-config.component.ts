import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { NgbCalendar, NgbDate, NgbDateStruct, NgbInputDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-datepicker-config',
  templateUrl: './datepicker-config.component.html',
  styleUrls: ['./datepicker-config.component.css'],
})
export class DatepickerConfigComponent implements OnInit{
  
  minDate: { year: number; month: number; day: number; } | undefined;
  model: NgbDateStruct | undefined;
  @Input() blockedRanges: { start: NgbDate, end: NgbDate }[] = [];
  @Input() date: { year: number; month: number; day: number; } | undefined; // should be compatible with your formControl

  // @Output() dateChange = new EventEmitter<{ year: number; month: number; day: number }>();
  @Output() dateChange = new EventEmitter<NgbDateStruct>();
  constructor(config: NgbInputDatepickerConfig, calendar: NgbCalendar) {
  }
  
  ngOnInit(): void {
    if (this.date) {
      this.setDateModel(this.date);
    }
    const date = new Date();
    this.minDate = {
      year: date.getFullYear(),
      month: date.getMonth() + 1, // miesiące w Date zaczynają się od 0
      day: date.getDate()
    };
    console.log(this.minDate);
  }

  ngOnChanges(changes: SimpleChanges): void {
    // Sprawdzamy, czy 'date' została zmieniona w rodzicu
    if (changes['date']) {
      this.setDateModel(changes['date'].currentValue);
    }
  }


  onDateChange(date: { year: number; month: number; day: number }) {
    console.log("emit")
    console.log(date)
    console.log("--------")
    //this.dateChange.emit(date);
    this.setDateModel(date);
    this.dateChange.emit(this.model);
  }
  // setDateModel(date:{ year: number; month: number; day: number } |  string | NgbDateStruct | undefined) {
  //   if (typeof date === 'string') {
  //     const [year, month, day] = date.split('-').map(Number);
  //     this.model = { year, month, day };
  //   } else {
  //     this.model = date || undefined;
  //   }
  // }

  setDateModel(date: { year: number; month: number; day: number } | string | undefined) {
    if (typeof date === 'string') {
      // Przekształcenie stringa 'yyyy-mm-dd' na { year, month, day }
      const [year, month, day] = date.split('-').map(Number);
      this.model = { year, month, day };
    } else if (date && typeof date === 'object' && 'year' in date && 'month' in date && 'day' in date) {
      // Obsługuje przypadek { year, month, day }
      this.model = date;
    }

  }

  // onDateChange(date: { year: number; month: number; day: number }) {
  //     console.log("emit")
  //     this.dateChange.emit(this.model);
    
  // }
  
  isDisabled = (date: NgbDate) => {
    return this.blockedRanges.some(range => {
      return date.equals(range.start) ||
             (range.end && date.equals(range.end)) ||
             this.isInside(date, range);
    });
  }

  isInside(date: NgbDate, range: { start: NgbDate; end: NgbDate }): boolean {
    return range.end && date.after(range.start) && date.before(range.end);
  }


}
