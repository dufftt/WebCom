import { Component, inject, OnInit, ViewChild, } from '@angular/core';
import { OrderSummaryComponent } from '../../component/order-summary-component/order-summary-component';
import { AddressCheckoutComponent } from '../../component/address-checkout-component/address-checkout-component';
import { CourierComponent } from '../../component/courier-component/courier-component';
import { LoginService } from '../../services/authService/login-service';
import { AddressService } from '../../services/checkout/address-service';


@Component({
  selector: 'app-check-out-page',
  imports: [OrderSummaryComponent, AddressCheckoutComponent,CourierComponent],
  templateUrl: './check-out-page.html',
  styleUrl: './check-out-page.scss',
})
export class CheckOutPage implements OnInit{
  

authService = inject(LoginService)
addressService = inject(AddressService)
isAddingNewAddress = false;
selectedAddressId: string | null = null;
customerName = this.authService.customerName
addressList = this.addressService.addressList
@ViewChild(AddressCheckoutComponent) addressChild!: AddressCheckoutComponent;

ngOnInit(): void {

    this.addressService.findAllAddressByCustomerID()
  }

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
const formData = this.addressChild.addressForm.value;
    console.log('Checkout Data:', formData);
this.currentStep = 'shipping';
}


}
