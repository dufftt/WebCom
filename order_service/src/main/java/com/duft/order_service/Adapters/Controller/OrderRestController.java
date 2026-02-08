package com.duft.order_service.Adapters.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duft.order_service.Adapters.WebDTO.OrderRequestDTO;

@RestController
@RequestMapping("/orderService")
public class OrderRestController {


    public ResponseEntity<Boolean> buy(@RequestBody OrderRequestDTO orderRequest){
        //TODO: first we will add this in order & orderItem table after calculating prices etc then pass call to payment service
        return null;
    }
}
