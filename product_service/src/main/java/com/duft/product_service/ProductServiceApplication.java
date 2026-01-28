package com.duft.product_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.duft.product_service.Adapters.Controllers.ProductRestController;
import com.duft.product_service.Adapters.out.Inventory.InventoryRepositoryAdapter;
import com.duft.product_service.Adapters.out.Product.ProductRepositoryAdapter;
import com.duft.product_service.Domains.Use_Cases.AddInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.AddProductUseCase;
import com.duft.product_service.Domains.Use_Cases.DeleteInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.DeleteProductUseCase;
import com.duft.product_service.Domains.Use_Cases.UpdateInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.UpdateProductUseCase;
import com.duft.product_service.Ports.out.InventoryRepositoryPort;
import com.duft.product_service.Ports.out.ProductRepositoryPort;

@SpringBootApplication
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
	public ProductRestController productRestController(AddProductUseCase addProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase,
			AddInventoryUseCase addInventoryUseCase, UpdateInventoryUseCase updateInventoryUseCase, DeleteInventoryUseCase deleteInventoryUseCase){
				return new ProductRestController(addProductUseCase, updateProductUseCase, deleteProductUseCase,
						addInventoryUseCase, updateInventoryUseCase, deleteInventoryUseCase);
			}

}
