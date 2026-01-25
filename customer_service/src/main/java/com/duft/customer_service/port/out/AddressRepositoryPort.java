package com.duft.customer_service.port.out;

import java.util.Optional;

import com.duft.customer_service.Entities.Address;

public interface AddressRepositoryPort {

    Optional<Address> findById(int id);
    Address save(Address address);
}
