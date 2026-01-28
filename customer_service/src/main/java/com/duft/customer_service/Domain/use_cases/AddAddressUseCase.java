package com.duft.customer_service.Domain.use_cases;

import com.duft.customer_service.Domain.Entities.Address;
import com.duft.customer_service.port.out.AddressRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AddAddressUseCase {

    private final AddressRepositoryPort addressRepositoryPort;
    Logger logger = LoggerFactory.getLogger(AddAddressUseCase.class);

    public AddAddressUseCase(AddressRepositoryPort addressRepositoryPort) {
        this.addressRepositoryPort = addressRepositoryPort;
    }

    public Address execute(Address address){
        Address add = addressRepositoryPort.save(address);
        logger.info("Address Created: "+add.toString());
        return add;
    }
}
