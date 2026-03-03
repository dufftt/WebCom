import { Component, inject, Input } from '@angular/core';
import { CartServices } from '../../services/cart-service/cart-services';

@Component({
  selector: 'app-cart-item-component',
  imports: [],
  templateUrl: './cart-item-component.html',
  styleUrl: './cart-item-component.scss',
})
export class CartItemComponent {
// item = {
//   name: "Men's winter jacket",
//   size: 'L',
//   quantity: '1',
//   price: '99'
// };

cartService = inject(CartServices)
@Input() item: any; 


onRemove(productId: number){
  //TODO
  console.log(this.item)
  this.cartService.removeFromCart(productId)
  
}
}
