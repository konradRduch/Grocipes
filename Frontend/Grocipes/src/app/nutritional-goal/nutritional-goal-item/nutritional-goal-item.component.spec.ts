import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NutritionalGoalItemComponent } from './nutritional-goal-item.component';

describe('NutritionalGoalItemComponent', () => {
  let component: NutritionalGoalItemComponent;
  let fixture: ComponentFixture<NutritionalGoalItemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NutritionalGoalItemComponent]
    });
    fixture = TestBed.createComponent(NutritionalGoalItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
