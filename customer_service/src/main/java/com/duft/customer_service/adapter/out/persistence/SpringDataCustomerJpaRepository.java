package com.duft.customer_service.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {
}