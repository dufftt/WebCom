import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-address-checkout-component',
  imports: [ReactiveFormsModule],
  templateUrl: './address-checkout-component.html',
  styleUrl: './address-checkout-component.scss',
})
export class AddressCheckoutComponent {

  addressForm: FormGroup = new FormGroup({
    address: new FormControl<String>('',[Validators.required]),
    city: new FormControl<String>('',[Validators.required]),
    country: new FormControl<String>('',[Validators.required]),
    pincode: new FormControl<number>(100001,[Validators.required])

  })

  getFormData(): FormGroup{
    return this.addressForm;
  }
}
