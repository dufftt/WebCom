import { Component, inject, Input, signal } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';

@Component({
  selector: 'app-grid-component',
  imports: [MatButton,
    MatCardModule],
  templateUrl: './grid-component.html',
  styleUrl: './grid-component.scss',
})
export class GridComponent {
addToCart() {
throw new Error('Method not implemented.');
}
  router = inject(Router)
goToProduct(productId: number) {
  console.log("Card clicked: "+productId)
  this.router.navigate(['/productPage',productId])
}

  @Input() productList: productItem[] = []


}
