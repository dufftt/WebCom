import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressCheckoutComponent } from './address-checkout-component';

describe('AddressCheckoutComponent', () => {
  let component: AddressCheckoutComponent;
  let fixture: ComponentFixture<AddressCheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddressCheckoutComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddressCheckoutComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
