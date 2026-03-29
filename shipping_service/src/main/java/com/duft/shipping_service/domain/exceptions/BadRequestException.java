package com.duft.shipping_service.domain.exceptions;

public class BadRequestException extends BaseDomainException {

    public BadRequestException(String message) {
        super(message, "BAD_REQUEST");
        //TODO Auto-generated constructor stub
    }

}
