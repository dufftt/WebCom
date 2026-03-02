import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { CartServices } from '../cart-service/cart-services';
import { LoginService } from '../authService/login-service';
import { AddressService } from './address-service';

@Injectable({
  providedIn: 'root',
})
export class ShippingService {
  
  private http = inject(HttpClient)
  private cartService = inject(CartServices)
  private authService = inject(LoginService)
  private addressService = inject(AddressService)
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

   return this.http.post(this.#apiUrl,shippingRequest)
  }

  
}
