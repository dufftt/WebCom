package com.duft.shipping_service.port;

import java.util.List;

import com.duft.shipping_service.Adapters.WebDTO.UpdateStatusDTO;
import com.duft.shipping_service.domain.entities.Shipments;

public interface ShipmentRepositoryPort {

    Shipments addShipment(Shipments shipments);

    List<Shipments> getAllShipments();

    Shipments trackShipment(String trackId);

    void updateShipmentStatus(List<UpdateStatusDTO> updateStatusDTO);

}
