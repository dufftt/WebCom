import { HttpClient } from '@angular/common/http';
import { computed, Injectable, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { ProductList, productItem } from './productItemDTO';
import { Apollo } from 'apollo-angular';
import { ApiService } from '../../shared/service/APIService';
import { getProductById, getProductsList } from '../../shared/service/graphQLService/queries';

@Injectable({
  providedIn: 'root',
})
export class ProductServices {
  
  #apiUrl = environment.apiUrl
  #mode = 'GRAPHQL';
  #productList = signal<productItem[]>([]);
  #currentPageNumber = signal<number>(1);
  #lastPageBool = signal<boolean>(false);

  productList = this.#productList.asReadonly()
  count = computed(() => this.#productList().length)

  constructor(private http: HttpClient, private apollo: Apollo, private apiService: ApiService) {}

  //TODO: In backend make sure this api is unprotected
  getProducts() {
//     this.http.get<productItem[]>(this.#apiUrl+'/api/getProducts').subscribe({
//       next: (data) => {
//         this.#productList.set(data)
//       },
//       error: () => console.error('error occurred')
//     })

//     //graphql logic
//     this.apollo.watchQuery<any>({ query: ProductList }).valueChanges.subscribe({
//     next: (result) => {
//     // ✅ Safe: Only access if data exists
//     if (result.data) {
//       const customer = result.data.getProductsList;
//       console.log(customer);
//     }
//   },
//   error: (err) => console.error('GraphQL Error:', err)
// });
 this.apiService.request<productItem[]>({
      mode: 'GRAPHQL', // Flip this to 'REST' or 'GRAPHQL' to instantly switch transports
      rest: { url: '/api/getProducts', method: 'GET' },
      graphql: { query: getProductsList, extractKey: 'getProductsList' }
    }).subscribe({
      next: (data) => {
        console.log(data);
        this.#productList.set(data);
      },
      error: (err) => console.error('error occurred', err)
    });
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
     this.apiService.request<productItem>({
      mode: 'GRAPHQL', // Flip this to 'REST' or 'GRAPHQL' to instantly switch transports
      rest: { url: '/api/getProducts', method: 'GET' },
      graphql: { query: getProductById, extractKey: 'getProductById' }
    }).subscribe({
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
