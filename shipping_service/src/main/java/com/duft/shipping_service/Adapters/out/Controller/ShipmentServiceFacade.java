package com.duft.shipping_service.Adapters.out.Controller;

import java.util.List;


import org.springframework.web.bind.annotation.RequestBody;
import com.duft.shipping_service.Adapters.WebDTO.ShipmentRequestDTO;
import com.duft.shipping_service.Adapters.WebDTO.ShipmentResponseDTO;
import com.duft.shipping_service.Adapters.WebDTO.UpdateStatusDTO;
import com.duft.shipping_service.domain.entities.Shipments;
import com.duft.shipping_service.domain.exceptions.BadRequestException;
import com.duft.shipping_service.domain.exceptions.ShipmentNotFoundException;
import com.duft.shipping_service.domain.services.ShipmentService;

public class ShipmentServiceFacade {

     public final ShipmentService shipmentService;

    public ShipmentServiceFacade(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }
  
    public ShipmentResponseDTO startShipping(ShipmentRequestDTO shipmentRequestDTO){
        if(!shipmentRequestDTO.validated()){
            throw new BadRequestException("Invalid Shipment Request");
        }
        //TODO: get order id, customer id, address id from shipment request DTO and then make call to customer service to get address and customer details
        //TODO: then we calculate shipment cost and start shipment process
        Shipments shipment = shipmentService.addShipment(new Shipments(null, shipmentRequestDTO.getOrderId(), shipmentRequestDTO.getCustomerId(), shipmentRequestDTO.getAddressId(),null,false,null,null));
        return new ShipmentResponseDTO(shipment.getOrderId(), shipment.getCustomer_id(), shipment.getAddress_id(), shipment.getCarrier(), shipment.getStatus(), shipment.getTrackId(), shipment.getDeliveryCost());
    }

    //TODO: getshipment status(track id) , updateshipmentstatus, getAllShipments 

  
    public List<ShipmentResponseDTO> getAllShipments(){
        List<Shipments> shipments = shipmentService.getAllShipments();
        if(shipments.isEmpty()){
            throw new ShipmentNotFoundException("No Shipment Available");
        }
        List<ShipmentResponseDTO> shipmentResponseDTOs = shipments.stream().map(shipment -> new ShipmentResponseDTO(shipment.getOrderId(), shipment.getCustomer_id(), shipment.getAddress_id(),shipment.getCarrier(),shipment.getStatus(),shipment.getTrackId(),shipment.getDeliveryCost())).toList();
        return shipmentResponseDTOs;
    }


    public ShipmentResponseDTO trackShipment(String trackId){
        Shipments shipment = shipmentService.trackShipment(trackId);
        if(shipment==null){
            throw new ShipmentNotFoundException("Shipment with track id: "+trackId+ " Not found");
        }
        return new ShipmentResponseDTO(shipment.getOrderId(),shipment.getCustomer_id(),shipment.getAddress_id(),shipment.getCarrier(),shipment.getStatus(),shipment.getTrackId(),shipment.getDeliveryCost());
    }
 
    public Boolean updateShipmentStatus(@RequestBody List<UpdateStatusDTO> updateStatusDTO){

        Boolean status =  shipmentService.updateShipmentStatus(updateStatusDTO);
        if(!status){
            throw new ShipmentNotFoundException("Unable to Update Status");
        }
        return status;
    }

}
