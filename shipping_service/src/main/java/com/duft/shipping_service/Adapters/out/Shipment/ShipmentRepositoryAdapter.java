package com.duft.shipping_service.Adapters.out.Shipment;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.duft.shipping_service.Adapters.WebDTO.UpdateStatusDTO;
import com.duft.shipping_service.domain.entities.Shipments;
import com.duft.shipping_service.port.ShipmentRepositoryPort;

@Repository
public class ShipmentRepositoryAdapter implements ShipmentRepositoryPort {

    private ShipmentRepositoryJPA shipmentRepositoryJPA;

    public ShipmentRepositoryAdapter(ShipmentRepositoryJPA shipmentRepositoryJPA) {
        this.shipmentRepositoryJPA = shipmentRepositoryJPA;
    }


    @Override
    public Shipments addShipment(Shipments shipments) {
        ShipmentEntity shipmentEntity = shipmentRepositoryJPA.save(new ShipmentEntity(null, shipments.getOrderId(), shipments.getCustomer_id(), shipments.getAddress_id(), shipments.getCarrier(), false, shipments.getTrackId(), shipments.getDeliveryCost()));
        return new Shipments(shipmentEntity.getShipmentId(), shipmentEntity.getOrderId(), shipmentEntity.getCustomer_id(), shipmentEntity.getAddress_id(), shipmentEntity.getCarrier(), shipmentEntity.getStatus(), shipmentEntity.getTrackId(), shipmentEntity.getDeliveryCost());
    }

    @Override
    public List<Shipments> getAllShipments() {
        List<ShipmentEntity> shipmentEntityList = shipmentRepositoryJPA.findAll();
        List<Shipments> shipmentsList = shipmentEntityList.stream().map(shipmentEntity -> new Shipments(shipmentEntity.getShipmentId(),shipmentEntity.getOrderId(),shipmentEntity.getCustomer_id(),shipmentEntity.getAddress_id(),
        shipmentEntity.getCarrier(),shipmentEntity.getStatus(),shipmentEntity.getTrackId(),shipmentEntity.getDeliveryCost())).toList();
        return shipmentsList;
    }

    @Override
    public Shipments trackShipment(String trackId) {
       ShipmentEntity shipmentEntity = shipmentRepositoryJPA.findByTrackId(trackId);
       return new Shipments(shipmentEntity.getShipmentId(),shipmentEntity.getOrderId(),shipmentEntity.getCustomer_id(),shipmentEntity.getAddress_id(),
        shipmentEntity.getCarrier(),shipmentEntity.getStatus(),shipmentEntity.getTrackId(),shipmentEntity.getDeliveryCost());
    }

    @Override
    public void updateShipmentStatus(List<UpdateStatusDTO> updateStatusDTO) {
        for(UpdateStatusDTO updateStatus : updateStatusDTO){
            shipmentRepositoryJPA.updateShipmentStatus(updateStatus.getShipmentId(), updateStatus.getStatus());
        }
    }

}
