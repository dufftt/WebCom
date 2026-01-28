package com.duft.customer_service.Domain.use_cases;

import com.duft.customer_service.Domain.Entities.Customer;
import com.duft.customer_service.port.out.CustomerRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AddCustomerUseCase {

    private final CustomerRepositoryPort customerRepository;
    Logger logger = LoggerFactory.getLogger(AddCustomerUseCase.class);

    public AddCustomerUseCase(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer execute(Customer customer){
        Customer cus = customerRepository.save(customer);
        logger.info("Customer Created: "+cus.toString());
        return cus;
    }
}
