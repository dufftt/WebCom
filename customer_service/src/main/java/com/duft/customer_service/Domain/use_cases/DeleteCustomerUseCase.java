package com.duft.customer_service.Domain.use_cases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duft.customer_service.Domain.Entities.Address;
import com.duft.customer_service.Domain.Entities.Customer;
import com.duft.customer_service.Domain.exceptions.CustomerException.CustomerNotFoundException;
import com.duft.customer_service.port.out.AddressRepositoryPort;
import com.duft.customer_service.port.out.CustomerRepositoryPort;

public class DeleteCustomerUseCase {

 private  CustomerRepositoryPort customerRepositoryPort;
    Logger logger = LoggerFactory.getLogger(DeleteCustomerUseCase.class);

    public DeleteCustomerUseCase(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    public Boolean execute(Integer id){
         Customer exisitngCustomer= customerRepositoryPort.findById(id).orElse(null);
        if(exisitngCustomer!=null){
        Boolean deleteStatus = customerRepositoryPort.deleteCustomerById(id);
                 logger.info("Customer Deleted: "+deleteStatus);
        return deleteStatus;
        }else{
            throw new CustomerNotFoundException("Customer Not Found"+id);
        }
       
    }
}
