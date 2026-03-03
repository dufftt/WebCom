import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginService } from '../../services/authService/login-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-component',
  imports: [ReactiveFormsModule],
  templateUrl: './register-component.html',
  styleUrl: './register-component.scss',
})
export class RegisterComponent {

  authService = inject(LoginService)
  router = inject(Router)

  registerForm: FormGroup = new FormGroup({
    name: new FormControl<String>('',[Validators.required]),
    mobNumber: new FormControl<number>(0, [Validators.required]),
    email: new FormControl<String>('',[Validators.required]),
    password: new FormControl<String>('',[Validators.required])
  })


  DoRegister() {
    this.authService.register(this.registerForm).subscribe(isloggedIn =>{
      if(isloggedIn){
        this.router.navigate(['/'])
      }else{
        alert('Wrong credentials')
      }
    })
}
}
