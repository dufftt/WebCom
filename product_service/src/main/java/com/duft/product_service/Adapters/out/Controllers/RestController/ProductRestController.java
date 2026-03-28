package com.duft.product_service.Adapters.out.Controllers.RestController;

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
import com.duft.product_service.Adapters.out.Controllers.ProductServiceFacade;
import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Domains.Entities.Product;
import com.duft.product_service.Domains.Services.ProductInventoryService;
import com.duft.product_service.Utils.MapperUtils;

import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductServiceFacade productServiceFacade;
   Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    private final ObjectMapper mapper = new ObjectMapper();

   
    public ProductRestController(ProductServiceFacade productServiceFacade) {
        this.productServiceFacade = productServiceFacade;
    }

        @PostMapping("/addProduct")
        public ResponseEntity<String> addProduct(@RequestBody ProductDTO product) {
            return ResponseEntity.ok("Product Created with ID: "+productServiceFacade.addProduct(product));
        }

        @PostMapping("/addInventory")
        public ResponseEntity<String> addInventory(@RequestBody InventoryDTO inventory) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product ID: "+productServiceFacade.addInventory(inventory)+" Not found");
            }
         

            @PostMapping("/updateProduct")
            public ResponseEntity<String> UpdateProduct(@RequestBody ProductDTO product){
              return ResponseEntity.ok("Product Updated with ID: "+productServiceFacade.UpdateProduct(product));
            }
         

    @PostMapping("/updateInventory")
    public ResponseEntity<String> UpdateInventory(@RequestBody InventoryDTO inventory){
              return ResponseEntity.ok("Inventory Updated with ID: "+productServiceFacade.UpdateInventory(inventory));
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity<String> DeleteProduct(@RequestParam Integer id){
                return ResponseEntity.ok("Product Deleted with ID: "+id+" with Status: "+productServiceFacade.DeleteProduct(id));          
              }
      

    @PostMapping("/deleteInventory")
    public ResponseEntity<String> DeleteInventory(@RequestParam Integer id){
                return ResponseEntity.ok("Inventory Deleted with ID: "+id+" with Status: "+productServiceFacade.DeleteInventory(id));    
                    }
        

    @GetMapping("/getProductsList")
    public ResponseEntity<List<ProductDTO>> getProductsList(){
        return ResponseEntity.ok(productServiceFacade.getProductsList());
    }
    @GetMapping("/getInventoryList")
    public ResponseEntity<List<InventoryDTO>> getInventoryList(){
        return ResponseEntity.ok(productServiceFacade.getInventoryList());
    }
    @GetMapping("/getInventoryByProductId")
    public ResponseEntity<List<InventoryDTO>> getInventoryByProductId(@RequestParam Integer id){ 
          return ResponseEntity.ok(productServiceFacade.getInventoryByProductId(id));
     
    }
     @GetMapping("/getProductPriceByProductID")
    public ResponseEntity<Integer> getProductPriceByProductID(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(productServiceFacade.getProductPriceByProductID(id));
    }
    @GetMapping("/getInventoryAvailablewithProductId")
    public ResponseEntity<Boolean> getInventoryAvailablewithProductId(@RequestParam Integer id){
            return ResponseEntity.ok(productServiceFacade.getInventoryAvailablewithProductId(id));
    }

    @PostMapping("/lockInventoryByInventoryID")
    public ResponseEntity<Boolean> holdInventory(@RequestBody LockOrReleaseInventoryDTO request){
            return ResponseEntity.ok(productServiceFacade.holdInventory(request));
    }
    @PostMapping("/releaseInventoryByInventoryID")
    public ResponseEntity<Boolean> releaseInventory(@RequestBody LockOrReleaseInventoryDTO request){
            return ResponseEntity.ok(productServiceFacade.releaseInventory(request));
    }
    @GetMapping("/getLocationByProductId")
    public ResponseEntity<List<String>> getLocationByProductId(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(productServiceFacade.getLocationByProductId(id));
    }
}
