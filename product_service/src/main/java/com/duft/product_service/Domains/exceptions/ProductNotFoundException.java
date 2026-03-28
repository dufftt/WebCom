package com.duft.product_service.Domains.exceptions;

public class ProductNotFoundException extends BaseDomainException {

    public ProductNotFoundException(String message) {
        super(message, "NOT_FOUND");
        //TODO Auto-generated constructor stub
    }

}
