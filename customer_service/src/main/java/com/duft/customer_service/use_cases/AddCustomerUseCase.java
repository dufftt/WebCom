package com.duft.customer_service.use_cases;

import com.duft.customer_service.Entities.Customer;
import com.duft.customer_service.port.out.CustomerRepositoryPort;

public class AddCustomerUseCase {

    private CustomerRepositoryPort customerRepository;


    public AddCustomerUseCase(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Customer customer){
        Customer cus = customerRepository.save(customer);
        return cus;

    }



}
