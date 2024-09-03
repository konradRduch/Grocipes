import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroceriesItemDetailsComponent } from './groceries-item-details.component';

describe('GroceriesItemDetailsComponent', () => {
  let component: GroceriesItemDetailsComponent;
  let fixture: ComponentFixture<GroceriesItemDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GroceriesItemDetailsComponent]
    });
    fixture = TestBed.createComponent(GroceriesItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
