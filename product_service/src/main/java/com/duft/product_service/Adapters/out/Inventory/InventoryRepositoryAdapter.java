package com.duft.product_service.Adapters.out.Inventory;

import java.util.List;
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
        e.setInventoryId(null);
        logger.info("Saving Inventory: "+e.toString());
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

    @Override
    public List<Inventory> getInventoryByProductId(Integer id) {
        return MapperUtils.mapList(inventoryRepositoryJPA.getInventoryByProductId(id), Inventory.class);
    }

    //TODO rewrite this adapter later to only returns Inventory based on product id and then let domain make the decision whether any location has quantity greater than 0 or not
    @Override
    public Boolean getInventoryAvailablewithProductId(Integer id) {
        return inventoryRepositoryJPA.getInventoryByProductId(id).stream().anyMatch(i -> i.getQuantity() > 0);
    }
    //TODO rewrite this adapter later such that it will only update inventory and reduction happened inside domain layer
    @Override
    public void reduceInventoryQuantityByInventoryID(Integer id) {
         inventoryRepositoryJPA.findById(id).ifPresent(inventoryEntity -> {
            inventoryEntity.setQuantity(inventoryEntity.getQuantity() - 1);
            inventoryRepositoryJPA.save(inventoryEntity);
         });
        
    }

    @Override
    public Boolean lockInventoryByInventoryID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean releaseInventoryByInventoryID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //TODO rewrite this adapter to only return inventory and then perform location extraction in domain layer
    @Override
    public List<String> getLocationByProductId(Integer id) {
       return inventoryRepositoryJPA.getInventoryByProductId(id).stream().map(InventoryEntity::getLocation).toList();
    }

    @Override
    public List<Inventory> getAllInventories() {
        return MapperUtils.mapList(inventoryRepositoryJPA.findAll(), Inventory.class);
    }

    @Override
    public List<Inventory> findByProductID(Integer id) {
        return MapperUtils.mapList(inventoryRepositoryJPA.getInventoryByProductId(id), Inventory.class);
    }

}
