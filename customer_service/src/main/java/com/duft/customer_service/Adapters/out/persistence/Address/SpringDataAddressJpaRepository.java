package com.duft.customer_service.Adapters.out.persistence.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duft.customer_service.Domain.Entities.Address;

public interface SpringDataAddressJpaRepository extends JpaRepository<AddressEntity, Integer> {

    public List<AddressEntity> findAllAddressByCustomerId(Integer id);
}
