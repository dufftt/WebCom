package com.duft.order_service.Adapters.out.Controller.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.duft.order_service.domain.exceptions.BaseDomainException;

@ControllerAdvice
public class OrderRestExceptionHandler {

    @ExceptionHandler(BaseDomainException.class)
    public ResponseEntity<String> handleException(BaseDomainException ex){
        return new ResponseEntity<>("Error: "+ex.getMessage(),HttpStatus.valueOf(ex.getErrorCategory()));
    }
}
