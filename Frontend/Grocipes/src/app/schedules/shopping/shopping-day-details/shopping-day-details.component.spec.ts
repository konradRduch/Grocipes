import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingDayDetailsComponent } from './shopping-day-details.component';

describe('ShoppingDayDetailsComponent', () => {
  let component: ShoppingDayDetailsComponent;
  let fixture: ComponentFixture<ShoppingDayDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShoppingDayDetailsComponent]
    });
    fixture = TestBed.createComponent(ShoppingDayDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
