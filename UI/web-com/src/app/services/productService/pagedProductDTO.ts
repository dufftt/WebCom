import { productItem } from "./productItemDTO";

export interface pagedProductDTO{
    lastPage: boolean,
    productItemList: productItem[]
}

