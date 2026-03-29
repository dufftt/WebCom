package com.duft.order_service.Adapters.out.persistence.InitializeData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.duft.order_service.Port.OrderItemRepositoryPort;
import com.duft.order_service.Port.OrderRepositoryPort;
import com.duft.order_service.domain.entities.Order;
import com.duft.order_service.domain.entities.OrderItems;
import com.duft.order_service.domain.services.OrderService;

import jakarta.annotation.PostConstruct;

@Configuration
public class OrderData {

    private OrderService orderService;

    public OrderData(OrderService orderService){
        this.orderService = orderService;
    }

    @PostConstruct
    public void InitializeData(){
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(null, 1, false, 24, (new Date()).toString()));
        orderList.add(new Order(null, 2, false, 224, (new Date()).toString()));
        orderList.add(new Order(null, 3, false, 78, (new Date()).toString()));
        orderList.add(new Order(null, 4, false, 12, (new Date()).toString()));
        orderList.add(new Order(null, 5, false, 1000, (new Date()).toString()));
        List<Order> addedOrders = orderService.addAllOrders(orderList);

        List<OrderItems> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItems(null, addedOrders.get(0).getOrderId(), 1, 1, 24));
        orderItemList.add(new OrderItems(null, addedOrders.get(1).getOrderId(), 2, 10, 22));
        orderItemList.add(new OrderItems(null, addedOrders.get(2).getOrderId(), 3, 100, 200));
        orderItemList.add(new OrderItems(null, addedOrders.get(3).getOrderId(), 4, 1000, 400));
        orderItemList.add(new OrderItems(null, addedOrders.get(4).getOrderId(), 5, 10000, 1000));
        List<OrderItems> addOrderItems = orderService.addAllOrderItems(orderItemList);

    }
}
