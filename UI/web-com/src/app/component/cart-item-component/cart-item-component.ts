import { Component, Input } from '@angular/core';

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
@Input() item: any; 
onRemove(){
  //TODO
}
}
