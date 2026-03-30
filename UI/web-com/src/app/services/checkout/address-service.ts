import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { LoginService } from '../authService/login-service';
import { environment } from '../../../environments/environment.development';
import { ApiService } from '../../shared/service/APIService';
import { addAddress, DeleteAddress, getAllAddressessByCustomerId, UpdateAddress } from '../../shared/service/graphQLService/queries';

@Injectable({
  providedIn: 'root',
})
export class AddressService {
  http = inject(HttpClient)
  apiService = inject(ApiService)
  authService = inject(LoginService)
  #addressList = signal<AddressDTO[] | null>(null)
  addressList = this.#addressList.asReadonly()
  #apiUrl = environment.apiUrl
  #currentSelectedAddress = signal<String>('')
  currentSelectedAddress = this.#currentSelectedAddress.asReadonly();


  findAllAddressByCustomerID(){
      // this.http.get<AddressDTO[]>(this.#apiUrl+'/getAddressByCustomerId?customerId='+this.authService.customerId()).subscribe({
      // next: (data) => {
      this.apiService.request<AddressDTO[]>({
            mode: 'GRAPHQL', // Flip this to 'REST' or 'GRAPHQL' to instantly switch transports
            rest: { url: '/api/getAllAddressessByCustomerId', method: 'GET' },
            graphql: { query: getAllAddressessByCustomerId, extractKey: 'getAllAddressessByCustomerId', isMutation: false }
          }).subscribe({
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
    // this.http.post<String>(this.#apiUrl+'/addAddress',address).subscribe({
    //  next: response => {
     this.apiService.request<String>({
      mode: 'GRAPHQL', // Flip this to 'REST' or 'GRAPHQL' to instantly switch transports
      rest: { url: '/api/addAddress', method: 'POST', body: address },
      // Note: Make sure to import your actual addToCart mutation document in this file!
      graphql: { query: addAddress , variables: { request: address }, extractKey: 'addAddress', isMutation: true }
    }).subscribe({
      next: (data) => {
          console.log(data)
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
    // this.http.post<String>(this.#apiUrl+'/UpdateAddress',address).subscribe({
    //   next: response => {
     this.apiService.request<String>({
      mode: 'GRAPHQL', // Flip this to 'REST' or 'GRAPHQL' to instantly switch transports
      rest: { url: '/api/UpdateAddress', method: 'POST', body: address },
      // Note: Make sure to import your actual addToCart mutation document in this file!
      graphql: { query: UpdateAddress , variables: { request: address }, extractKey: 'UpdateAddress', isMutation: true }
    }).subscribe({
      next: (data) => {
          console.log(data)
      }
    })
  }

  DeleteAddress(addressId: number){
    // this.http.get<String>(this.#apiUrl+'/DeleteAddress'+addressId).subscribe({
    this.apiService.request<String>({
      mode: 'GRAPHQL', // Flip this to 'REST' or 'GRAPHQL' to instantly switch transports
      rest: { url: '/api/DeleteAddress', method: 'POST', body: addressId },
      // Note: Make sure to import your actual addToCart mutation document in this file!
      graphql: { query: DeleteAddress , variables: { request: addressId }, extractKey: 'DeleteAddress', isMutation: true }
    }).subscribe({  
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
