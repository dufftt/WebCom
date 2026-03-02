import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { LoginService } from '../authService/login-service';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class AddressService {
  http = inject(HttpClient)
  authService = inject(LoginService)
  #addressList = signal<AddressDTO[] | null>(null)
  #apiUrl = environment.apiUrl
  #currentSelectedAddress = signal<String>('')
  currentSelectedAddress = this.#currentSelectedAddress.asReadonly();


  findAllAddressByCustomerID(){
      this.http.get<AddressDTO[]>(this.#apiUrl+'/getAddressByCustomerId?customerId='+this.authService.customerId()).subscribe({
      next: (data) => {
        this.#addressList.set(data)
      },
      error: () => console.error('error occurred')
    })
  }
//TODO: when address component call this better i will do subscription there so I can use address id returned there
  AddAddress(formData: any){
    const address: AddressDTO = {
      addressId: 0,
      customerId: this.authService.customerId(),
      address: formData.get('Address')?.value,
      City: formData.get('City')?.value,
      state: formData.get('state')?.value,
      country: formData.get('country')?.value,
      pinCode: formData.get('pincode')?.value
    }
    this.http.post<String>(this.#apiUrl+'/addAddress',address).subscribe({
      next: response => {
          console.log(response)
      }
    })
  }

  
//TODO: when address component call this better i will do subscription there so I can use address id returned there
  UpdateAddress(formData: any){
    const address: AddressDTO = {
      addressId: formData.get('addressId')?.value,
      customerId: this.authService.customerId(),
      address: formData.get('Address')?.value,
      City: formData.get('City')?.value,
      state: formData.get('state')?.value,
      country: formData.get('country')?.value,
      pinCode: formData.get('pincode')?.value
    }
    this.http.post<String>(this.#apiUrl+'/UpdateAddress',address).subscribe({
      next: response => {
          console.log(response)
      }
    })
  }

  DeleteAddress(addressId: number){
    this.http.get<String>(this.#apiUrl+'/DeleteAddress'+addressId).subscribe({
      next: response => {
          if(response){
            this.findAllAddressByCustomerID()
          }
      }
    })
  }

  selectAddress(addressId: String){
    this.#currentSelectedAddress.set(addressId);
  }
}
