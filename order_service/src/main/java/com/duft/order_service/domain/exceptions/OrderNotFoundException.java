package com.duft.order_service.domain.exceptions;

public class OrderNotFoundException extends BaseDomainException{

    public OrderNotFoundException(String message) {
        super(message, "NOT_FOUND");
        //TODO Auto-generated constructor stub
    }

}
