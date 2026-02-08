package com.duft.product_service.Adapters.out.Inventory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepositoryJPA extends JpaRepository<InventoryEntity, Integer> {

    List<InventoryEntity> getInventoryByProductId(Integer id);

}
