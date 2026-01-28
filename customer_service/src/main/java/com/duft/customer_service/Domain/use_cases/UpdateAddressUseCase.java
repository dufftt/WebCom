package com.duft.customer_service.Domain.use_cases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duft.customer_service.Domain.Entities.Address;
import com.duft.customer_service.port.out.AddressRepositoryPort;

public class UpdateAddressUseCase {

    private  AddressRepositoryPort addressRepositoryPort;
    Logger logger = LoggerFactory.getLogger(AddAddressUseCase.class);

    public UpdateAddressUseCase(AddressRepositoryPort addressRepositoryPort) {
        this.addressRepositoryPort = addressRepositoryPort;
    }


    public Address execute(Address address){
        Address exisitngAddress = addressRepositoryPort.findById(address.getAddressId()).orElse(null);
        if(exisitngAddress!=null){
            address.setCustomerId(exisitngAddress.getCustomerId());
             Address add = addressRepositoryPort.UpdateAddress(address);
            logger.info("Address Updated: "+add.toString());
            return add;
        }else{
            return null;
        }
       
       
    }
}
