package com.duft.product_service.Domains.Use_Cases;

import com.duft.product_service.Domains.Entities.Product;
import com.duft.product_service.Ports.out.ProductRepositoryPort;

public class AddProductUseCase {

    private ProductRepositoryPort productRepositoryPort;

    public AddProductUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public Product execute(Product product){
        Product prod = productRepositoryPort.save(product);
        return prod;
    }
}
