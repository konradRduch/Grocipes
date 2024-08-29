import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingScheduleComponent } from './shopping-schedule.component';

describe('ShoppingScheduleComponent', () => {
  let component: ShoppingScheduleComponent;
  let fixture: ComponentFixture<ShoppingScheduleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShoppingScheduleComponent]
    });
    fixture = TestBed.createComponent(ShoppingScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
