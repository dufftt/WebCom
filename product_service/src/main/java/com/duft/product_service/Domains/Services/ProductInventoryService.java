package com.duft.product_service.Domains.Services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Domains.Entities.Product;
import com.duft.product_service.Domains.Use_Cases.AddInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.AddProductUseCase;
import com.duft.product_service.Domains.Use_Cases.DeleteInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.DeleteProductUseCase;
import com.duft.product_service.Domains.Use_Cases.UpdateInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.UpdateProductUseCase;
import com.duft.product_service.Ports.out.InventoryRepositoryPort;
import com.duft.product_service.Ports.out.ProductRepositoryPort;

public class ProductInventoryService {

    private final AddProductUseCase addProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final AddInventoryUseCase addInventoryUseCase;
    private final UpdateInventoryUseCase updateInventoryUseCase;
    private final DeleteInventoryUseCase deleteInventoryUseCase;
    private final ProductRepositoryPort productRepositoryPort;
    private final InventoryRepositoryPort inventoryRepositoryPort;

    Logger logger = LoggerFactory.getLogger(ProductInventoryService.class);

    public ProductInventoryService(AddProductUseCase addProductUseCase, UpdateProductUseCase updateProductUseCase,
                                   DeleteProductUseCase deleteProductUseCase, AddInventoryUseCase addInventoryUseCase,
                                   UpdateInventoryUseCase updateInventoryUseCase, DeleteInventoryUseCase deleteInventoryUseCase,
                                   ProductRepositoryPort productRepositoryPort, InventoryRepositoryPort inventoryRepositoryPort) {
        this.addProductUseCase = addProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.addInventoryUseCase = addInventoryUseCase;
        this.updateInventoryUseCase = updateInventoryUseCase;
        this.deleteInventoryUseCase = deleteInventoryUseCase;
        this.productRepositoryPort = productRepositoryPort;
        this.inventoryRepositoryPort = inventoryRepositoryPort;
    }

    // --- Product Methods ---

    public List<Product> getProductList(){
        return productRepositoryPort.getAllProducts();
    }

    public List<Inventory> getInventoryList(){
        return inventoryRepositoryPort.getAllInventories();
    }

    public Product addProduct(Product product) {
        return addProductUseCase.execute(product);
    }

    public Product updateProduct(Product product) {
        if(productRepositoryPort.findById(product.getProductId()).isPresent()){
            return updateProductUseCase.execute(product);
        }else{
            return null;
        }
        
    }

    public Boolean deleteProduct(Integer id) {
        return deleteProductUseCase.execute(id);
    }

    // --- Inventory Methods ---

    public Inventory addInventory(Inventory inventory) {
        // Check if product exists before adding inventory
        if (productRepositoryPort.findById(inventory.getProductId()).isPresent()) {
            inventory.setLockedQuantities(0);
            return addInventoryUseCase.execute(inventory);
        } else {
            return null; // Or throw exception
        }
    }

    public Inventory updateInventory(Inventory inventory) {
        Optional<Inventory> inv = inventoryRepositoryPort.findById(inventory.getInventoryId());
        if(inv.isPresent() && inv.get().getProductId().equals(inventory.getProductId())){
            if(inventory.getLockedQuantities()!=null){
                return updateInventoryUseCase.execute(inventory);
            }else{
                inventory.setLockedQuantities(inv.get().getLockedQuantities());
                return updateInventoryUseCase.execute(inventory);
            }
            
        }else{
            return null;
        }
        
    }

    public Boolean deleteInventory(Integer id) {
        if(inventoryRepositoryPort.findById(id).isPresent()){
            return deleteInventoryUseCase.execute(id);
        }else{
            return false;
        }
        
    }

    public Boolean lockInventory(Integer id, Integer quantity) {
        Optional<Inventory> inventoryOpt = inventoryRepositoryPort.findById(id);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            inventory.lockQuantity(quantity);
            Inventory inv = inventoryRepositoryPort.UpdateInventory(inventory);
            if(inv!=null){
                return true;
            }
        }return false;
    }

    public Boolean releaseInventory(Integer id, Integer quantity){
        Optional<Inventory> inventoryOpt = inventoryRepositoryPort.findById(id);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            inventory.releaseInventory(quantity);
            try {
                Inventory inv = inventoryRepositoryPort.UpdateInventory(inventory);
                if(inv!=null){
                return true;
            }
            } catch (Exception e) {
                logger.error("Constraint failure: "+e.getMessage());
                return false;
            }            
        }return false;
    }

    public List<Inventory> getInventoryByProductId(Integer id) {
        return inventoryRepositoryPort.findByProductID(id);
    }

    public Boolean getInventoryAvailablewithProductId(Integer id){
       return inventoryRepositoryPort.getInventoryAvailablewithProductId(id);
    }

    public List<String> getLocationsByProductId(Integer id){
        return inventoryRepositoryPort.getLocationByProductId(id);
    }

    public Integer getProductPriceByProductId(Integer id){
        return productRepositoryPort.getProductPriceByProductID(id);
    }
}