package com.duft.product_service.Adapters.out.Product;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.duft.product_service.Domains.Entities.Product;
import com.duft.product_service.Ports.out.ProductRepositoryPort;
import com.duft.product_service.Utils.MapperUtils;
@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort{

    private ProductRepositoryJPA productRepositoryJPA;

    Logger logger = LoggerFactory.getLogger(ProductRepositoryAdapter.class);

    public ProductRepositoryAdapter(ProductRepositoryJPA productRepositoryJPA) {
        this.productRepositoryJPA = productRepositoryJPA;
    }

     //mapping from customer entity to customer
    private Product toDomain(ProductEntity e) {
        return MapperUtils.map(e, Product.class);
    }
    //mapping from customer to customer entity
    private ProductEntity toEntity(Product c) {
        return MapperUtils.map(c, ProductEntity.class);
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepositoryJPA.findById(id).map(this::toDomain);
    }

    @Override
    public Product save(Product product) {
        ProductEntity e = toEntity(product);
        product.setProductId(null);
        ProductEntity saved = productRepositoryJPA.saveAndFlush(e);
        logger.info("Saving Product: "+saved.toString());
        return toDomain(saved);
    }

    @Override
    public Boolean DeleteProductById(Integer id) {
        try {
            productRepositoryJPA.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete: "+e);
            return false;
        }
        return true;
    }

    @Override
    public Product UpdateProduct(Product product) {
        ProductEntity e = toEntity(product);
        ProductEntity saved = productRepositoryJPA.saveAndFlush(e);
        logger.info("Updated Product: "+saved.toString());
        return toDomain(saved);
    }

}
