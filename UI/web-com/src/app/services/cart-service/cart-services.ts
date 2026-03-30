import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from '../authService/login-service';
import { ApiService } from '../../shared/service/APIService';

@Injectable({
  providedIn: 'root',
})
export class CartServices {
 //there are two ways to inject dependencies 1) constructor injection and 2) inject() api 
 //inject() is better than constructor injection because child classes do not need to inherit parent class depedencies explicitly
  
private http = inject(HttpClient);
private authService = inject(LoginService);
private apiService = inject(ApiService);
#cartItemList = signal<cartItemDTO[]>([])
#apiUrl = ''
#orderResponse = signal<OrderResponseDTO | null>(null);
orderResponse = this.#orderResponse.asReadonly();
cartItemList = this.#cartItemList.asReadonly();

//as user click on add to cart on each products then it will push the same here and update cartItemList
 addToCart(productId: number){
  console.log("Cart Service Invoked with product ID: ",productId)
    this.#cartItemList.update(items =>{
      if(items.find(item => item.productId===productId)){
        return items.map(item =>
          item.productId===productId ? {...item, quantity : item.quantity+1}: item
        );
      }
      return [...items, {productId: productId, quantity: 1}]
    })
  
 }

 removeFromCart(productId: number){
  this.#cartItemList.update(items => items.filter(i => i.productId !== productId));
 }

 pushAddToCartToBackend(){
    const cartRequest: cartOrderDTO = {
      customerId: this.authService.customerId(),
      orderItems: this.#cartItemList()
    }

    this.apiService.request<OrderResponseDTO>({
      mode: 'GRAPHQL', // Flip this to 'REST' or 'GRAPHQL' to instantly switch transports
      rest: { url: '/api/addToCart', method: 'POST', body: cartRequest },
      // Note: Make sure to import your actual addToCart mutation document in this file!
      graphql: { query: {} as any /* YOUR_ADD_TO_CART_MUTATION */, variables: { request: cartRequest }, extractKey: 'addToCart', isMutation: true }
    }).subscribe({
      next: (data) => {
        this.#orderResponse.set(data);
      },
      error: (err) => console.error('error occurred', err)
    });

 }

 emptyCart(){
  this.#cartItemList.set([])
  this.#orderResponse.set(null)
 }

 getProductIDListFromCart(){
  return this.#cartItemList().map(cartItem => cartItem.productId)
 }

}
