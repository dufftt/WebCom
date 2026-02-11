package com.duft.order_service.Adapters.out.Order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.duft.order_service.Port.OrderRepositoryPort;
import com.duft.order_service.domain.entities.Order;

@Repository
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private OrderRepositoryJPA orderRepositoryJPA;

    public OrderRepositoryAdapter(OrderRepositoryJPA orderRepositoryJPA) {
        this.orderRepositoryJPA = orderRepositoryJPA;
    }

    @Override
    public Order addOrder(Order order) {
       OrderEntity orderEntity = orderRepositoryJPA.save(new OrderEntity(null, order.getCustomerId(), false , order.getTotal(),new java.util.Date().toString()));
        return new Order(orderEntity.getOrderId(), orderEntity.getCustomerId(), orderEntity.getStatus(), orderEntity.getTotal(), orderEntity.getCreated_date());
    }

    @Override
    public List<Order> getOrderListByCustomerId(Integer customer_id) {
       List<OrderEntity> orderList = orderRepositoryJPA.findByCustomerId(customer_id);
      List<Order> finalOrderList = orderList.stream().map(orderEntity -> new Order(orderEntity.getOrderId(), orderEntity.getCustomerId(), orderEntity.getStatus(), orderEntity.getTotal(), orderEntity.getCreated_date())).toList();
        return finalOrderList;
    }

    @Override
    public Order getOrder(Integer orderId) {
       OrderEntity orderEntity = orderRepositoryJPA.findById(orderId).get();
       return new Order(orderEntity.getOrderId(),orderEntity.getCustomerId(),orderEntity.getStatus(),orderEntity.getTotal(),orderEntity.getCreated_date());
    }

    @Override
    public void updateOrderStatus(Integer orderId, Boolean status) {
        orderRepositoryJPA.updateOrderStatus(orderId, status);

    }

}
