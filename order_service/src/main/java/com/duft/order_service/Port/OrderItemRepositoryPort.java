package com.duft.order_service.Port;

import java.util.List;

import com.duft.order_service.domain.entities.OrderItems;

public interface OrderItemRepositoryPort {

    OrderItems addOrderItems(OrderItems orderItems);

    //List<OrderItems> getOrderItemsListByCustomerId(Integer customer_id);

    List<OrderItems> getOrderItemsListByOrderId(Integer orderId);

}
