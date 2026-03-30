import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { CartServices } from '../cart-service/cart-services';
import { LoginService } from '../authService/login-service';
import { AddressService } from './address-service';
import { ApiService } from '../../shared/service/APIService';
import { startShipping } from '../../shared/service/graphQLService/queries';

@Injectable({
  providedIn: 'root',
})
export class ShippingService {
  
  private http = inject(HttpClient)
  private cartService = inject(CartServices)
  private authService = inject(LoginService)
  private addressService = inject(AddressService)
  private apiService = inject(ApiService)
  #apiUrl = environment.apiUrl


  getDeliveryCost(addressId: String){
    return this.http.post<String>(this.#apiUrl, {"address": addressId, "productId": this.cartService.getProductIDListFromCart})
  }

  AddShipping(){
    const shippingRequest: ShippingRequestDTO = {
      customerId: this.authService.customerId(),
      addressId: this.addressService.currentSelectedAddress(),
      orderId: this.cartService.orderResponse()?.OrderId ?? '',
      carrier: Carriers.Bluedart.toString()
    }

  //  return this.http.post(this.#apiUrl,shippingRequest)
  this.apiService.request<String>({
            mode: 'GRAPHQL', // Flip this to 'REST' or 'GRAPHQL' to instantly switch transports
            rest: { url: '/api/startShipping', method: 'POST', body: shippingRequest },
            // Note: Make sure to import your actual addToCart mutation document in this file!
            graphql: { query: startShipping , variables: { request: shippingRequest }, extractKey: 'startShipping', isMutation: true }
          }).subscribe({
            next: (data) => {
                console.log(data)
            }
          })
  }

  
}
