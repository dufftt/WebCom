package com.duft.product_service.Ports.out;

import java.util.List;
import java.util.Optional;

import com.duft.product_service.Domains.Entities.Inventory;

public interface InventoryRepositoryPort {

     //List<Inventory> findAllById(Integer id);
    Optional<Inventory> findById(int id);
    Inventory save(Inventory inventory);
    Boolean DeleteInventoryById(Integer id);
    Inventory UpdateInventory(Inventory inventory);
    List<Inventory> getInventoryByProductId(Integer id);
    Boolean getInventoryAvailablewithProductId(Integer id);
    void reduceInventoryQuantityByInventoryID(Integer id);
    Boolean lockInventoryByInventoryID(Integer id);
    Boolean releaseInventoryByInventoryID(Integer id);
    List<String> getLocationByProductId(Integer id);
    List<Inventory> getAllInventories();
    List<Inventory> findByProductID(Integer id);
}
