import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopListItemDetailsComponent } from './shop-list-item-details.component';

describe('ShopListItemDetailsComponent', () => {
  let component: ShopListItemDetailsComponent;
  let fixture: ComponentFixture<ShopListItemDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShopListItemDetailsComponent]
    });
    fixture = TestBed.createComponent(ShopListItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
