package com.duft.product_service.Adapters.InitializeData;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Domains.Entities.Product;
import com.duft.product_service.Domains.Services.ProductInventoryService;

import jakarta.annotation.PostConstruct;

@Configuration
public class ProductBaseData {

    private ProductInventoryService productInventoryService;

    public ProductBaseData(ProductInventoryService productInventoryService) {
        this.productInventoryService = productInventoryService;
    }

    @PostConstruct
    public void InitializeData(){
       List<Product> products = new ArrayList<>();
        products.add(new Product(null, "Iphone", "A mobile Phone", 25));
        products.add(new Product(null, "Google Pixel", "Another mobile Phone", 45));
        products.add(new Product(null, "Galaxy S25", "Best Phone", 55));
        products.add(new Product(null, "Macbook", "A Laptop", 255));
        products.add(new Product(null, "LOQ", "Best gaming Laptop", 275));
        List<Product> addedProducts = productInventoryService.addAllProduct(products);

        List<Inventory> inventories = new ArrayList<>();
        inventories.add(new Inventory(null, addedProducts.get(0).getProductId(), 20, "Kolkata", 0));
        inventories.add(new Inventory(null, addedProducts.get(1).getProductId(), 40, "Bangalore", 0));
        inventories.add(new Inventory(null, addedProducts.get(2).getProductId(), 50, "Mumbai", 0));
        inventories.add(new Inventory(null, addedProducts.get(3).getProductId(), 60, "Delhi", 0));
        inventories.add(new Inventory(null, addedProducts.get(4).getProductId(), 80, "Bhopal", 0));
        List<Inventory> addedInventories = productInventoryService.addAllInventories(inventories);

    }
}
