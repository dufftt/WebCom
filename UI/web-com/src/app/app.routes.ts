import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { LoginComponent } from './component/login-component/login-component';
import { CheckOutPage } from './page/check-out-page/check-out-page';
import { CartPage } from './page/cart-page/cart-page';
import { RegisterComponent } from './component/register-component/register-component';
import { OrderSummaryComponent } from './component/order-summary-component/order-summary-component';
import { ProductListComponent } from './component/product-list-component/product-list-component';
import { CartItemComponent } from './component/cart-item-component/cart-item-component';
import { CourierComponent } from './component/courier-component/courier-component';
import { AddressCheckoutComponent } from './component/address-checkout-component/address-checkout-component';
import { ProductPageComponent } from './component/product-page-component/product-page-component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomePage },
  { path: 'cart', component: CartPage },
  { path: 'checkout', component: CheckOutPage },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'orderSummary', component: OrderSummaryComponent },
  { path: 'productList', component: ProductListComponent },
  {path: 'productPage/:id', component: ProductPageComponent},
  { path: 'cartItem', component: CartItemComponent },
  { path: 'courier', component: CourierComponent },
  { path: 'address', component: AddressCheckoutComponent },
  { path: '**', redirectTo: 'home' } // Wildcard for 404s
];
