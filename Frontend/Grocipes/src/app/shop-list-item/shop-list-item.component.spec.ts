import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopListItemComponent } from './shop-list-item.component';

describe('ShopListItemComponent', () => {
  let component: ShopListItemComponent;
  let fixture: ComponentFixture<ShopListItemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShopListItemComponent]
    });
    fixture = TestBed.createComponent(ShopListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
