package com.duft.customer_service.Domain.use_cases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duft.customer_service.Domain.Entities.Address;
import com.duft.customer_service.port.out.AddressRepositoryPort;

public class DeleteAddressUseCase {

 private  AddressRepositoryPort addressRepositoryPort;
    Logger logger = LoggerFactory.getLogger(AddAddressUseCase.class);

    public DeleteAddressUseCase(AddressRepositoryPort addressRepositoryPort) {
        this.addressRepositoryPort = addressRepositoryPort;
    }

    public Boolean execute(Integer id){
         Address exisitngAddress = addressRepositoryPort.findById(id).orElse(null);
        if(exisitngAddress!=null){
        Boolean deleteStatus = addressRepositoryPort.DeleteAddressById(id);
                 logger.info("Address Deleted: "+deleteStatus);
        return deleteStatus;
        }else{
            logger.info("Address Not present with id: "+id);
            return false;
        }
       
    }
}
