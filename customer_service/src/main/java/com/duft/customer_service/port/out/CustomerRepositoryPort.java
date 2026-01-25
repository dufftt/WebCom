package com.duft.customer_service.port.out;

import com.duft.customer_service.Entities.Customer;
import java.util.Optional;

public interface CustomerRepositoryPort {
    Optional<Customer> findById(int id);
    Customer save(Customer customer);
}