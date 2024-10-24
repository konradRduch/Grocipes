import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BodyParametersChartComponent } from './body-parameters-chart.component';

describe('BodyParametersChartComponent', () => {
  let component: BodyParametersChartComponent;
  let fixture: ComponentFixture<BodyParametersChartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BodyParametersChartComponent]
    });
    fixture = TestBed.createComponent(BodyParametersChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
