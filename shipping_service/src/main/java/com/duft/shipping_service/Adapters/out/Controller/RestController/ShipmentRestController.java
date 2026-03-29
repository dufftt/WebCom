package com.duft.shipping_service.Adapters.out.Controller.RestController;

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
import com.duft.shipping_service.Adapters.out.Controller.ShipmentServiceFacade;


@RestController
@RequestMapping("/shipments")
public class ShipmentRestController {

    public final ShipmentServiceFacade serviceFacade;

    public ShipmentRestController(ShipmentServiceFacade serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    @PostMapping("/startShipping")
    public ResponseEntity<ShipmentResponseDTO> startShipping(@RequestBody ShipmentRequestDTO shipmentRequestDTO){
        return ResponseEntity.ok(serviceFacade.startShipping(shipmentRequestDTO));
    }

    //TODO: getshipment status(track id) , updateshipmentstatus, getAllShipments 

    @GetMapping("/getAllShipments")
    public ResponseEntity<List<ShipmentResponseDTO>> getAllShipments(){
         return ResponseEntity.ok(serviceFacade.getAllShipments());
    }

    @GetMapping("/trackShipment")
    public ResponseEntity<ShipmentResponseDTO> trackShipment(@RequestParam String trackId){
         return ResponseEntity.ok(serviceFacade.trackShipment(trackId));
    }
    @PostMapping("/updateShipmentStatus")
    public ResponseEntity<Boolean> updateShipmentStatus(@RequestBody List<UpdateStatusDTO> updateStatusDTO){

        return ResponseEntity.ok(serviceFacade.updateShipmentStatus(updateStatusDTO));
    }

    // @GetMapping("/shippingCharge")
    // public ResponseEntity<String> calculateShippingCost(){
    //     String cost = shipmentService.calculateShippingCost(2,'f').toString();
    //     return ResponseEntity.ok(cost);
    // }

}
