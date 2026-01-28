package com.duft.product_service.Domains.Use_Cases;

import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Ports.out.InventoryRepositoryPort;

public class DeleteInventoryUseCase {

    private final InventoryRepositoryPort inventoryRepositoryPort;

    public DeleteInventoryUseCase(InventoryRepositoryPort inventoryRepositoryPort) {
        this.inventoryRepositoryPort = inventoryRepositoryPort;
    }

    public Boolean execute(Integer id){
        Inventory exisitngInventory = inventoryRepositoryPort.findById(id).orElse(null);
        if(exisitngInventory!=null){
            Boolean deleteStatus = inventoryRepositoryPort.DeleteInventoryById(id);
            return deleteStatus;
        }else{
            return false;
        }
    }
}
