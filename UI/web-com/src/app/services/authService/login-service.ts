import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { catchError, map, of } from 'rxjs';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  
  constructor(private http: HttpClient){}

  #url =environment.apiUrl
  #customerId = signal('')
  customerId = this.#customerId.asReadonly()
  login(formData: any){
    debugger
    const loginRequest: LoginDTO = {
      email: formData.get('email')?.value,
      password: formData.get('password')?.value
    }
   console.log({"email":formData.get('email').value,"password":formData.get('password').value})
   console.log(loginRequest)
    return this.http.post<{token: String}>(this.#url+'/login',loginRequest)
    .pipe(
      map((response) => {
        if(response.token){
          localStorage.setItem('token',response.token.toString())

          return true
        }
        return false
      }),
      catchError((error) => {
      console.log('login failed' + error);
      return of(false);
    })
    )
  }

  //it will make two actions: 1) add user to customer and also register their creds in keycloak
  register(formData: any){
    const registerRequest: RegisterDTO = {
      name: formData.get('name')?.value,
      mobNumber: formData.get('mobNumber')?.value,
      email: formData.get('email')?.value,
      Password: formData.get('password')?.value
    }
    console.log(registerRequest)
    this.http.post<{token: String}>(this.#url+'/registerUser',registerRequest).pipe(
      map((response) => {
        if(response){
          localStorage.setItem('token',response.token.toString())
          return true;
        }
        return false;
      }),catchError((error) => {
        console.error("Register Failed"+error)
        return of(false);
      })
    )
  }
    
  logout(){
    localStorage.removeItem('token')
  }

  
}
