package com.duft.order_service.Adapters.out.Order;

import org.springframework.stereotype.Repository;

import com.duft.order_service.Port.OrderRepositoryPort;
import com.duft.order_service.domain.entities.Order;

@Repository
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    @Override
    public Order addOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOrder'");
    }

}
