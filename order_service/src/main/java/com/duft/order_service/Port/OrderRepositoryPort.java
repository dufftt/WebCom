package com.duft.order_service.Port;

import com.duft.order_service.domain.entities.Order;

public interface OrderRepositoryPort {

    Order addOrder(Order order);

}
