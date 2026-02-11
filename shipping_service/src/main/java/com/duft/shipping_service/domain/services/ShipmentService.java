package com.duft.shipping_service.domain.services;

import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;

import com.duft.shipping_service.Adapters.WebDTO.UpdateStatusDTO;
import com.duft.shipping_service.domain.entities.Shipments;
import com.duft.shipping_service.domain.enums.Carriers;
import com.duft.shipping_service.port.ShipmentRepositoryPort;

public class ShipmentService {

    private final ShipmentRepositoryPort shipmentRepositoryPort;

    public ShipmentService(ShipmentRepositoryPort shipmentRepositoryPort) {
        this.shipmentRepositoryPort = shipmentRepositoryPort;
    }

    public Shipments addShipment(Shipments shipments) {
        //TODO: services will calculate delivey cost, generate track id and decide carrier
        shipments.setDeliveryCost(this.calculateDeliveryCost(shipments.getOrderId(), shipments.getAddress_id()));
        shipments.setTrackId(this.generateTrackID());
        shipments.setCarrier(this.decideCarrier());
        return shipmentRepositoryPort.addShipment(shipments);
        
    }

    private Integer calculateDeliveryCost(Integer orderId,Integer addressId){
        //TODO: make call to product service to get <product_id,address,quantities>
        //TODO: them make simple calculation delivery cost = SUM((delivery_address - product_inventory_address) * quantities)
        return 100; //dummy amount
    }

    private String generateTrackID(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
    // Take a portion (e.g., first 6 hex characters)
    String shortUuid = uuid.substring(0, 6);
    // Attempt to convert to a decimal number (might not always be 6 digits)
    try {
        long decimalValue = Long.parseLong(shortUuid, 16);
        String randomValue = String.format("%06d", decimalValue % 1000000); // Ensure 6 digits with leading zeros
        return "WEbCoM"+randomValue;
    } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
    }
}

private Integer decideCarrier(){
    //TODO: based on current availablity of carrier partner need to decide carrier partner

    return Carriers.BLUE_DART.getId();
}

public List<Shipments> getAllShipments() {
    return shipmentRepositoryPort.getAllShipments();
   
}

public Shipments trackShipment(String trackId) {
    return shipmentRepositoryPort.trackShipment(trackId);

}



public Boolean updateShipmentStatus(List<UpdateStatusDTO> updateStatusDTO) {
    shipmentRepositoryPort.updateShipmentStatus(updateStatusDTO);
    return true;
}


}
