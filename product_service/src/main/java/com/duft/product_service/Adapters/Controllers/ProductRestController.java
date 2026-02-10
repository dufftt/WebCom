package com.duft.product_service.Adapters.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duft.product_service.Adapters.WebDTO.InventoryDTO;
import com.duft.product_service.Adapters.WebDTO.LockOrReleaseInventoryDTO;
import com.duft.product_service.Adapters.WebDTO.ProductDTO;
import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Domains.Entities.Product;
import com.duft.product_service.Domains.Services.ProductInventoryService;
import com.duft.product_service.Utils.MapperUtils;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductInventoryService productInventoryService;
   Logger logger = LoggerFactory.getLogger(ProductRestController.class);


   
    public ProductRestController(ProductInventoryService productInventoryService) {
        this.productInventoryService = productInventoryService;
    }

        @PostMapping("/addProduct")
        public ResponseEntity<String> addProduct(@RequestBody ProductDTO product) {
           logger.info("Entered api: "+product);
           if(product.isValidProductDTO()){
            Product prod = MapperUtils.map(product, Product.class);
            Product createdProduct = productInventoryService.addProduct(prod);
            return ResponseEntity.ok("Product Created with ID: "+createdProduct.getProductId());
           }
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Product DTO");
        }

        @PostMapping("/addInventory")
        public ResponseEntity<String> addInventory(@RequestBody InventoryDTO inventory) {
           logger.info("Entered api: "+inventory);
           if(inventory.isValidInventoryDTO()){
            Inventory inv = MapperUtils.map(inventory, Inventory.class);
            Inventory createdInventory = productInventoryService.addInventory(inv);
            if(createdInventory!=null){
             return ResponseEntity.ok("Inventory Created with ID: "+createdInventory.getInventoryId());

            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product ID: "+inventory.getProductId()+" Not found");
            }
           }
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Inventory DTO");
        }

            @PostMapping("/updateProduct")
            public ResponseEntity<String> UpdateProduct(@RequestBody ProductDTO product){
            logger.info("Entered Update Product api: "+product);
            if(product.isValidProductDTO()){
            Product prod = MapperUtils.map(product, Product.class);
            Product updatedProduct = productInventoryService.updateProduct(prod);
            if(updatedProduct!=null){
              return ResponseEntity.ok("Product Updated with ID: "+updatedProduct.getProductId());
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
            }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Product DTO");
    }

    @PostMapping("/updateInventory")
    public ResponseEntity<String> UpdateInventory(@RequestBody InventoryDTO inventory){
            logger.info("Entered Update Inventory api: "+inventory);
            if(inventory.isValidInventoryDTO()){
            Inventory inv = MapperUtils.map(inventory, Inventory.class);
            Inventory updatedInventory = productInventoryService.updateInventory(inv);
            if(updatedInventory!=null){
              return ResponseEntity.ok("Inventory Updated with ID: "+updatedInventory.getInventoryId());
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory/Product Not Found");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Inventory DTO");
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity<String> DeleteProduct(@RequestParam Integer id){
            logger.info("Entered Delete Product api: "+id);
                Boolean status = productInventoryService.deleteProduct(id);
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
                Boolean status = productInventoryService.deleteInventory(id);
            if(status){
                return ResponseEntity.ok("Inventory Deleted with ID: "+id+" with Status: "+status);    
                    }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory Not Found");
            }
    }

    @GetMapping("/getProductsList")
    public ResponseEntity<List<ProductDTO>> getProductsList(){
        return ResponseEntity.ok(MapperUtils.mapList(productInventoryService.getProductList(), ProductDTO.class));
    }
    @GetMapping("/getInventoryList")
    public ResponseEntity<List<InventoryDTO>> getInventoryList(){
        return ResponseEntity.ok(MapperUtils.mapList(productInventoryService.getInventoryList(), InventoryDTO.class));
    }
    @GetMapping("/getInventoryByProductId")
    public ResponseEntity<List<InventoryDTO>> getInventoryByProductId(@RequestParam Integer id){ 
          return ResponseEntity.ok(MapperUtils.mapList(productInventoryService.getInventoryByProductId(id), InventoryDTO.class));
     
    }
    @GetMapping("/getInventoryAvailablewithProductId")
    public ResponseEntity<Boolean> getInventoryAvailablewithProductId(@RequestParam Integer id){
            return ResponseEntity.ok(productInventoryService.getInventoryAvailablewithProductId(id));
    }

    @PostMapping("/lockInventoryByInventoryID")
    public ResponseEntity<Boolean> holdInventory(@RequestBody LockOrReleaseInventoryDTO request){
            return ResponseEntity.ok(productInventoryService.lockInventory(request.getInventoryId(),request.getQuantity()));
    }
    @PostMapping("/releaseInventoryByInventoryID")
    public ResponseEntity<Boolean> releaseInventory(@RequestBody LockOrReleaseInventoryDTO request){
            return ResponseEntity.ok(productInventoryService.releaseInventory(request.getInventoryId(), request.getQuantity()));
    }
    @GetMapping("/getLocationByProductId")
    public ResponseEntity<List<String>> getLocationByProductId(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(productInventoryService.getLocationsByProductId(id));
    }
    @GetMapping("/getProductPriceByProductID")
    public ResponseEntity<Integer> getProductPriceByProductID(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(productInventoryService.getProductPriceByProductId(id));
    }

}
