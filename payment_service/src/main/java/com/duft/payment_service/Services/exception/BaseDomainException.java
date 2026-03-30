package com.duft.payment_service.Services.exception;

public class BaseDomainException extends RuntimeException {

    private final String errorCategory;

    public BaseDomainException(String message, String errorCategory) {
        super(message);
        this.errorCategory = errorCategory;
    }

    public String getErrorCategory() {
        return errorCategory;
    }

    
}
