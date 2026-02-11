package com.duft.order_service.Port;

import java.util.List;

import com.duft.order_service.domain.entities.Order;

public interface OrderRepositoryPort {

    Order addOrder(Order order);

    List<Order> getOrderListByCustomerId(Integer customerId);

    Order getOrder(Integer orderId);

    void updateOrderStatus(Integer orderId, Boolean status);

}
