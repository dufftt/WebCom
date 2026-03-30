package com.duft.product_service.Adapters.out.Controllers.GraphQlController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


import com.duft.product_service.Adapters.WebDTO.InventoryDTO;
import com.duft.product_service.Adapters.WebDTO.LockOrReleaseInventoryDTO;
import com.duft.product_service.Adapters.WebDTO.ProductDTO;
import com.duft.product_service.Adapters.out.Controllers.ProductServiceFacade;
import com.duft.product_service.Adapters.out.Controllers.RestController.ProductRestController;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ProductGraphQlController {

    private final ProductServiceFacade productServiceFacade;
   Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    private final ObjectMapper mapper = new ObjectMapper();

   
    public ProductGraphQlController(ProductServiceFacade productServiceFacade) {
        this.productServiceFacade = productServiceFacade;
    }

        @MutationMapping
        public String addProduct(@Argument ProductDTO product) {
            return productServiceFacade.addProduct(product);
        }

        @MutationMapping
        public String addInventory(@Argument InventoryDTO inventory) {
                return productServiceFacade.addInventory(inventory)+" Not found";
            }
         

            @MutationMapping
            public String UpdateProduct(@Argument ProductDTO product){
              return productServiceFacade.UpdateProduct(product);
            }
         

    @MutationMapping
    public String UpdateInventory(@Argument InventoryDTO inventory){
              return productServiceFacade.UpdateInventory(inventory);
    }

    @MutationMapping
    public String DeleteProduct(@Argument Integer id){
                return productServiceFacade.DeleteProduct(id);          
              }
      

    @MutationMapping
    public String DeleteInventory(@Argument Integer id){
                return productServiceFacade.DeleteInventory(id);    
          }
        

    @QueryMapping
    public List<ProductDTO> getProductsList(){
        return productServiceFacade.getProductsList();
    }
    @QueryMapping
    public List<InventoryDTO> getInventoryList(){
        return productServiceFacade.getInventoryList();
    }
    @QueryMapping
    public List<InventoryDTO> getInventoryByProductId(@Argument Integer id){ 
          return productServiceFacade.getInventoryByProductId(id);
     
    }
       @QueryMapping
    public ProductDTO getProductById(@Argument Integer id) {
        return productServiceFacade.getProduct(id);
    }
     @QueryMapping
    public Integer getProductPriceByProductID(@Argument Integer id){
        return productServiceFacade.getProductPriceByProductID(id);
    }
    @QueryMapping
    public Boolean getInventoryAvailablewithProductId(@Argument Integer id){
            return productServiceFacade.getInventoryAvailablewithProductId(id);
    }

    @MutationMapping
    public Boolean holdInventory(@Argument LockOrReleaseInventoryDTO request){
            return productServiceFacade.holdInventory(request);
    }
    @MutationMapping
    public Boolean releaseInventory(@Argument LockOrReleaseInventoryDTO request){
            return productServiceFacade.releaseInventory(request);
    }
    @QueryMapping
    public List<String> getLocationByProductId(@Argument Integer id){
        return productServiceFacade.getLocationByProductId(id);
    }

    @EntityMapping
    public ProductDTO productDTO(@Argument Integer productId){
        return productServiceFacade.getProduct(productId);
    }

    @EntityMapping
    public InventoryDTO inventoryDTO(@Argument Integer inventoryId){
        return productServiceFacade.getInventory(inventoryId);
    }

}
