package com.duft.order_service.domain.services;

import java.util.ArrayList;
import java.util.List;
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


    public List<Order> getOrderList(Integer customer_id) {
       List<Order> orderList = orderRepositoryPort.getOrderListByCustomerId(customer_id);
        return orderList;
    }


    public List<OrderItems> getOrderItemsList(Integer customerId) {
        List<Order> orderList = this.getOrderList(customerId);
        List<OrderItems> orderItemsList = new ArrayList<>();
        for(Order order : orderList){
            List<OrderItems> orderItems = this.getOrderItemsListByOrderID(order.getOrderId());
            orderItemsList.addAll(orderItems);
        }
        return orderItemsList;
        }
    //     List<OrderItems> orderItemsList = orderItemRepositoryPort.getOrderItemsListByCustomerId(customerId);
    //     return orderItemsList;
    // }


    public Order getOrderDetails(Integer orderId) {
        Order order = orderRepositoryPort.getOrder(orderId);
        return order;
    }


    public List<OrderItems> getOrderItemsListByOrderID(Integer orderId) {
        List<OrderItems> orderItemsList = orderItemRepositoryPort.getOrderItemsListByOrderId(orderId);
        return orderItemsList;
    }


    public void updateOrderStatus(Integer orderId, Boolean status) {
       orderRepositoryPort.updateOrderStatus(orderId, status);
    }

    //TODO: /buy /getOrderStatus /updateOrderStatus /getOrderList /getOrderDetails /getTotal
}
