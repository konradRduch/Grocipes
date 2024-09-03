import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroceriesItemComponent } from './groceries-item.component';

describe('GroceriesItemComponent', () => {
  let component: GroceriesItemComponent;
  let fixture: ComponentFixture<GroceriesItemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GroceriesItemComponent]
    });
    fixture = TestBed.createComponent(GroceriesItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
