import { Component, computed, inject } from '@angular/core';
import { OrderSummaryComponent } from "../../component/order-summary-component/order-summary-component";
import { CartItemComponent } from '../../component/cart-item-component/cart-item-component';
import { Router, RouterLink } from "@angular/router";
import { CartServices } from '../../services/cart-service/cart-services';
import { ProductServices } from '../../services/productService/product-services';

@Component({
  selector: 'app-cart-page',
  imports: [OrderSummaryComponent, CartItemComponent, RouterLink],
  templateUrl: './cart-page.html',
  styleUrl: './cart-page.scss',
})
export class CartPage {

router = inject(Router)
cartService = inject(CartServices)
productService = inject(ProductServices)
 

gotToCheckout() {
// this.router.navigate(['/checkout']);
// console.log(this.cartItemsList);
console.log(this.productItemLists);

}

// cartItemsList = this.cartService.cartItemList();
productItemLists = computed(() => this.cartService.cartItemList().map(cartItem => {
  const product = computed(() => this.productService.productList().find(p => p.productId===cartItem.productId));
  return {
          id: cartItem.productId,
          name: product()?.productName,
          imageUrl: product()?.productImageUrl,
          quantity: cartItem.quantity,
          price: product()?.productPrice

  }
}))
// productItemLists = this.cartItemsList.map(cartItem => {
//   const product = computed(() => this.productService.productList().find(p => p.productId===cartItem.productId));
//   return {
//           id: cartItem.productId,
//           name: product()?.productName,
//           quantity: cartItem.quantity,
//           price: product()?.productPrice

//   }
// })



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
