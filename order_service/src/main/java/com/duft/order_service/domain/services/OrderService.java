package com.duft.order_service.domain.services;

import java.util.Map;

import com.duft.order_service.Port.OrderItemRepositoryPort;
import com.duft.order_service.Port.OrderRepositoryPort;
import com.duft.order_service.domain.entities.Order;
import com.duft.order_service.domain.entities.OrderItems;

public class OrderService {

    private final OrderRepositoryPort orderRepositoryPort;
    private final OrderItemRepositoryPort orderItemRepositoryPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort, OrderItemRepositoryPort orderItemRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.orderItemRepositoryPort = orderItemRepositoryPort;
    }

    
    public Order addOrder(Order order){
        return orderRepositoryPort.addOrder(order);
    }


    public OrderItems addOrderItems(OrderItems orderItems){
        return orderItemRepositoryPort.addOrderItems(orderItems);
    }

    public Map<Integer,Integer> calculateTotal(Map<Integer,Integer> priceList){
                return null;
    }

    //TODO: /buy /getOrderStatus /updateOrderStatus /getOrderList /getOrderDetails /getTotal
}
