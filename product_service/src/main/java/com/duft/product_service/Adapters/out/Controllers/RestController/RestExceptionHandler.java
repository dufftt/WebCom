package com.duft.product_service.Adapters.out.Controllers.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.duft.product_service.Domains.exceptions.BaseDomainException;


@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(BaseDomainException.class)
    public ResponseEntity<String> handleException(BaseDomainException ex){
        return new ResponseEntity<>("Error: "+ex.getMessage(),HttpStatus.valueOf(ex.getErrorCategory()));
    }
}
