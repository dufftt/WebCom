package com.duft.product_service.Domains.exceptions;

public class InventoryNotFoundException extends BaseDomainException {

    public InventoryNotFoundException(String message) {
        super(message, "NOT_FOUND");
        //TODO Auto-generated constructor stub
    }

}
