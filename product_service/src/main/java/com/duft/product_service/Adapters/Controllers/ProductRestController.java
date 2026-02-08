package com.duft.product_service.Adapters.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duft.product_service.Adapters.WebDTO.InventoryDTO;
import com.duft.product_service.Adapters.WebDTO.ProductDTO;
import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Domains.Entities.Product;
import com.duft.product_service.Domains.Use_Cases.AddInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.AddProductUseCase;
import com.duft.product_service.Domains.Use_Cases.DeleteInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.DeleteProductUseCase;
import com.duft.product_service.Domains.Use_Cases.UpdateInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.UpdateProductUseCase;
import com.duft.product_service.Utils.MapperUtils;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final AddProductUseCase addProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final AddInventoryUseCase addInventoryUseCase;
    private final UpdateInventoryUseCase updateInventoryUseCase;
    private final DeleteInventoryUseCase deleteInventoryUseCase;
    
   Logger logger = LoggerFactory.getLogger(ProductRestController.class);


   public ProductRestController(AddProductUseCase addProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase,
        AddInventoryUseCase addInventoryUseCase, UpdateInventoryUseCase updateInventoryUseCase, DeleteInventoryUseCase deleteInventoryUseCase){
            this.addProductUseCase = addProductUseCase;
            this.updateProductUseCase = updateProductUseCase;
            this.deleteProductUseCase = deleteProductUseCase;
            this.addInventoryUseCase = addInventoryUseCase;
            this.updateInventoryUseCase = updateInventoryUseCase;
            this.deleteInventoryUseCase = deleteInventoryUseCase;
        }
        @PostMapping("/addProduct")
        public ResponseEntity<String> addProduct(@RequestBody ProductDTO product) {
           logger.info("Entered api: "+product);
           if(product.isValidProductDTO()){
            Product prod = MapperUtils.map(product, Product.class);
            Product createdProduct = addProductUseCase.execute(prod);
            return ResponseEntity.ok("Product Created with ID: "+createdProduct.getProductId());
           }
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Product DTO");
        }

        //TODO need to update add inventory and update inventory such that it will work only if the given product id already present in the product table
        
        @PostMapping("/addInventory")
        public ResponseEntity<String> addInventory(@RequestBody InventoryDTO inventory) {
           logger.info("Entered api: "+inventory);
           if(inventory.isValidInventoryDTO()){
            Inventory inv = MapperUtils.map(inventory, Inventory.class);
            Inventory createdInventory = addInventoryUseCase.execute(inv);
            return ResponseEntity.ok("Inventory Created with ID: "+createdInventory.getInventoryId());
           }
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Inventory DTO");
        }

            @PostMapping("/updateProduct")
            public ResponseEntity<String> UpdateProduct(@RequestBody ProductDTO product){
            logger.info("Entered Update Product api: "+product);
            if(product.isValidProductDTO()){
            Product prod = MapperUtils.map(product, Product.class);
            Product updatedProduct = updateProductUseCase.execute(prod);
            if(updatedProduct!=null){
              return ResponseEntity.ok("Product Updated with ID: "+updatedProduct.getProductId());
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
            }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Product DTO");
    }
    //TODO need to update add inventory and update inventory such that it will work only if the given product id already present in the database

    @PostMapping("/updateInventory")
    public ResponseEntity<String> UpdateInventory(@RequestBody InventoryDTO inventory){
            logger.info("Entered Update Inventory api: "+inventory);
            if(inventory.isValidInventoryDTO()){
            Inventory inv = MapperUtils.map(inventory, Inventory.class);
            Inventory updatedInventory = updateInventoryUseCase.execute(inv);
            if(updatedInventory!=null){
              return ResponseEntity.ok("Inventory Updated with ID: "+updatedInventory.getInventoryId());
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory Not Found");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Inventory DTO");
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity<String> DeleteProduct(@RequestParam Integer id){
            logger.info("Entered Delete Product api: "+id);
                Boolean status = deleteProductUseCase.execute(id);
            if(status){
                return ResponseEntity.ok("Product Deleted with ID: "+id+" with Status: "+status);          
              }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
            }
    }

    @PostMapping("/deleteInventory")
    public ResponseEntity<String> DeleteInventory(@RequestParam Integer id){
            logger.info("Entered Delete Inventory api: "+id);
                Boolean status = deleteInventoryUseCase.execute(id);
            if(status){
                return ResponseEntity.ok("Inventory Deleted with ID: "+id+" with Status: "+status);    
                    }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory Not Found");
            }
    }
    //TODO add getProductsList should add return all products
    //TODO add getInventoryList should add return all inventories
    //TODO add getInventoryByProductId should add return inventory id by product id and having at least one record with quantity greater than 0
    //TODO add boolean getInventoryAvailablewithProductId return true if at least location has quantity greater than 0 else false
    //TODO add reducenInventoryQuantityByInventoryID should reduce quantity by quantity parameter in inventory table by inventory id
    //TODO add lockInventoryByInventoryID -> it will temporarily(15 mins or so) hold given quantity of that inventory id while the order processes 
    //TODO releaseInventoryByInventoryID -> release locked inventory either back to inventory or reduces it(if order is success)
    
    @PostMapping("/getProductsList")
    public ResponseEntity<String> getProductsList(){
          
        return ResponseEntity.ok("Product List");
    }
    @PostMapping("/getInventoryList")
    public ResponseEntity<String> getInventoryList(){
            return ResponseEntity.ok("Inventory List");
    }
    @PostMapping("/getInventoryByProductId")
    public ResponseEntity<String> getInventoryByProductId(@RequestParam Integer id){
          return ResponseEntity.ok("Inventory List");
     
    }
    @PostMapping("/getInventoryAvailablewithProductId")
    public ResponseEntity<String> getInventoryAvailablewithProductId(@RequestParam Integer id){
            return ResponseEntity.ok("Inventory List");
    }
    @PostMapping("/reducenInventoryQuantityByInventoryID")
    public ResponseEntity<String> reduceInventoryQuantityByInventoryID(@RequestParam Integer id){
            return ResponseEntity.ok("Inventory List");
    }
    @PostMapping("/lockInventoryByInventoryID")
    public ResponseEntity<String> holdInventory(@RequestParam Integer id){
            return ResponseEntity.ok("Inventory List");
    }
    @PostMapping("/releaseInventoryByInventoryID")
    public ResponseEntity<String> releaseInventory(@RequestParam Integer id){
            return ResponseEntity.ok("Inventory List");
    }
    @PostMapping("/getLocationByProductId")
    public ResponseEntity<List<String>> getLocationByProductId(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<String>());
    }
    @PostMapping("/getProductPriceByProductID")
    public ResponseEntity<Integer> getProductPriceByProductID(Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(0);
    }

}
