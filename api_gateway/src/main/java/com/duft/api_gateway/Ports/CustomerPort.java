package com.duft.api_gateway.Ports;

import com.duft.api_gateway.Adapter.CustomerService.WebDTO.CustomerWebDTO;

public interface CustomerPort {

    CustomerWebDTO getCustomers();

}
