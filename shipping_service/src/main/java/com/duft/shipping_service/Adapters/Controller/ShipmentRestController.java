package com.duft.shipping_service.Adapters.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duft.shipping_service.Adapters.WebDTO.ShipmentRequestDTO;
import com.duft.shipping_service.Adapters.WebDTO.ShipmentResponseDTO;
import com.duft.shipping_service.Adapters.WebDTO.UpdateStatusDTO;
import com.duft.shipping_service.domain.entities.Shipments;
import com.duft.shipping_service.domain.services.ShipmentService;

@RestController
@RequestMapping("/shipments")
public class ShipmentRestController {

    public final ShipmentService shipmentService;

    public ShipmentRestController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }
    @PostMapping("/startShipping")
    public ResponseEntity<ShipmentResponseDTO> startShipping(@RequestBody ShipmentRequestDTO shipmentRequestDTO){
        //TODO: get order id, customer id, address id from shipment request DTO and then make call to customer service to get address and customer details
        //TODO: then we calculate shipment cost and start shipment process
        Shipments shipment = shipmentService.addShipment(new Shipments(null, shipmentRequestDTO.getOrderId(), shipmentRequestDTO.getCustomerId(), shipmentRequestDTO.getAddressId(),null,false,null,null));
        return ResponseEntity.ok(new ShipmentResponseDTO(shipment.getOrderId(), shipment.getCustomer_id(), shipment.getAddress_id(), shipment.getCarrier(), shipment.getStatus(), shipment.getTrackId(), shipment.getDeliveryCost()));
    }

    //TODO: getshipment status(track id) , updateshipmentstatus, getAllShipments 

    @GetMapping("/getAllShipments")
    public ResponseEntity<List<ShipmentResponseDTO>> getAllShipments(){
        List<Shipments> shipments = shipmentService.getAllShipments();
        List<ShipmentResponseDTO> shipmentResponseDTOs = shipments.stream().map(shipment -> new ShipmentResponseDTO(shipment.getOrderId(), shipment.getCustomer_id(), shipment.getAddress_id(),shipment.getCarrier(),shipment.getStatus(),shipment.getTrackId(),shipment.getDeliveryCost())).toList();
        return ResponseEntity.ok(shipmentResponseDTOs);
    }

    @GetMapping("/trackShipment")
    public ResponseEntity<ShipmentResponseDTO> trackShipment(@RequestParam String trackId){
        Shipments shipment = shipmentService.trackShipment(trackId);
        return ResponseEntity.ok(new ShipmentResponseDTO(shipment.getOrderId(),shipment.getCustomer_id(),shipment.getAddress_id(),shipment.getCarrier(),shipment.getStatus(),shipment.getTrackId(),shipment.getDeliveryCost()));
    }
    @PostMapping("/updateShipmentStatus")
    public ResponseEntity<Boolean> updateShipmentStatus(@RequestBody List<UpdateStatusDTO> updateStatusDTO){

        return ResponseEntity.ok(shipmentService.updateShipmentStatus(updateStatusDTO));
    }


}
