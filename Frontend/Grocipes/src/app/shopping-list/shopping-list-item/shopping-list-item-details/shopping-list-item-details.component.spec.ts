import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingListItemDetailsComponent } from './shopping-list-item-details.component';

describe('ShoppingListItemDetailsComponent', () => {
  let component: ShoppingListItemDetailsComponent;
  let fixture: ComponentFixture<ShoppingListItemDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShoppingListItemDetailsComponent]
    });
    fixture = TestBed.createComponent(ShoppingListItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
