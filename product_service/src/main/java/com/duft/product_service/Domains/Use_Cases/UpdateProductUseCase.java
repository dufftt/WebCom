package com.duft.product_service.Domains.Use_Cases;

import com.duft.product_service.Domains.Entities.Product;
import com.duft.product_service.Ports.out.ProductRepositoryPort;

public class UpdateProductUseCase {
    
    private final ProductRepositoryPort productRepositoryPort;
    
    public UpdateProductUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }
    
    public Product execute(Product product){
        Product exisitngProduct = productRepositoryPort.findById(product.getProductId()).orElse(null);
        if(exisitngProduct!=null){
            product.setProductId(exisitngProduct.getProductId());
            Product prod = productRepositoryPort.UpdateProduct(product);
            return prod;
        }else{
            return null;
        }
    }
}
