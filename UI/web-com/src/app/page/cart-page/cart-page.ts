import { Component } from '@angular/core';
import { OrderSummaryComponent } from "../../component/order-summary-component/order-summary-component";
import { CartItemComponent } from '../../component/cart-item-component/cart-item-component';
import { Router, RouterLink } from "@angular/router";
import { CartServices } from '../../services/cart-service/cart-services';

@Component({
  selector: 'app-cart-page',
  imports: [OrderSummaryComponent, CartItemComponent, RouterLink],
  templateUrl: './cart-page.html',
  styleUrl: './cart-page.scss',
})
export class CartPage {

  constructor(private router: Router, private cartService: CartServices){}

 

gotToCheckout() {
this.router.navigate(['/checkout']);

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
