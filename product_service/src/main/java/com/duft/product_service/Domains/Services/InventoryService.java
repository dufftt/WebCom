package com.duft.product_service.Domains.Services;

import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Domains.Use_Cases.AddInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.DeleteInventoryUseCase;
import com.duft.product_service.Domains.Use_Cases.UpdateInventoryUseCase;
import com.duft.product_service.Ports.out.InventoryRepositoryPort;


//TODO as there is too much use cases so need to do 2 things 1) move simpler actions inside entities itself 2) create bus pattern
public class InventoryService {

    private final AddInventoryUseCase addInventoryUseCase;
    private final UpdateInventoryUseCase updateInventoryUseCase;
    private final DeleteInventoryUseCase deleteInventoryUseCase;
    private final InventoryRepositoryPort inventoryRepositoryPort;


    

    public InventoryService(AddInventoryUseCase addInventoryUseCase, UpdateInventoryUseCase updateInventoryUseCase,
            DeleteInventoryUseCase deleteInventoryUseCase, InventoryRepositoryPort inventoryRepositoryPort) {
        this.addInventoryUseCase = addInventoryUseCase;
        this.updateInventoryUseCase = updateInventoryUseCase;
        this.deleteInventoryUseCase = deleteInventoryUseCase;
        this.inventoryRepositoryPort = inventoryRepositoryPort;
    }

    public void addInventory(Inventory inventory) {
        // map dto -> input model, then delegate
        addInventoryUseCase.execute(inventory);
    }

    public void updateInventory(Long id, Inventory inventory) {
        updateInventoryUseCase.execute(inventory);
    }

    public void deleteInventory(Integer id) {
        deleteInventoryUseCase.execute(id);
    }

    //TODO add logic to hold inventory using lockquantity of inventory entity where we temporarily holds the quanity while order processes and releases once release method is called

    public void lockInventory(Integer id) {
        Inventory inventory = inventoryRepositoryPort.findById(id).orElse(null);
        if (inventory != null) {
            inventory.setLockedQuantities(inventory.getLockedQuantities() + 1);
            inventoryRepositoryPort.UpdateInventory(inventory);
        }
    }


}
