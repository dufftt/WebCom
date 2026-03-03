import { Component, inject, Input, signal } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';
import { CartServices } from '../../services/cart-service/cart-services';

@Component({
  selector: 'app-grid-component',
  imports: [MatButton,
    MatCardModule],
  templateUrl: './grid-component.html',
  styleUrl: './grid-component.scss',
})
export class GridComponent {

  router = inject(Router)
  cartService = inject(CartServices)

  @Input() productList: productItem[] = []

  goToProduct(productId: number) {
  console.log("Card clicked: "+productId)
  this.router.navigate(['/productPage',productId])
}

  addToCart(product: productItem, event: Event) {
  event.stopPropagation();
  this.cartService.addToCart(product.productId)
  
}

}
