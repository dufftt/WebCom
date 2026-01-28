package com.duft.product_service.Adapters.Controllers;

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
            Product prod = MapperUtils.map(product, Product.class);
            Product createdProduct = addProductUseCase.execute(prod);
            return ResponseEntity.ok("Customer Created with ID: "+createdProduct.getProductId());
        }

        @PostMapping("/addInventory")
        public ResponseEntity<String> addInventory(@RequestBody InventoryDTO inventory) {
           logger.info("Entered api: "+inventory);
            Inventory inv = MapperUtils.map(inventory, Inventory.class);
            Inventory createdInventory = addInventoryUseCase.execute(inv);
            return ResponseEntity.ok("Customer Created with ID: "+createdInventory.getInventoryId());
        }

    @PostMapping("/updateProduct")
    public ResponseEntity<String> UpdateProduct(@RequestBody ProductDTO product){
            logger.info("Entered Update Product api: "+product);
            Product prod = MapperUtils.map(product, Product.class);
            Product updatedProduct = updateProductUseCase.execute(prod);
            if(updatedProduct!=null){
              return ResponseEntity.ok("Product Updated with ID: "+updatedProduct.getProductId());
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
            }
    }

    @PostMapping("/updateInventory")
    public ResponseEntity<String> UpdateInventory(@RequestBody InventoryDTO inventory){
            logger.info("Entered Update Inventory api: "+inventory);
            Inventory inv = MapperUtils.map(inventory, Inventory.class);
            Inventory updatedInventory = updateInventoryUseCase.execute(inv);
            if(updatedInventory!=null){
              return ResponseEntity.ok("Inventory Updated with ID: "+updatedInventory.getInventoryId());
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory Not Found");
            }
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

}
