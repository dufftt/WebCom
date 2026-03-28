package com.duft.customer_service.port.out;

import java.util.List;
import java.util.Optional;

import com.duft.customer_service.Domain.Entities.Customer;

public interface CustomerRepositoryPort {
    Optional<Customer> findById(int id);
    Customer save(Customer customer);
    List<Customer> saveAll(List<Customer> listOfCustomers);
    Boolean deleteCustomerById(Integer id);
}