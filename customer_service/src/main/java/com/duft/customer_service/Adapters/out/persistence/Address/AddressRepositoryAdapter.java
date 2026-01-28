package com.duft.customer_service.Adapters.out.persistence.Address;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.duft.customer_service.Entities.Address;
import com.duft.customer_service.Utils.MaperUtils.MapperUtils;
import com.duft.customer_service.port.out.AddressRepositoryPort;

@Repository
public class AddressRepositoryAdapter implements AddressRepositoryPort{

    private final SpringDataAddressJpaRepository jpa;

    public AddressRepositoryAdapter(SpringDataAddressJpaRepository jpa) {
        this.jpa = jpa;
    }

    //mapping from AddressEntity to domain Address using MapperUtils
    private Address toDomain(AddressEntity e) {
        return MapperUtils.map(e, Address.class);
    }

    //mapping from domain Address to AddressEntity using MapperUtils
    private AddressEntity toEntity(Address a) {
        return MapperUtils.map(a, AddressEntity.class);
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
