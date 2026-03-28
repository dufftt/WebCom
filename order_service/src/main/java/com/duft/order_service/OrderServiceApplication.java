package com.duft.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.graphql.autoconfigure.GraphQlSourceBuilderCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.federation.FederationSchemaFactory;

import com.duft.order_service.Adapters.Controller.OrderServiceFacade;
import com.duft.order_service.Adapters.Controller.GraphQlController.OrderGraphQlController;
import com.duft.order_service.Adapters.Controller.RestController.OrderRestController;
import com.duft.order_service.Adapters.out.Order.OrderRepositoryAdapter;
import com.duft.order_service.Adapters.out.OrderItem.OrderItemRepositoryAdapter;
import com.duft.order_service.Port.OrderItemRepositoryPort;
import com.duft.order_service.Port.OrderRepositoryPort;
import com.duft.order_service.domain.services.OrderService;

@SpringBootApplication
@EnableDiscoveryClient
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
	public OrderServiceFacade orderServiceFacade(OrderService orderService){
		return new OrderServiceFacade(orderService);
	}
	@Bean
	public OrderRestController orderRestController(OrderServiceFacade orderServiceFacade){
		return new OrderRestController(orderServiceFacade);
	}
	@Bean
	public OrderGraphQlController orderGraphQlController(OrderServiceFacade orderServiceFacade){
		return new OrderGraphQlController(orderServiceFacade);
	}


		//graphql - apollo federation configuration
	@Bean
	public GraphQlSourceBuilderCustomizer customizer(FederationSchemaFactory factory) {
		return builder -> builder.schemaFactory(factory::createGraphQLSchema);
	}

	@Bean
	public FederationSchemaFactory schemaFactory() {
		return new FederationSchemaFactory();
	}


}
