package com.duft.api_gateway.Adapter.In.Controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

// import com.duft.api_gateway.Adapter.CustomerService.WebDTO.CustomerWebDTO;

import com.duft.api_gateway.Adapter.CustomerService.WebDTO.CustomerWebDTO;
import com.duft.api_gateway.Ports.CustomerPort;


@Controller
public class GraphController {


    private final CustomerPort customerPort;


    public GraphController(CustomerPort customerPort){
        this.customerPort= customerPort;
    }

    @QueryMapping
    public CustomerWebDTO getCustomers(){
        System.out.println("Started Grahql:");
        return customerPort.getCustomers();
    }
}
