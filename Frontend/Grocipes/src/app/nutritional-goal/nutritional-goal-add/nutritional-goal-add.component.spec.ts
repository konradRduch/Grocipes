import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NutritionalGoalAddComponent } from './nutritional-goal-add.component';

describe('NutritionalGoalAddComponent', () => {
  let component: NutritionalGoalAddComponent;
  let fixture: ComponentFixture<NutritionalGoalAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NutritionalGoalAddComponent]
    });
    fixture = TestBed.createComponent(NutritionalGoalAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
