package com.duft.product_service.Adapters.out.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.duft.product_service.Adapters.WebDTO.InventoryDTO;
import com.duft.product_service.Adapters.WebDTO.LockOrReleaseInventoryDTO;
import com.duft.product_service.Adapters.WebDTO.ProductDTO;
import com.duft.product_service.Adapters.out.Controllers.RestController.ProductRestController;
import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Domains.Entities.Product;
import com.duft.product_service.Domains.Services.ProductInventoryService;
import com.duft.product_service.Domains.exceptions.BadRequestException;
import com.duft.product_service.Domains.exceptions.InventoryNotFoundException;
import com.duft.product_service.Domains.exceptions.ProductNotFoundException;
import com.duft.product_service.Utils.MapperUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductServiceFacade {

    private final ProductInventoryService productInventoryService;
   Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    private final ObjectMapper mapper = new ObjectMapper();

   
    public ProductServiceFacade(ProductInventoryService productInventoryService) {
        this.productInventoryService = productInventoryService;
    }

        public String addProduct(ProductDTO product) {
           logger.info("Entered api: "+product);
           if(product.isValidProductDTO()){
            Product prod = MapperUtils.map(product, Product.class);
            Product createdProduct = productInventoryService.addProduct(prod);
            return createdProduct.getProductId().toString();
           }
            throw new BadRequestException("Invalid Product DTO");
        }

        public String addInventory(InventoryDTO inventory) {
           logger.info("Entered api: "+inventory);
           if(inventory.isValidInventoryDTO()){
            Inventory inv = MapperUtils.map(inventory, Inventory.class);
            Inventory createdInventory = productInventoryService.addInventory(inv);
            if(createdInventory!=null){
             return createdInventory.getInventoryId().toString();

            }else{
                throw new ProductNotFoundException("Product ID: "+inventory.getProductId()+" Not found");    
            }
           }
                throw new BadRequestException("Invalid Inventory DTO");
        }

            public String UpdateProduct(ProductDTO product){
            logger.info("Entered Update Product api: "+product);
            if(product.isValidProductDTO()){
            Product prod = MapperUtils.map(product, Product.class);
            Product updatedProduct = productInventoryService.updateProduct(prod);
            if(updatedProduct!=null){
              return updatedProduct.getProductId().toString();
            }
            else{
                throw new ProductNotFoundException("Product Not Found");
            }
            }
            throw new BadRequestException("Invalid Product DTO");
    }

    public String UpdateInventory(InventoryDTO inventory){
            logger.info("Entered Update Inventory api: "+inventory);
            if(inventory.isValidInventoryDTO()){
            Inventory inv = MapperUtils.map(inventory, Inventory.class);
            Inventory updatedInventory = productInventoryService.updateInventory(inv);
            if(updatedInventory!=null){
              return updatedInventory.getInventoryId().toString();
            }
            else{
                throw new InventoryNotFoundException("Inventory/Product Not Found");
            }
        }
        throw new BadRequestException("Invalid Inventory DTO");
    }

    public String DeleteProduct(Integer id){
            logger.info("Entered Delete Product api: "+id);
                Boolean status = productInventoryService.deleteProduct(id);
            if(status){
                return status.toString();          
              }
            else{
                throw new BadRequestException("Product Not Found");
            }
    }

    public String DeleteInventory(Integer id){
            logger.info("Entered Delete Inventory api: "+id);
                Boolean status = productInventoryService.deleteInventory(id);
            if(status){
                return status.toString();    
                    }
            else{
                throw new BadRequestException("Inventory Not Found");
            }
    }

    public List<ProductDTO> getProductsList(){
        return MapperUtils.mapList(productInventoryService.getProductList(), ProductDTO.class);
    }
  
    public List<InventoryDTO> getInventoryList(){
        return MapperUtils.mapList(productInventoryService.getInventoryList(), InventoryDTO.class);
    }

    public List<InventoryDTO> getInventoryByProductId(Integer id){ 
        List<Inventory> lInventories = productInventoryService.getInventoryByProductId(id);
        if(lInventories==null || lInventories.isEmpty()){
            throw new InventoryNotFoundException("Inventory is empty");
        }
          return MapperUtils.mapList(lInventories, InventoryDTO.class);
    }

    public Integer getProductPriceByProductID(Integer id){
        Integer price = productInventoryService.getProductPriceByProductId(id);
        if(price==null){
            throw new ProductNotFoundException("Product not found");
        }
        return price;
    }
  
    public Boolean getInventoryAvailablewithProductId(Integer id){
            return productInventoryService.getInventoryAvailablewithProductId(id);
    }

    public Boolean holdInventory(LockOrReleaseInventoryDTO request){
            return productInventoryService.lockInventory(request.getInventoryId(),request.getQuantity());
    }

    public Boolean releaseInventory(LockOrReleaseInventoryDTO request){
            return productInventoryService.releaseInventory(request.getInventoryId(), request.getQuantity());
    }

    public List<String> getLocationByProductId(Integer id){
        return productInventoryService.getLocationsByProductId(id);
    }

    public ProductDTO getProduct(Integer productId) {
        Product prod = productInventoryService.getProduct(productId);
        if(prod==null){
            throw new ProductNotFoundException("Product not found");
        }
        return MapperUtils.map(prod, ProductDTO.class);
    }

    public InventoryDTO getInventory(Integer inventoryId) {
        Inventory inventory = productInventoryService.getInventory(inventoryId);
        if(inventory==null){
            throw new InventoryNotFoundException("Inventory Not found");
        }
        return MapperUtils.map(inventory, InventoryDTO.class);
    }
   

}
