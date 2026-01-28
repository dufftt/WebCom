package com.duft.customer_service.port.out;

import java.util.List;
import java.util.Optional;

import com.duft.customer_service.Domain.Entities.Address;

public interface AddressRepositoryPort {

    List<Address> findAllById(Integer id);
    Optional<Address> findById(int id);
    Address save(Address address);
    Boolean DeleteAddressById(Integer id);
    Address UpdateAddress(Address address);


}
