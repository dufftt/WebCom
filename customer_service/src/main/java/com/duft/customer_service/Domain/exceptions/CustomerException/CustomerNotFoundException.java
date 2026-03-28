package com.duft.customer_service.Domain.exceptions.CustomerException;

import com.duft.customer_service.Domain.exceptions.BaseDomainException;

public class CustomerNotFoundException extends BaseDomainException {

    public CustomerNotFoundException(String message) {
        super(message, "NOT_FOUND");
    }

}
