package com.duft.customer_service.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.duft.customer_service.Entities.Address;
import com.duft.customer_service.port.out.AddressRepositoryPort;

@Repository
public class AddressRepositoryAdapter implements AddressRepositoryPort{

    private final SpringDataAddressJpaRepository jpa;

    public AddressRepositoryAdapter(SpringDataAddressJpaRepository jpa) {
        this.jpa = jpa;
    }

    //mapping from customer entity to customer
    private Address toDomain(AddressEntity e) {
        return new Address(e.getAddressID(), e.getCustomerID(), e.getAddress(), e.getCity(),e.getState(),e.getCountry(),e.getPinCode());
    }
    //mapping from customer to customer entity
    private AddressEntity toEntity(Address a) {
        return new AddressEntity(a.getAddressID(), a.getCustomerID(), a.getAddress(), a.getCity(),a.getState(),a.getCountry(),a.getPinCode());
    }

    @Override
    public Optional<Address> findById(int id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public Address save(Address address) {
        AddressEntity e = toEntity(address);
        AddressEntity saved = jpa.save(e);
        return toDomain(saved);
    }

}
