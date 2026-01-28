package com.duft.product_service.Ports.out;

import java.util.Optional;

import com.duft.product_service.Domains.Entities.Inventory;

public interface InventoryRepositoryPort {

     //List<Inventory> findAllById(Integer id);
    Optional<Inventory> findById(int id);
    Inventory save(Inventory inventory);
    Boolean DeleteInventoryById(Integer id);
    Inventory UpdateInventory(Inventory inventory);
}
