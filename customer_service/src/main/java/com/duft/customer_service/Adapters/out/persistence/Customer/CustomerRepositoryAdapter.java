package com.duft.customer_service.Adapters.out.persistence.Customer;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.duft.customer_service.Entities.Customer;
import com.duft.customer_service.Utils.MaperUtils.MapperUtils;
import com.duft.customer_service.port.out.CustomerRepositoryPort;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final SpringDataCustomerJpaRepository jpa;

    public CustomerRepositoryAdapter(SpringDataCustomerJpaRepository jpa) {
        this.jpa = jpa;
    }
    //mapping from customer entity to customer
    private Customer toDomain(CustomerEntity e) {
        return MapperUtils.map(e, Customer.class);
    }
    //mapping from customer to customer entity
    private CustomerEntity toEntity(Customer c) {
        return MapperUtils.map(c, CustomerEntity.class);
    }

    @Override
    public Optional<Customer> findById(int id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity e = toEntity(customer);
        CustomerEntity saved = jpa.save(e);
        return toDomain(saved);
    }
}