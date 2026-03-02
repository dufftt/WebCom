import { Component } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { SearchBarComponent } from '../search-bar-component/search-bar-component';
import { Router } from '@angular/router';


@Component({
  selector: 'app-nav-bar',
  imports: [MatToolbarModule, MatButtonModule, MatIconModule,SearchBarComponent],
  templateUrl: './nav-bar.html',
  styleUrl: './nav-bar.scss',
})
export class NavBar {


  constructor(private router: Router) {}
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

handleSearch: any;

}
