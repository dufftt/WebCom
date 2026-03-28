package com.duft.api_gateway.Adapter.In.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duft.api_gateway.Adapter.CustomerService.WebDTO.CustomerWebDTO;
import com.duft.api_gateway.Ports.CustomerPort;


@RestController
@RequestMapping("/rest")
public class ApiGatewayRestController {

      private final CustomerPort customerPort;

    public ApiGatewayRestController(CustomerPort customerPort){
        this.customerPort= customerPort;
    }

    @GetMapping("/getCustomer")
    public CustomerWebDTO getCustomers(){
        return customerPort.getCustomers();
    }
}
