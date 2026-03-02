import { Component, inject, Input, OnInit } from '@angular/core';
import { ProductServices } from '../../services/productService/product-services';

@Component({
  selector: 'app-product-page-component',
  imports: [],
  templateUrl: './product-page-component.html',
  styleUrl: './product-page-component.scss',
})
export class ProductPageComponent implements OnInit {

@Input() id!: string;
product: productItem | undefined;
productService = inject(ProductServices)
  ngOnInit(): void {
  this.product = this.productService.getProductByProductIdLocally(Number(this.id));
}





}
