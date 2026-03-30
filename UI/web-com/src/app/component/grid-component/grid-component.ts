import { Component, inject, Input, signal } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';
import { CartServices } from '../../services/cart-service/cart-services';
import { LoginService } from '../../services/authService/login-service';
import { productItem } from '../../services/productService/productItemDTO';

@Component({
  selector: 'app-grid-component',
  imports: [MatButton,
    MatCardModule],
  templateUrl: './grid-component.html',
  styleUrl: './grid-component.scss',
})
export class GridComponent {

  router = inject(Router)
  authService = inject(LoginService)
  cartService = inject(CartServices)
  isLoggedIn = this.authService.isLoggedin
  @Input() productList: productItem[] = []

  goToProduct(productId: number) {
  console.log("Card clicked: "+productId)
  this.router.navigate(['/productPage',productId])
}

  addToCart(product: productItem, event: Event) {
  event.stopPropagation();
  if(!this.authService.isLoggedin()){
    this.router.navigate(['/login'])
  }
  this.cartService.addToCart(product.productId)
  
}

}
