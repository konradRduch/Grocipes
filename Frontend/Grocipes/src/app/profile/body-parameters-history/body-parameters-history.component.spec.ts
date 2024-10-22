import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BodyParametersHistoryComponent } from './body-parameters-history.component';

describe('BodyParametersHistoryComponent', () => {
  let component: BodyParametersHistoryComponent;
  let fixture: ComponentFixture<BodyParametersHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BodyParametersHistoryComponent]
    });
    fixture = TestBed.createComponent(BodyParametersHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
