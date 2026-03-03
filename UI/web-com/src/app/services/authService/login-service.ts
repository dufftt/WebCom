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
  #customerId = signal(localStorage.getItem('customerId') ?? '')
  customerId = this.#customerId.asReadonly();
  #customerName = signal(localStorage.getItem('name') ?? '')
  customerName = this.#customerName.asReadonly();
  #isLoggedIn = signal(!!localStorage.getItem('customerId') && !!localStorage.getItem('token'))
  isLoggedin = this.#isLoggedIn.asReadonly();


  login(formData: any){
    debugger
    const loginRequest: LoginDTO = {
      email: formData.get('email')?.value,
      password: formData.get('password')?.value
    }
  
    return this.http.post<AuthResponseDTO>(this.#url+'/login',loginRequest)
    .pipe(
      map((response) => {
        if(response.token){
          
          localStorage.setItem('token',response.token.toString())
          localStorage.setItem('name',response.name.toString())
          localStorage.setItem('customerId',response.customerId.toString())
          this.#customerId.set(response.customerId.toString())
          this.#isLoggedIn.set(true)

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
    console.log(formData)
    const registerRequest: RegisterDTO = {
      name: formData.get('name')?.value,
      mobNumber: formData.get('mobNumber')?.value,
      email: formData.get('email')?.value,
      Password: formData.get('password')?.value
    }
    console.log(registerRequest)
    return this.http.post<AuthResponseDTO>(this.#url+'/register',registerRequest).pipe(
      map((response) => {
        console.log(response)
        if(response){
          localStorage.setItem('token',response.token.toString())
          localStorage.setItem('name',response.name.toString())
          localStorage.setItem('customerId',response.customerId.toString())
          this.#customerId.set(response.customerId.toString())
          this.#isLoggedIn.set(true)
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
    localStorage.removeItem('customerId')
    localStorage.removeItem('name')
    this.#customerId.set('')
    this.#isLoggedIn.set(false)
  }

  
}
