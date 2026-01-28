package com.duft.customer_service.use_cases;

import com.duft.customer_service.Entities.Address;
import com.duft.customer_service.port.out.AddressRepositoryPort;

public class AddAddressUseCase {

    private final AddressRepositoryPort addressRepositoryPort;

    public AddAddressUseCase(AddressRepositoryPort addressRepositoryPort) {
        this.addressRepositoryPort = addressRepositoryPort;
    }

    public Address execute(Address address){
        Address add = addressRepositoryPort.save(address);
        return add;
    }
}
