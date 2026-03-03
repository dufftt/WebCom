import { Component, inject, Input, OnInit, computed } from '@angular/core';
import { ProductServices } from '../../services/productService/product-services';
import { Router } from '@angular/router';
import { CartServices } from '../../services/cart-service/cart-services';

@Component({
  selector: 'app-product-page-component',
  imports: [],
  templateUrl: './product-page-component.html',
  styleUrl: './product-page-component.scss',
})
export class ProductPageComponent implements OnInit {


@Input() id!: string;
product = computed(() => this.productService.productList().find(p => p.productId === Number(this.id)));
productService = inject(ProductServices)
router = inject(Router)
cartService = inject(CartServices)
  ngOnInit(): void {
  this.productService.getProductByProductIdLocally(Number(this.id));
}

goToCart() {
this.router.navigate(['/cart'])
}
addToCart(product: any) {
this.cartService.addToCart(product.productId)
}



}
