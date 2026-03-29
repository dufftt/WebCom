package com.duft.shipping_service.Adapters.out.Controller.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.duft.shipping_service.domain.exceptions.BaseDomainException;

@ControllerAdvice
public class ShipmentGlobalExceptionHandler {


    @ExceptionHandler(BaseDomainException.class)
    public ResponseEntity<String> handleRestException(BaseDomainException ex){
        return new ResponseEntity<>("Error: "+ex.getLocalizedMessage(),HttpStatus.valueOf(ex.getErrorCategory()));
    }
}
