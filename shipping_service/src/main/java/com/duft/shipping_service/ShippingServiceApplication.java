package com.duft.shipping_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.duft.shipping_service.Adapters.Controller.ShipmentRestController;
import com.duft.shipping_service.Adapters.out.Shipment.ShipmentRepositoryAdapter;
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
	public ShipmentRestController shipmentRestController(ShipmentService shipmentService){
		return new ShipmentRestController(shipmentService);
	}

}
