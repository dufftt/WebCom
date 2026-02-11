package com.duft.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.duft.order_service.Adapters.Controller.OrderRestController;
import com.duft.order_service.Adapters.out.Order.OrderRepositoryAdapter;
import com.duft.order_service.Adapters.out.OrderItem.OrderItemRepositoryAdapter;
import com.duft.order_service.Port.OrderItemRepositoryPort;
import com.duft.order_service.Port.OrderRepositoryPort;
import com.duft.order_service.domain.services.OrderService;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	@Bean
	public OrderRepositoryPort orderRepositoryPort(OrderRepositoryAdapter orderRepositoryAdapter){
		return orderRepositoryAdapter;
	}
	@Bean
	public OrderItemRepositoryPort orderItemRepositoryPort(OrderItemRepositoryAdapter orderItemRepositoryAdapter){
		return orderItemRepositoryAdapter;
	}
	@Bean
	public OrderService orderService(OrderRepositoryPort orderRepositoryPort, OrderItemRepositoryPort orderItemRepositoryPort){
		return new OrderService(orderRepositoryPort, orderItemRepositoryPort);
	}
	@Bean
	public OrderRestController orderRestController(OrderService orderService){
		return new OrderRestController(orderService);
	}


}
