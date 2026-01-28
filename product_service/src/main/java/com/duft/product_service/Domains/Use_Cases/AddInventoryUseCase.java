package com.duft.product_service.Domains.Use_Cases;

import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Ports.out.InventoryRepositoryPort;

public class AddInventoryUseCase {

    private final InventoryRepositoryPort inventoryRepositoryPort;

    public AddInventoryUseCase(InventoryRepositoryPort inventoryRepositoryPort) {
        this.inventoryRepositoryPort = inventoryRepositoryPort;
    }

    public Inventory execute(Inventory inventory){
        Inventory inv = inventoryRepositoryPort.save(inventory);
        return inv;
    }
}
