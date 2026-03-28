package com.duft.product_service.Domains.exceptions;

public class BadRequestException extends BaseDomainException {

    public BadRequestException(String message) {
        super(message, "BAD_REQUEST");
       
    }
    

}
