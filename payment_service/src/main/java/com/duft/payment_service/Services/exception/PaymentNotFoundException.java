package com.duft.payment_service.Services.exception;

public class PaymentNotFoundException extends BaseDomainException {

    public PaymentNotFoundException(String message) {
        super(message, "NOT_FOUND");
        //TODO Auto-generated constructor stub
    }


}
