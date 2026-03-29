package com.duft.shipping_service.domain.exceptions;

public class ShipmentNotFoundException extends BaseDomainException {

    public ShipmentNotFoundException(String message) {
        super(message, "NOT_FOUND");
        //TODO Auto-generated constructor stub
    }

}
