import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingListAddComponent } from './shopping-list-add.component';

describe('ShoppingListAddComponent', () => {
  let component: ShoppingListAddComponent;
  let fixture: ComponentFixture<ShoppingListAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShoppingListAddComponent]
    });
    fixture = TestBed.createComponent(ShoppingListAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
