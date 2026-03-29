package com.duft.shipping_service.Adapters.out.persistence.InitalizeData;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.duft.shipping_service.domain.entities.Shipments;
import com.duft.shipping_service.domain.enums.Carriers;
import com.duft.shipping_service.domain.services.ShipmentService;

import jakarta.annotation.PostConstruct;

@Configuration
public class ShipmentData {

    private ShipmentService shipmentService;

    public ShipmentData(ShipmentService shipmentService){
        this.shipmentService = shipmentService;
    }

    @PostConstruct
    public void InitalizeData(){
        List<Shipments> listOfShipment = new ArrayList<>();
        listOfShipment.add(new Shipments(1, 1, 1, 1, Carriers.BLUE_DART.getId(), false, "1234", 25));
        listOfShipment.add(new Shipments(1, 1, 1, 1, Carriers.DHL.getId(), false, "1235", 20));
        listOfShipment.add(new Shipments(1, 1, 1, 1, Carriers.FEDEX.getId(), false, "1236", 35));
        listOfShipment.add(new Shipments(1, 1, 1, 1, Carriers.INDIAN_POST.getId(), false, "1237", 45));
        listOfShipment.add(new Shipments(1, 1, 1, 1, Carriers.UPS.getId(), false, "1237", 95));
        List<Shipments> shipments = shipmentService.addAllShipments(listOfShipment);
    }
}
