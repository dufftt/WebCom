package com.duft.order_service.Adapters.out.OrderItem;

import org.springframework.stereotype.Repository;

import com.duft.order_service.Port.OrderItemRepositoryPort;
import com.duft.order_service.domain.entities.OrderItems;

@Repository
public class OrderItemRepositoryAdapter implements OrderItemRepositoryPort {

    @Override
    public OrderItems addOrderItems(OrderItems orderItems) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOrderItems'");
    }

}
