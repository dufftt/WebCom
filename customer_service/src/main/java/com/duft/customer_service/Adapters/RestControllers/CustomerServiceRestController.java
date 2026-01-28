package com.duft.customer_service.Adapters.RestControllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duft.customer_service.Adapters.WebDTOs.AddressDTO;
import com.duft.customer_service.Adapters.WebDTOs.CustomerDTO;
import com.duft.customer_service.Domain.Entities.Address;
import com.duft.customer_service.Domain.Entities.Customer;
import com.duft.customer_service.Domain.use_cases.AddAddressUseCase;
import com.duft.customer_service.Domain.use_cases.AddCustomerUseCase;
import com.duft.customer_service.Domain.use_cases.DeleteAddressUseCase;
import com.duft.customer_service.Domain.use_cases.UpdateAddressUseCase;
import com.duft.customer_service.Utils.MaperUtils.MapperUtils;

@RestController
@RequestMapping("/customerservice")
public class CustomerServiceRestController {

    Logger logger
        = LoggerFactory.getLogger(CustomerServiceRestController.class);
    private final AddCustomerUseCase addCustomerUseCase;
    private final AddAddressUseCase addAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;

    public CustomerServiceRestController(AddCustomerUseCase addCustomerUseCase, AddAddressUseCase addAddressUseCase,
            UpdateAddressUseCase updateAddressUseCase, DeleteAddressUseCase deleteAddressUseCase) {
        this.addCustomerUseCase = addCustomerUseCase;
        this.addAddressUseCase = addAddressUseCase;
        this.updateAddressUseCase = updateAddressUseCase;
        this.deleteAddressUseCase = deleteAddressUseCase;
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customer){
            logger.info("Entered api: "+customer);
            Customer cust = MapperUtils.map(customer, Customer.class);
            Customer createdCustomer = addCustomerUseCase.execute(cust);
            return ResponseEntity.ok("Customer Created with ID: "+createdCustomer.getCustomerId());
    }

    @PostMapping("/addAddress")
    public ResponseEntity<String> addAddress(@RequestBody AddressDTO address){
            logger.info("Entered Address api: "+address);
            Address addr = MapperUtils.map(address, Address.class);
            Address createdAddress = addAddressUseCase.execute(addr);
            return ResponseEntity.ok("Address Created with ID: "+createdAddress.getAddressId());
    }

    @PostMapping("/updateAddress")
    public ResponseEntity<String> UpdateAddress(@RequestBody AddressDTO address){
            logger.info("Entered Address api: "+address);
            Address addr = MapperUtils.map(address, Address.class);
            Address updatedAddress = updateAddressUseCase.execute(addr);
            if(updatedAddress!=null){
              return ResponseEntity.ok("Address Updated with ID: "+updatedAddress.getAddressId());
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address Not Found");
            }
    }

    @PostMapping("/deleteAddress")
    public ResponseEntity<String> DeleteAddress(@RequestParam Integer id){
            logger.info("Entered Delete Addressapi: "+id);
                Boolean status = deleteAddressUseCase.execute(id);
            if(status){
                return ResponseEntity.ok("Address Deleted with ID: "+id+" with Status: "+status);            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address Not Found");
            }
    }

}
