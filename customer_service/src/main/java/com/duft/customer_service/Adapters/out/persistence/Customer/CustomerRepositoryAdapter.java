package com.duft.customer_service.Adapters.out.persistence.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.duft.customer_service.Domain.Entities.Customer;
import com.duft.customer_service.port.out.CustomerRepositoryPort;

import tools.jackson.databind.ObjectMapper;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final SpringDataCustomerJpaRepository jpa;
    private final ObjectMapper mapper = new ObjectMapper();

    Logger logger = LoggerFactory.getLogger(CustomerRepositoryAdapter.class);
    public CustomerRepositoryAdapter(SpringDataCustomerJpaRepository jpa) {
        this.jpa = jpa;
    }
    //mapping from customer entity to customer
    private Customer toDomain(CustomerEntity e) {
        return mapper.convertValue(e, Customer.class);
    }
    //mapping from customer to customer entity
    private CustomerEntity toEntity(Customer c) {
        return mapper.convertValue(c, CustomerEntity.class);
    }

    @Override
    public Optional<Customer> findById(int id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity e = toEntity(customer);
        // Only set ID if it's a new entity (ID is 0 or null)
        // if (customer.getCustomerId() == null || customer.getCustomerId() <= 0) {
        //     e.setCustomerId(null); // Let the database generate the ID
        // } else {
        //     e.setCustomerId(customer.getCustomerId()); // Preserve existing ID
        // }
        e.setCustomerId(null);
        CustomerEntity saved = jpa.saveAndFlush(e);
        System.out.println(saved.toString());
        return toDomain(saved);
    }
    @Override
    public List<Customer> saveAll(List<Customer> listOfCustomers) {
        List<Customer> finalCustomerList = new ArrayList<>();
        for(Customer customer : listOfCustomers){
            finalCustomerList.add(this.save(customer));
        }
        return finalCustomerList;
    }
    @Override
    public Boolean deleteCustomerById(Integer id) {
         try {
            jpa.deleteById(id);
        } catch (Exception e) {
            logger.error("Failed to delete: "+e);
            return false;
        }
        return true;
    }
}