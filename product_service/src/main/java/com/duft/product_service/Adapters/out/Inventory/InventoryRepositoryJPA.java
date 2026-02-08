package com.duft.product_service.Adapters.out.Inventory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duft.product_service.Domains.Entities.Inventory;

public interface InventoryRepositoryJPA extends JpaRepository<InventoryEntity, Integer> {

    List<InventoryEntity> getInventoryByProductId(Integer id);

    Optional<List<Inventory>> findAllByProductId(Integer id);

}
