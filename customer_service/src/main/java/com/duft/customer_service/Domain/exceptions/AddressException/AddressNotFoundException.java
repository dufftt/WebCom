package com.duft.customer_service.Domain.exceptions.AddressException;

import com.duft.customer_service.Domain.exceptions.BaseDomainException;

public class AddressNotFoundException  extends BaseDomainException{

    public AddressNotFoundException(String message) {
        super(message, "NOT_FOUND");
    }
    

}
