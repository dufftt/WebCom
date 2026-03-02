import { Component, } from '@angular/core';
import { OrderSummaryComponent } from '../../component/order-summary-component/order-summary-component';
import { AddressCheckoutComponent } from '../../component/address-checkout-component/address-checkout-component';
import { CourierComponent } from '../../component/courier-component/courier-component';


@Component({
  selector: 'app-check-out-page',
  imports: [OrderSummaryComponent, AddressCheckoutComponent,CourierComponent],
  templateUrl: './check-out-page.html',
  styleUrl: './check-out-page.scss',
})
export class CheckOutPage {


isAddingNewAddress = false;
selectedAddressId: string | null = null;

savedAddresses = [
  { id: '1', name: 'John Doe', street: '123 Main St', city: 'New York' },
  { id: '2', name: 'John Doe', street: '456 Business Blvd', city: 'Chicago' }
];
  currentStep: String = "address";

  gotoShipping() {
this.currentStep = 'shipping';
}

toggleNewAddressForm() {
  this.currentStep='addAddress';
}
chooseAddress() {
this.currentStep='address';
}
saveAndShipping() {
this.currentStep = 'shipping';
}

itemList =
[
  {
    id: 1,
    name: "Men's winter jacket",
  size: 'L',
  quantity: '12',
  price: '99'
  },
  {
    id: 2,
    name: "Women's winter jacket",
  size: 'M',
  quantity: '11',
  price: '49'
  },
  {
    id: 3,
    name: "Any's winter jacket",
  size: 'S',
  quantity: '100',
  price: '29'
  },
]
}
