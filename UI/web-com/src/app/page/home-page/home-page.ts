import { Component, inject } from '@angular/core';
import { HeroComponent } from '../../component/hero-component/hero-component';
import { GridComponent } from '../../component/grid-component/grid-component';
import { FooterComponent } from '../../component/footer-component/footer-component';
import { Router } from '@angular/router';
import { ProductServices } from '../../services/productService/product-services';

@Component({
  selector: 'app-home-page',
  imports: [HeroComponent,GridComponent,FooterComponent],
  templateUrl: './home-page.html',
  styleUrl: './home-page.scss',
})
export class HomePage {

router = inject(Router)
productService = inject(ProductServices)
productHomeList = this.productService.productList;


ngOnInit(): void{
  this.productService.getHomeProducts();
}

goToProductList() {
  console.log("clicked product lis");
this.router.navigate(['/productList']);
}


}
