package com.duft.product_service.Adapters.out.Inventory;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.duft.product_service.Domains.Entities.Inventory;
import com.duft.product_service.Ports.out.InventoryRepositoryPort;
import com.duft.product_service.Utils.MapperUtils;
@Repository
public class InventoryRepositoryAdapter implements InventoryRepositoryPort {

   private final InventoryRepositoryJPA inventoryRepositoryJPA;
    Logger logger = LoggerFactory.getLogger(InventoryRepositoryAdapter.class);

    public InventoryRepositoryAdapter(InventoryRepositoryJPA inventoryRepositoryJPA) {
        this.inventoryRepositoryJPA = inventoryRepositoryJPA;
    }

     //mapping from customer entity to customer
    private Inventory toDomain(InventoryEntity e) {
        return MapperUtils.map(e, Inventory.class);
    }
    //mapping from customer to customer entity
    private InventoryEntity toEntity(Inventory c) {
        return MapperUtils.map(c, InventoryEntity.class);
    }

    //  @Override
    // public List<Inventory> findAllById(Integer id) {
    //   return MapperUtils.mapList(InventoryRepositoryJPA.findAllById(Arrays.asList(id)), Inventory.class);
    // }
    @Override
    public Optional<Inventory> findById(int id) {
       return inventoryRepositoryJPA.findById(id).map(this::toDomain);
    }

    @Override
    public Inventory save(Inventory inventory) {
        InventoryEntity e = toEntity(inventory);
        InventoryEntity saved = inventoryRepositoryJPA.saveAndFlush(e);
        return toDomain(saved);
    }

    @Override
    public Boolean DeleteInventoryById(Integer id) {
       try {
            inventoryRepositoryJPA.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete: "+e);
            return false;
        }
        return true;
    }

    @Override
    public Inventory UpdateInventory(Inventory inventory) {
         InventoryEntity e = toEntity(inventory);
        InventoryEntity saved = inventoryRepositoryJPA.saveAndFlush(e);
        logger.info("Updating Inventory: "+saved.toString());
        return toDomain(saved);
    }

}
