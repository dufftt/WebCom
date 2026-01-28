package com.duft.customer_service.Adapters.out.persistence.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {
}