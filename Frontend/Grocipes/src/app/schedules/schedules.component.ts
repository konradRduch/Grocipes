import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrls: ['./schedules.component.css']
})
export class SchedulesComponent {

  showCards: boolean = true;

  constructor(private router: Router) {
      this.router.events.subscribe(() => {
          this.showCards = !this.router.url.includes('nutrition') && !this.router.url.includes('shopping');
      });
  }

}
