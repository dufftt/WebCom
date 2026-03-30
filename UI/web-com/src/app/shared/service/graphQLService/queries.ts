import { gql } from "apollo-angular";


// export const ProductList = gql`query GetProductList {
//   getProductsList {
//     price
//     productDescription
//     productId
//     productName
//   }
// }
// `

export const getAddress = gql`
query Query($getAddressId: Int!) {
  getAddress(id: $getAddressId) {
    address
    addressId
    city
    country
    customerId
    pinCode
    state
  }
}`

export const getAllAddressessByCustomerId = gql`
query Query($getAllAddressessByCustomerIdId: Int!) {
  getAllAddressessByCustomerId(id: $getAllAddressessByCustomerIdId) {
    address
    addressId
    city
    country
    customerId
    pinCode
    state
  }
}`


export const getAllShipments = gql`query Query {
  getAllShipments {
    address_id
    carrier
    customer_id
    deliveryCost
    orderId
    status
    trackId
  }
}
`

export const getCustomer = gql`query Query($getCustomerId: Int!) {
  getCustomer(id: $getCustomerId) {
    customerId
    email
    mobNumber
    name
  }
}
`

export const getInventoryAvailablewithProductId = gql`query Query($getInventoryAvailablewithProductIdId: Int!) {
  getInventoryAvailablewithProductId(id: $getInventoryAvailablewithProductIdId)
}
`

export const getInventoryByProductId = gql`query Query($getInventoryByProductIdId: Int!) {
  getInventoryByProductId(id: $getInventoryByProductIdId) {
    inventoryId
    location
    lockedQuantities
    productId
    quantity
  }
}
`

export const getInventoryList = gql`query Query {
  getInventoryList {
    inventoryId
    location
    lockedQuantities
    productId
    quantity
  }
}
`

export const getLocationByProductId = gql`query Query($getLocationByProductIdId: Int!) {
  getLocationByProductId(id: $getLocationByProductIdId)
}
`

export const getOrderDetails = gql`query Query($orderId: Int) {
  getOrderDetails(orderId: $orderId) {
    OrderId
    created_date
    customerId
    orderItems {
      orderId
      orderItemId
      price
      productId
      quantity
    }
    status
    total
  }
}
`

export const getOrderList = gql`query Query($customerId: Int) {
  getOrderList(customer_id: $customerId) {
    OrderId
    created_date
    customerId
    orderItems {
      orderId
    }
    status
    total
  }
}
`

export const getOrderStatus = gql`query Query($orderId: Int) {
  getOrderStatus(orderId: $orderId) {
    OrderId
    created_date
    customerId
    orderItems {
      orderId
      orderItemId
      price
      productId
      quantity
    }
    status
    total
  }
}
`

export const getProductPriceByProductID = gql`query Query($getProductPriceByProductIdId: Int!) {
  getProductPriceByProductID(id: $getProductPriceByProductIdId)
}
`

export const getProductsList = gql`query Query {
  getProductsList {
    price
    productDescription
    productId
    productName
  }
}
`

export const trackShipment = gql`query Query($trackId: String) {
  trackShipment(trackId: $trackId) {
    address_id
    carrier
    customer_id
    deliveryCost
    orderId
    status
    trackId
  }
}
`

export const getProductById = gql`query {
  getProductById(id: 1) {
    productId
    productName
    price
  }
}
`

export const DeleteAddress = gql`mutation Query($deleteAddressId: Int!) {
  DeleteAddress(id: $deleteAddressId)
}`

export const DeleteInventory = gql`mutation Query($deleteInventoryId: Int) {
  DeleteInventory(id: $deleteInventoryId)
}`


export const DeleteProduct = gql`mutation Query($deleteProductId: Int) {
  DeleteProduct(id: $deleteProductId)
}`

export const UpdateAddress = gql`mutation UpdateAddress($address: AddressInput! ) {
  UpdateAddress(address: $address)
}`

export interface AddressInput {
  address?: string;      // Optional (no ! in your screenshot)
  addressId?: number;    // Optional
  city?: string;
  country?: string;
  customerId: number;    // Required (has ! in your screenshot)
  pinCode?: string;
  state?: string;
}


export const UpdateInventory = gql`mutation Query($inventory: inventoryInput) {
  UpdateInventory(inventory: $inventory)
}`

export interface inventoryInput {
  inventoryId?: number;      // Optional (no ! in your screenshot)
  location?: String;    // Optional
  lockedQuantities?: number;
  productId?: number;
  quantity: number;    // Required (has ! in your screenshot)
}


export const UpdateProduct = gql`mutation Query($product: ProductInput) {
  UpdateProduct(product: $product)
}`

export interface ProductInput {
  price?: number;      // Optional (no ! in your screenshot)
  productDescription?: String;    // Optional
  productId?: number;
  productName?: String;
}

export const addAddress = gql`mutation Query($address: AddressInput!) {
  addAddress(address: $address)
}`


export const addCustomer = gql`mutation Query($customer: CustomerInput!) {
  addCustomer(customer: $customer)
}`

export interface CustomerInput {
  customerId?: number;      // Optional (no ! in your screenshot)
  email?: String;    // Optional
  mobNumber?: String;
  name?: String;
}

export const addInventory = gql`mutation Query($inventory: inventoryInput) {
  addInventory(inventory: $inventory)
}`

export const addProduct = gql`mutation Query($product: ProductInput) {
  addProduct(product: $product)
}`

export const addToCart = gql`mutation Query($orderRequest: OrderRequestInput) {
  addToCart(orderRequest: $orderRequest) {
    OrderId
    created_date
    customerId
    orderItems {
      orderId
      orderItemId
      price
      productId
      quantity
    }
    status
    total
  }
}
`

export interface OrderItemInput {
  product_id: number; // Matches your JSON structure
  quantity: number;
}

export interface OrderRequestInput {
  customerId: number;
  orderItems: OrderItemInput[]; // This is the 'list' part
}

export const deleteCustomer = gql`mutation DeleteCustomer($deleteCustomerId: Int!) {
  deleteCustomer(id: $deleteCustomerId)
}`


export const holdInventory = gql`mutation Query($request: LockOrReleaseInventoryInput) {
  holdInventory(request: $request)
}`

export interface LockOrReleaseInventoryInput {
  inventoryId: number; // Matches your JSON structure
  quantity: number;
}

export const releaseInventory = gql`mutation Query($request: LockOrReleaseInventoryInput) {
  releaseInventory(request: $request)
}`


export const startShipping = gql`mutation Query($shipmentRequestDto: ShipmentRequestDTO) {
  startShipping(shipmentRequestDTO: $shipmentRequestDto) {
    address_id
    carrier
    customer_id
    deliveryCost
    orderId
    status
    trackId
  }
}`

export interface ShipmentRequestDTO {
  addressId: number; // Matches your JSON structure
  carrier: number;
  customerId: number;
  orderId: number;
}

export const updateOrderStatus = gql`mutation Query($orderId: Int, $status: Boolean) {
  updateOrderStatus(orderId: $orderId, status: $status)
}`

export const updateShipmentStatus = gql`mutation Query($updateStatusDto: [UpdateStatusDTO]) {
  updateShipmentStatus(updateStatusDTO: $updateStatusDto)
}`

export interface UpdateStatusDTO {
  shipmentId: number; // Matches your JSON structure
  status: Boolean;

}
