import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroceriesComponent } from './groceries.component';

describe('GroceriesComponent', () => {
  let component: GroceriesComponent;
  let fixture: ComponentFixture<GroceriesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GroceriesComponent]
    });
    fixture = TestBed.createComponent(GroceriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
