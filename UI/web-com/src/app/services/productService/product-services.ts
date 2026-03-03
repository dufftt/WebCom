import { HttpClient } from '@angular/common/http';
import { computed, Injectable, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class ProductServices {
  
  #apiUrl = environment.apiUrl

  #productList = signal<productItem[]>([]);
  #currentPageNumber = signal<number>(1);
  #lastPageBool = signal<boolean>(false);

  productList = this.#productList.asReadonly()
  count = computed(() => this.#productList().length)

  constructor(private http: HttpClient) {}

  //TODO: In backend make sure this api is unprotected
  getProducts() {
    this.http.get<productItem[]>(this.#apiUrl+'/api/getProducts').subscribe({
      next: (data) => {
        this.#productList.set(data)
      },
      error: () => console.error('error occurred')
    })
    
  }
//TODO: In backend make sure this api is unprotected
  getHomeProducts(){
    this.getProducts();
    // this.http.get<productItem[]>(this.#apiUrl+'/getHomeProducts').subscribe({
    //   next: (data) => {
    //     this.#productList.set(data)
    //   },
    //   error: () => console.error('error occurred')
    // })
  }
//TODO: In backend make sure this api is unprotected
//Component using this will pass productId and then 
  getProductByProductId(productId: number){
     this.http.get<productItem>(this.#apiUrl+'/api/getProductById?productId='+productId).subscribe({
      next: (data) => {
        this.#productList.update(products => [...products, data]);
      },
      error: () => console.error('error occurred')
    

     })
  }
getProductByProductIdLocally(productId: number): productItem | undefined {
  if(this.#productList().find(product => product.productId === productId)){
    return this.#productList().find(product => product.productId === productId);
  }else{
    this.getProductByProductId(productId)
    return this.#productList().find(product => product.productId === productId);
  }
  
}
//TODO: built backend for offset pagination
//logic for backend that based on page number we will fetch 10 records on offset 
// and on lastpage we will return lastPage as true and offset will happen after sorting them
getProductListByPagination(pageNumber: number){
    this.http.get<pagedProductDTO>(this.#apiUrl+'/getPagedProducts?pagenumber='+pageNumber).subscribe({
      next: (data) => {
        this.#productList.set(data.productItemList)
        this.#lastPageBool.set(data.lastPage)
      },
      error: () => console.error('error occurred')
    })
}

}
