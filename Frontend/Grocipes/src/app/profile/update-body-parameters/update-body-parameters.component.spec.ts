import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateBodyParametersComponent } from './update-body-parameters.component';

describe('UpdateBodyParametersComponent', () => {
  let component: UpdateBodyParametersComponent;
  let fixture: ComponentFixture<UpdateBodyParametersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateBodyParametersComponent]
    });
    fixture = TestBed.createComponent(UpdateBodyParametersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
