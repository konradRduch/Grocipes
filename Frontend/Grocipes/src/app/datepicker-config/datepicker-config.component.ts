import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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

  @Output() dateChange = new EventEmitter<{ year: number; month: number; day: number }>();
  
  constructor(config: NgbInputDatepickerConfig, calendar: NgbCalendar) {
  }
  
  ngOnInit(): void {
    const date = new Date();
    this.minDate = {
      year: date.getFullYear(),
      month: date.getMonth() + 1, // miesiące w Date zaczynają się od 0
      day: date.getDate()
    };
    console.log(this.minDate);
  }

  onDateChange(date: { year: number; month: number; day: number }) {
    this.dateChange.emit(date);
  }
  
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
