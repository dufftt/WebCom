package com.duft.order_service.Adapters.out.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface OrderRepositoryJPA extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findByCustomerId(Integer customer_id);
    
    @Transactional
    @Modifying
    @Query("UPDATE OrderEntity o SET o.status = :status WHERE o.orderId = :orderId")
    void updateOrderStatus(@Param("orderId") Integer orderId,@Param("status") Boolean status);
}
