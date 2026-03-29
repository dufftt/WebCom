package com.duft.shipping_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.graphql.autoconfigure.GraphQlSourceBuilderCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.federation.FederationSchemaFactory;

import com.duft.shipping_service.Adapters.out.Controller.ShipmentServiceFacade;
import com.duft.shipping_service.Adapters.out.Controller.GraphQlController.ShipmentGraphQLController;
import com.duft.shipping_service.Adapters.out.Controller.RestController.ShipmentRestController;
import com.duft.shipping_service.Adapters.out.persistence.Shipment.ShipmentRepositoryAdapter;
import com.duft.shipping_service.domain.services.ShipmentService;
import com.duft.shipping_service.port.ShipmentRepositoryPort;

@SpringBootApplication
@EnableDiscoveryClient
public class ShippingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShippingServiceApplication.class, args);
	}
	@Bean
	public ShipmentRepositoryPort shipmentRepositoryPort(ShipmentRepositoryAdapter shipmentRepositoryAdapter){
		return shipmentRepositoryAdapter;
	}
	@Bean
	public ShipmentService shipmentService(ShipmentRepositoryPort shipmentRepositoryPort){
		return new ShipmentService(shipmentRepositoryPort);
	}
	@Bean
	public ShipmentServiceFacade shipmentServiceFacade(ShipmentService shipmentService){
		return new ShipmentServiceFacade(shipmentService);
	}

	@Bean
	public ShipmentRestController shipmentRestController(ShipmentServiceFacade shipmentServiceFacade){
		return new ShipmentRestController(shipmentServiceFacade);
	}
	@Bean
	public ShipmentGraphQLController shipmentGraphQLController(ShipmentServiceFacade shipmentServiceFacade){
		return new ShipmentGraphQLController(shipmentServiceFacade);
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
