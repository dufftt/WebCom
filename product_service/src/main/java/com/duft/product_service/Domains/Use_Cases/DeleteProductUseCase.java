package com.duft.product_service.Domains.Use_Cases;

import com.duft.product_service.Domains.Entities.Product;
import com.duft.product_service.Ports.out.ProductRepositoryPort;

public class DeleteProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public DeleteProductUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public Boolean execute(Integer id){
        Product exisitngProduct = productRepositoryPort.findById(id).orElse(null);
        if(exisitngProduct!=null){
            Boolean deleteStatus = productRepositoryPort.DeleteProductById(id);
            return deleteStatus;
        }else{
            return false;
        }
    }
}
