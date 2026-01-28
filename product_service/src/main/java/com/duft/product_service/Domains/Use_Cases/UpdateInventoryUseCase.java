package com.duft.product_service.Domains.Use_Cases;

import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Ports.out.InventoryRepositoryPort;

public class UpdateInventoryUseCase {


    private final InventoryRepositoryPort inventoryRepositoryPort;

    public UpdateInventoryUseCase(InventoryRepositoryPort inventoryRepositoryPort) {
        this.inventoryRepositoryPort = inventoryRepositoryPort;
    }

    
    public Inventory execute(Inventory inventory){
        Inventory exisitngInventory = inventoryRepositoryPort.findById(inventory.getInventoryId()).orElse(null);
        if(exisitngInventory!=null){
            inventory.setInventoryId(exisitngInventory.getInventoryId());
            Inventory inv = inventoryRepositoryPort.UpdateInventory(inventory);
            return inv;
        }else{
            return null;
        }
    }
}
