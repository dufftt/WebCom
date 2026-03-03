import { Component, inject } from '@angular/core';
import { GridComponent } from '../grid-component/grid-component';
import { NavBar } from '../nav-bar/nav-bar';
import { FooterComponent } from "../footer-component/footer-component";
import { Router } from '@angular/router';
import { ProductServices } from '../../services/productService/product-services';

@Component({
  selector: 'app-product-list-component',
  imports: [GridComponent, FooterComponent],
  templateUrl: './product-list-component.html',
  styleUrl: './product-list-component.scss',
})
export class ProductListComponent {
router = inject(Router)
productService = inject(ProductServices)
productList = this.productService.productList;


ngOnInit(): void{
  this.productService.getProducts();
}


products = [
    { id: 1, name: 'iPhone', price: 949.98, image: '/iphone.png' },
    { id: 2, name: 'HP Laptop', price: 899.95, image: '/hp-laptop.png' },
    { id: 3, name: 'Powerbeats', price: 199.99, image: '/powerbeats-headphones.png' },
  ];
}
