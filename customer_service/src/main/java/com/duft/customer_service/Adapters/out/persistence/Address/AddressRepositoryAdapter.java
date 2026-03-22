package com.duft.customer_service.Adapters.out.persistence.Address;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.duft.customer_service.Domain.Entities.Address;
import com.duft.customer_service.port.out.AddressRepositoryPort;

import tools.jackson.databind.ObjectMapper;

@Repository
public class AddressRepositoryAdapter implements AddressRepositoryPort{

    private final SpringDataAddressJpaRepository jpa;
    Logger logger = LoggerFactory.getLogger(AddressRepositoryAdapter.class);
    private final ObjectMapper mapper = new ObjectMapper();


    public AddressRepositoryAdapter(SpringDataAddressJpaRepository jpa) {
        this.jpa = jpa;
    }

    //mapping from AddressEntity to domain Address using MapperUtils
    private Address toDomain(AddressEntity e) {
        return mapper.convertValue(e, Address.class);
    }

    //mapping from domain Address to AddressEntity using MapperUtils
    private AddressEntity toEntity(Address a) {
        return mapper.convertValue(a, AddressEntity.class);
    }

    @Override
    public List<Address> findAllById(Integer id) {
        // return MapperUtils.mapList(jpa.findAllById(Arrays.asList(id)), Address.class);
       List<AddressEntity> addressEntities = jpa.findAllById(Arrays.asList(id));
       return addressEntities.stream()
               .map(this::toDomain)
               .collect(Collectors.toList());
       
    }

    @Override
    public Address save(Address address) {
        AddressEntity e = toEntity(address);
        e.setAddressId(null);
        AddressEntity saved = jpa.saveAndFlush(e);
        logger.info("after save: "+saved.toString());
        return toDomain(saved);
    }

    @Override
    public Boolean DeleteAddressById(Integer id) {
        
        try {
            jpa.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete: "+e);
            return false;
        }
        return true;
    }

    @Override
    public Address UpdateAddress(Address address) {
        AddressEntity e = toEntity(address);
        AddressEntity saved = jpa.saveAndFlush(e);
        logger.info("after save: "+saved.toString());
        return toDomain(saved);
    }

    @Override
    public Optional<Address> findById(int id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public List<Address> getAllAddressessByCustomerId(Integer id) {
       List<AddressEntity> addressEntities = jpa.findAllAddressByCustomerId(id);
       return addressEntities.stream()
               .map(this::toDomain)
               .collect(Collectors.toList());
    }

}
