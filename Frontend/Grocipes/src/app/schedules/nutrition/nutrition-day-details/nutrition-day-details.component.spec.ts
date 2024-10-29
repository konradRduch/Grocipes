import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NutritionDayDetailsComponent } from './nutrition-day-details.component';

describe('NutritionDayDetailsComponent', () => {
  let component: NutritionDayDetailsComponent;
  let fixture: ComponentFixture<NutritionDayDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NutritionDayDetailsComponent]
    });
    fixture = TestBed.createComponent(NutritionDayDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
