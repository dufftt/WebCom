package com.duft.order_service.Adapters.out.OrderItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepositoryJPA extends JpaRepository<OrderItemEntity, Integer> {

   // List<OrderItemEntity> findByCustomerId(Integer customer_id);

    List<OrderItemEntity> findByOrderId(Integer orderId);

}
