import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipesItemDetailsComponent } from './recipes-item-details.component';

describe('RecipesItemDetailsComponent', () => {
  let component: RecipesItemDetailsComponent;
  let fixture: ComponentFixture<RecipesItemDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RecipesItemDetailsComponent]
    });
    fixture = TestBed.createComponent(RecipesItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
