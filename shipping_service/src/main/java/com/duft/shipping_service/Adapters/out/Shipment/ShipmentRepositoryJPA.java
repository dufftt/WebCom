package com.duft.shipping_service.Adapters.out.Shipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface ShipmentRepositoryJPA extends JpaRepository<ShipmentEntity, Integer>{

    ShipmentEntity findByTrackId(String trackId);

    @Transactional
    @Modifying
    @Query("UPDATE ShipmentEntity s SET s.status = :status WHERE s.shipmentId = :shipmentId")
    void updateShipmentStatus(@Param("shipmentId") Integer shipmentId,@Param("status") Boolean status);
}
