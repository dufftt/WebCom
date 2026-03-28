package com.duft.product_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.graphql.autoconfigure.GraphQlSourceBuilderCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.federation.FederationSchemaFactory;

import com.duft.product_service.Adapters.out.Controllers.ProductServiceFacade;
import com.duft.product_service.Adapters.out.Controllers.GraphQlController.ProductGraphQlController;
import com.duft.product_service.Adapters.out.Controllers.RestController.ProductRestController;
import com.duft.product_service.Adapters.out.Inventory.InventoryRepositoryAdapter;
import com.duft.product_service.Adapters.out.Product.ProductRepositoryAdapter;
import com.duft.product_service.Domains.Services.ProductInventoryService;
import com.duft.product_service.Domains.Use_Cases.AddInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.AddProductUseCase;
import com.duft.product_service.Domains.Use_Cases.DeleteInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.DeleteProductUseCase;
import com.duft.product_service.Domains.Use_Cases.UpdateInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.UpdateProductUseCase;
import com.duft.product_service.Ports.out.InventoryRepositoryPort;
import com.duft.product_service.Ports.out.ProductRepositoryPort;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean	
	public ProductRepositoryPort productRepositoryPort(ProductRepositoryAdapter productRepositoryAdapter){
		return productRepositoryAdapter;
	}
	
	@Bean
	public InventoryRepositoryPort inventoryRepositoryPort(InventoryRepositoryAdapter inventoryRepositoryAdapter){
		return inventoryRepositoryAdapter;
	}
	
	@Bean
	public AddProductUseCase addProductUseCase(ProductRepositoryPort productRepositoryPort){
		return new AddProductUseCase(productRepositoryPort);
	}

	@Bean
	public AddInventoryUseCase addInventoryUseCase(InventoryRepositoryPort inventoryRepositoryPort){
		return new AddInventoryUseCase(inventoryRepositoryPort);
	}

	@Bean
	public UpdateProductUseCase updateProductUseCase(ProductRepositoryPort productRepositoryPort){
		return new UpdateProductUseCase(productRepositoryPort);
	}
	@Bean
	public UpdateInventoryUseCase updateInventoryUseCase(InventoryRepositoryPort inventoryRepositoryPort){
		return new UpdateInventoryUseCase(inventoryRepositoryPort);
	}
	@Bean
	public DeleteProductUseCase deleteProductUseCase(ProductRepositoryPort productRepositoryPort){
		return new DeleteProductUseCase(productRepositoryPort);
	}
	@Bean
	public DeleteInventoryUseCase deleteInventoryUseCase(InventoryRepositoryPort inventoryRepositoryPort){
		return new DeleteInventoryUseCase(inventoryRepositoryPort);
	}
	@Bean
	public ProductInventoryService productInventoryService(AddProductUseCase addProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase,
			AddInventoryUseCase addInventoryUseCase, UpdateInventoryUseCase updateInventoryUseCase, DeleteInventoryUseCase deleteInventoryUseCase, ProductRepositoryPort productRepositoryPort, InventoryRepositoryPort inventoryRepositoryPort){
				return new ProductInventoryService(addProductUseCase, updateProductUseCase, deleteProductUseCase,
						addInventoryUseCase, updateInventoryUseCase, deleteInventoryUseCase, productRepositoryPort,inventoryRepositoryPort);
			}

	@Bean
	public ProductServiceFacade productServiceFacade(ProductInventoryService productInventoryService){
		return new ProductServiceFacade(productInventoryService);
	}

	@Bean
	public ProductRestController productRestController(ProductServiceFacade productServiceFacade){
		return new ProductRestController(productServiceFacade);
	}

	@Bean
	public ProductGraphQlController productGraphQlController(ProductServiceFacade productServiceFacade){
		return new ProductGraphQlController(productServiceFacade);
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
