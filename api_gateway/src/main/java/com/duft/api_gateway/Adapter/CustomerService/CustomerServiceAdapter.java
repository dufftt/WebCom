package com.duft.api_gateway.Adapter.CustomerService;

import com.duft.api_gateway.Adapter.CustomerService.WebDTO.CustomerWebDTO;
import com.duft.api_gateway.Ports.CustomerPort;

public class CustomerServiceAdapter implements CustomerPort {

    @Override
    public CustomerWebDTO getCustomers() {
        return new CustomerWebDTO(1, "Jack", "234567890", "jack@gmail.com");
    }

}
