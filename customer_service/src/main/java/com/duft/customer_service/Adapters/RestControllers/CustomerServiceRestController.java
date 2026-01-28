package com.duft.customer_service.Adapters.RestControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duft.customer_service.Adapters.WebDTOs.CustomerDTO;
import com.duft.customer_service.Entities.Customer;
import com.duft.customer_service.Utils.MaperUtils.MapperUtils;
import com.duft.customer_service.use_cases.AddAddressUseCase;
import com.duft.customer_service.use_cases.AddCustomerUseCase;

@RestController
@RequestMapping("/customerservice")
public class CustomerServiceRestController {

    private AddCustomerUseCase addCustomerUseCase;
    private AddAddressUseCase addAddressUseCase;

    public CustomerServiceRestController(AddCustomerUseCase addCustomerUseCase, AddAddressUseCase addAddressUseCase) {
        this.addCustomerUseCase = addCustomerUseCase;
        this.addAddressUseCase = addAddressUseCase;
    }


    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customer){
            Customer cust = MapperUtils.map(customer, Customer.class);
            Customer createdCustomer = addCustomerUseCase.execute(cust);
            return ResponseEntity.ok("Customer Created with ID: "+createdCustomer.getCustomerID());
    }
}
