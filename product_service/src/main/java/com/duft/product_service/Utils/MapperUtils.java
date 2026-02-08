package com.duft.product_service.Utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.duft.product_service.Adapters.WebDTO.InventoryDTO;
import com.duft.product_service.Adapters.WebDTO.ProductDTO;
import com.duft.product_service.Adapters.out.Inventory.InventoryEntity;
import com.duft.product_service.Adapters.out.Product.ProductEntity;
import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Domains.Entities.Product;

public final class MapperUtils {

    private MapperUtils() {}
    static final Logger logger = LoggerFactory.getLogger(MapperUtils.class);
    
    public static <T> T map(Object source, Class<T> targetClass) {
        if (source == null) return null;
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            logger.info("Mapper: "+target);
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception ex) {
            throw new RuntimeException("Failed to map " + source.getClass() + " to " + targetClass, ex);
        }
    }

    //TODO write new mapper static method which will manually map from product -> productDTO, product->productEntity and so on and similarly for inventory->inventoryDTO, inventory->inventoryEntity and so on
    public static Object mapper(Object source, Class targetClass) {
        if (source == null) return null;
        try {
            //write logic to map from source to target class instance
           if(targetClass==ProductDTO.class && source instanceof Product){
            Product product = (Product) source;
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setProductDescription(product.getProductDescription());
            productDTO.setPrice(product.getPrice());
            return productDTO;
           }else if(targetClass==Product.class && source instanceof ProductDTO){
            ProductDTO productDTO = (ProductDTO) source;
            Product product = new Product();
            product.setProductId(productDTO.getProductId());
            product.setProductName(productDTO.getProductName());
            product.setProductDescription(productDTO.getProductDescription());
            product.setPrice(productDTO.getPrice());
            return product;
           }else if(targetClass==ProductDTO.class && source instanceof ProductEntity){
            ProductEntity productEntity = (ProductEntity) source;
            ProductDTO product = new ProductDTO();
            product.setProductId(productEntity.getProductId());
            product.setProductName(productEntity.getProductName());
            product.setProductDescription(productEntity.getProductDescription());
            product.setPrice(productEntity.getPrice());
            return product;
           }else if(targetClass==ProductEntity.class && source instanceof ProductDTO){
            ProductDTO productDTO = (ProductDTO) source;
            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductId(productDTO.getProductId());
            productEntity.setProductName(productDTO.getProductName());
            productEntity.setProductDescription(productDTO.getProductDescription());
            productEntity.setPrice(productDTO.getPrice());
            return productEntity;
           }else if(targetClass==ProductEntity.class && source instanceof Product){
            Product product = (Product) source;
            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductId(product.getProductId());
            productEntity.setProductName(product.getProductName());
            productEntity.setProductDescription(product.getProductDescription());
            productEntity.setPrice(product.getPrice());
            return productEntity;
           }else if(targetClass==Product.class && source instanceof ProductEntity){
            ProductEntity productEntity = (ProductEntity) source;
            Product product = new Product();
            product.setProductId(productEntity.getProductId());
            product.setProductName(productEntity.getProductName());
            product.setProductDescription(productEntity.getProductDescription());
            product.setPrice(productEntity.getPrice());
            return product;
           }else if(targetClass==InventoryDTO.class && source instanceof Inventory){
            Inventory inventory = (Inventory) source;
            InventoryDTO inventoryDTO = new InventoryDTO();
            inventoryDTO.setInventoryId(inventory.getInventoryId());
            inventoryDTO.setProductId(inventory.getProductId());
            inventoryDTO.setQuantity(inventory.getQuantity());
            inventoryDTO.setLocation(inventory.getLocation());
            return inventoryDTO;
           }else if(targetClass==Inventory.class && source instanceof InventoryDTO){
            InventoryDTO inventoryDTO = (InventoryDTO) source;
            Inventory inventory = new Inventory();
            inventory.setInventoryId(inventoryDTO.getInventoryId());
            inventory.setProductId(inventoryDTO.getProductId());
            inventory.setQuantity(inventoryDTO.getQuantity());
            inventory.setLocation(inventoryDTO.getLocation());
            return inventory;
           }else if(targetClass==Inventory.class && source instanceof InventoryEntity){
            InventoryEntity inventoryEntity = (InventoryEntity) source;
            Inventory inventory = new Inventory();
            inventory.setInventoryId(inventoryEntity.getInventoryId()); 
            inventory.setProductId(inventoryEntity.getProductId());
            inventory.setQuantity(inventoryEntity.getQuantity());
            inventory.setLocation(inventoryEntity.getLocation());
            return inventory;
           }else if(targetClass==InventoryEntity.class && source instanceof Inventory){
            Inventory inventory = (Inventory) source;
            InventoryEntity inventoryEntity = new InventoryEntity();
            inventoryEntity.setInventoryId(inventory.getInventoryId());
            inventoryEntity.setProductId(inventory.getProductId());
            inventoryEntity.setQuantity(inventory.getQuantity());
            inventoryEntity.setLocation(inventory.getLocation());
            return inventoryEntity;
           }else if(targetClass==InventoryEntity.class && source instanceof InventoryDTO){
            InventoryDTO inventoryDTO = (InventoryDTO) source;
            InventoryEntity inventoryEntity = new InventoryEntity();
            inventoryEntity.setInventoryId(inventoryDTO.getInventoryId());
            inventoryEntity.setProductId(inventoryDTO.getProductId());
            inventoryEntity.setQuantity(inventoryDTO.getQuantity());
            inventoryEntity.setLocation(inventoryDTO.getLocation());
            return inventoryEntity;
           }else if(targetClass==InventoryDTO.class && source instanceof InventoryEntity){
            InventoryEntity inventoryEntity = (InventoryEntity) source;
            InventoryDTO inventoryDTO = new InventoryDTO();
            inventoryDTO.setInventoryId(inventoryEntity.getInventoryId());
            inventoryDTO.setProductId(inventoryEntity.getProductId());
            inventoryDTO.setQuantity(inventoryEntity.getQuantity());
            inventoryDTO.setLocation(inventoryEntity.getLocation());
            return inventoryDTO;
           }

        } catch (Exception ex) {
        }
            
        return null;
    }

    public static <S, T> List<T> mapList(Collection<S> source, Class<T> targetClass) {
        if (source == null) return null;
        return source.stream().map(s -> map(s, targetClass)).collect(Collectors.toList());
    }

}
