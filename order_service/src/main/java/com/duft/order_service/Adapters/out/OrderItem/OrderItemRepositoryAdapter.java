package com.duft.order_service.Adapters.out.OrderItem;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.duft.order_service.Port.OrderItemRepositoryPort;
import com.duft.order_service.domain.entities.OrderItems;

@Repository
public class OrderItemRepositoryAdapter implements OrderItemRepositoryPort {

    private OrderItemRepositoryJPA orderItemRepositoryJPA;

    public OrderItemRepositoryAdapter(OrderItemRepositoryJPA orderItemRepositoryJPA) {
        this.orderItemRepositoryJPA = orderItemRepositoryJPA;
    }


    @Override
    public OrderItems addOrderItems(OrderItems orderItems) {
        // orderItemRepositoryJPA.save(new OrderItemEntity(null, orderItems.getOrderId(), orderItems.getProductId(), orderItems.getQuantity(), orderItems.getPrice()));
        // return null;
        OrderItemEntity savedEntity = orderItemRepositoryJPA.save(new OrderItemEntity(null, orderItems.getOrderId(), orderItems.getProductId(), orderItems.getQuantity(), orderItems.getPrice()));
        return new OrderItems(savedEntity.getOrderItemId(), savedEntity.getOrderId(), savedEntity.getProductId(), savedEntity.getQuantity(), savedEntity.getPrice());
    }

    // @Override
    // public List<OrderItems> getOrderItemsListByCustomerId(Integer customer_id) {
    //    List<OrderItemEntity> orderItemsList = orderItemRepositoryJPA.findByCustomerId(customer_id);
    //    List<OrderItems> finalOrderItemsList = orderItemsList.stream().map(orderItemEntity -> new OrderItems(orderItemEntity.getOrderItemId(), orderItemEntity.getOrderId(), orderItemEntity.getProductId(), orderItemEntity.getQuantity(), orderItemEntity.getPrice())).toList();
    //     return finalOrderItemsList;
    // }

    @Override
    public List<OrderItems> getOrderItemsListByOrderId(Integer orderId) {
        List<OrderItemEntity> orderItemsList = orderItemRepositoryJPA.findByOrderId(orderId);
        List<OrderItems> finalOrderItemsList = orderItemsList.stream().map(orderItemsEntity -> new OrderItems(orderItemsEntity.getOrderItemId(), orderItemsEntity.getOrderId(), orderItemsEntity.getProductId(), orderItemsEntity.getQuantity(), orderItemsEntity.getPrice())).toList();
        return finalOrderItemsList;
    }

}
