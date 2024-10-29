import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NutritionalGoalComponent } from './nutritional-goal.component';

describe('NutritionalGoalComponent', () => {
  let component: NutritionalGoalComponent;
  let fixture: ComponentFixture<NutritionalGoalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NutritionalGoalComponent]
    });
    fixture = TestBed.createComponent(NutritionalGoalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
