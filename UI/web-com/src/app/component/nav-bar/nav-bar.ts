import { Component, inject } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { SearchBarComponent } from '../search-bar-component/search-bar-component';
import { Router } from '@angular/router';
import { LoginService } from '../../services/authService/login-service';
import {MatMenuModule} from '@angular/material/menu';


@Component({
  selector: 'app-nav-bar',
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, SearchBarComponent, MatMenuModule],
  templateUrl: './nav-bar.html',
  styleUrl: './nav-bar.scss',
})
export class NavBar {


  router = inject(Router)
  authService = inject(LoginService)
  isLoggedIn = this.authService.isLoggedin

goToHome() {
  this.router.navigate(['/home']);
}

goToCart() {
this.router.navigate(['/cart']);
}
goToRegister() {
this.router.navigate(['/register']);
}
goToLogin() {
this.router.navigate(['/login']);
}
logOut() {
this.authService.logout()
this.router.navigate(['/home'])}

handleSearch: any;

}
