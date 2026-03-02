import { Component } from '@angular/core';
import { LoginService } from '../../services/authService/login-service';
import { inject } from '@angular/core/primitives/di';
import { routes } from '../../app.routes';
import { Router } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login-component',
  imports: [ReactiveFormsModule],
  templateUrl: './login-component.html',
  styleUrl: './login-component.scss',
})
export class LoginComponent {

  // loginService = inject(LoginService)
  
  constructor(private router: Router, private loginService: LoginService){}

  formData: FormGroup = new FormGroup({
    email: new FormControl<String>('',[Validators.required]),
    password: new FormControl<String>('',[Validators.required])
  })

  login(){
    this.loginService.login(this.formData).subscribe(isloggedIn =>{
      if(isloggedIn){
        this.router.navigate(['/'])
      }else{
        alert('Wrong credentials')
      }
    })
  
    
  }
}

