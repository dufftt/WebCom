package com.duft.product_service.Adapters.out.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryJPA extends JpaRepository<ProductEntity, Integer>{

    public Integer getPriceByProductId(Integer id);

}
