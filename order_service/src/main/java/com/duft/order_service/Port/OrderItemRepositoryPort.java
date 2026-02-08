package com.duft.order_service.Port;

import com.duft.order_service.domain.entities.OrderItems;

public interface OrderItemRepositoryPort {

    OrderItems addOrderItems(OrderItems orderItems);

}
