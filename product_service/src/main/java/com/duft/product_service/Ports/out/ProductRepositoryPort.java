package com.duft.product_service.Ports.out;

import java.util.List;
import java.util.Optional;

import com.duft.product_service.Domains.Entities.Product;

public interface ProductRepositoryPort {

    Optional<Product> findById(int id);
    Product save(Product product);
    Boolean DeleteProductById(Integer id);
    Product UpdateProduct(Product product);
    Integer getProductPriceByProductID(Integer id);
    List<Product> getAllProducts();
}
