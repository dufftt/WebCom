import { gql } from 'apollo-angular';

export interface productItem{
    productId: number,
    productName: String,
    productDescription: String,
    productPrice: number
    productImageUrl: String[]
}

export const ProductList = gql`query GetProductList {
  getProductsList {
    price
    productDescription
    productId
    productName
  }
}
`