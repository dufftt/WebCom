package com.duft.customer_service.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.duft.customer_service.Entities.Customer;
import com.duft.customer_service.port.out.CustomerRepositoryPort;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final SpringDataCustomerJpaRepository jpa;

    public CustomerRepositoryAdapter(SpringDataCustomerJpaRepository jpa) {
        this.jpa = jpa;
    }
    //mapping from customer entity to customer
    private Customer toDomain(CustomerEntity e) {
        return new Customer(e.getCustomerId(), e.getName(), e.getMobNumber(), e.getEmail());
    }
    //mapping from customer to customer entity
    private CustomerEntity toEntity(Customer c) {
        return new CustomerEntity(c.getCustomerID(), c.getName(), c.getMobNumber(), c.getEmail());
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