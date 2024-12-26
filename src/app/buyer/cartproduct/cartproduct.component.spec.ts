import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartproductComponent } from './cartproduct.component';

describe('CartproductComponent', () => {
  let component: CartproductComponent;
  let fixture: ComponentFixture<CartproductComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CartproductComponent]
    });
    fixture = TestBed.createComponent(CartproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
