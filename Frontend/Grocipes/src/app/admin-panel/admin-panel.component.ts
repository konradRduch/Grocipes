import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent {
  showCards: boolean = true;

  constructor(private router: Router) {
      this.router.events.subscribe(() => {
          this.showCards = !this.router.url.includes('manage-products') && !this.router.url.includes('manage-recipes');
      });
  }
}
