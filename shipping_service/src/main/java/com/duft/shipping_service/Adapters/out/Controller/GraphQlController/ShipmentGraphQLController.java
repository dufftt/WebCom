package com.duft.shipping_service.Adapters.out.Controller.GraphQlController;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.duft.shipping_service.Adapters.WebDTO.ShipmentRequestDTO;
import com.duft.shipping_service.Adapters.WebDTO.ShipmentResponseDTO;
import com.duft.shipping_service.Adapters.WebDTO.UpdateStatusDTO;
import com.duft.shipping_service.Adapters.out.Controller.ShipmentServiceFacade;

@Controller
public class ShipmentGraphQLController {

     public final ShipmentServiceFacade serviceFacade;

    public ShipmentGraphQLController(ShipmentServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @MutationMapping
    public ShipmentResponseDTO startShipping(@Argument ShipmentRequestDTO shipmentRequestDTO){
        return serviceFacade.startShipping(shipmentRequestDTO);
    }

    //TODO: getshipment status(track id) , updateshipmentstatus, getAllShipments 

    @QueryMapping
    public List<ShipmentResponseDTO> getAllShipments(){
         return serviceFacade.getAllShipments();
    }

    @QueryMapping
    public ShipmentResponseDTO trackShipment(@Argument String trackId){
         return serviceFacade.trackShipment(trackId);
    }
    @MutationMapping
    public Boolean updateShipmentStatus(@Argument List<UpdateStatusDTO> updateStatusDTO){
        return serviceFacade.updateShipmentStatus(updateStatusDTO);
    }
}
