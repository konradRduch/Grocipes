import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NutritionalGoalEditComponent } from './nutritional-goal-edit.component';

describe('NutritionalGoalEditComponent', () => {
  let component: NutritionalGoalEditComponent;
  let fixture: ComponentFixture<NutritionalGoalEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NutritionalGoalEditComponent]
    });
    fixture = TestBed.createComponent(NutritionalGoalEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
