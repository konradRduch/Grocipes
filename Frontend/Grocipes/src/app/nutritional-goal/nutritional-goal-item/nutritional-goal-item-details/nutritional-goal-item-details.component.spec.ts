import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NutritionalGoalItemDetailsComponent } from './nutritional-goal-item-details.component';

describe('NutritionalGoalItemDetailsComponent', () => {
  let component: NutritionalGoalItemDetailsComponent;
  let fixture: ComponentFixture<NutritionalGoalItemDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NutritionalGoalItemDetailsComponent]
    });
    fixture = TestBed.createComponent(NutritionalGoalItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
