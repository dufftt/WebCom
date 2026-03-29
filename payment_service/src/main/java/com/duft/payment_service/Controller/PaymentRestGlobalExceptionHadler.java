package com.duft.payment_service.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.duft.payment_service.Services.exception.BaseDomainException;

@ControllerAdvice
public class PaymentRestGlobalExceptionHadler {

   @ExceptionHandler(BaseDomainException.class)
    public ResponseEntity<String> handleRestException(BaseDomainException ex){
        return new ResponseEntity<>("Error: "+ex.getLocalizedMessage(),HttpStatus.valueOf(ex.getErrorCategory()));
    }
}
