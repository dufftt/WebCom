interface OrderResponseDTO{
    OrderId: String;
    customerId: number;
    status: boolean;
    total: number;
    created_date: String;
    orderItems: OrderItemResponseDTO[];
}