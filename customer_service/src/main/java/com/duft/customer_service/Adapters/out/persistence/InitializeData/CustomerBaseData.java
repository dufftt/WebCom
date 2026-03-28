package com.duft.customer_service.Adapters.out.persistence.InitializeData;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.duft.customer_service.Domain.Entities.Address;
import com.duft.customer_service.Domain.Entities.Customer;
import com.duft.customer_service.port.out.AddressRepositoryPort;
import com.duft.customer_service.port.out.CustomerRepositoryPort;

import jakarta.annotation.PostConstruct;

@Configuration
public class CustomerBaseData {

    private CustomerRepositoryPort customerRepositoryPort;
    private AddressRepositoryPort addressRepositoryPort;

    public CustomerBaseData(CustomerRepositoryPort customerRepositoryPort, AddressRepositoryPort addressRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.addressRepositoryPort = addressRepositoryPort;
    }

    @PostConstruct
    public void InitializeData(){
        List<Customer> listOfCustomers = new ArrayList<>();
        listOfCustomers.add(new Customer(null, "Jack", 987654321L, "jack@gmail.com"));
        listOfCustomers.add(new Customer(null,"John", 123454321L, "john@gmail.com"));
        listOfCustomers.add(new Customer(null,"Jen", 23456321L, "jen@gmail.com"));
        listOfCustomers.add(new Customer(null,"Jody", 4567321L, "jody@gmail.com"));
        listOfCustomers.add(new Customer(null,"Jim", 67864321L, "jim@gmail.com"));
        List<Customer> addedCustomers = customerRepositoryPort.saveAll(listOfCustomers);

        List<Address> lifoAddresses = new ArrayList<>();
        lifoAddresses.add(new Address(null, addedCustomers.get(0).getCustomerId(), "Jacob Street", "Kolkata", "West Bengal", "India", 700001L));
        lifoAddresses.add(new Address(null, addedCustomers.get(1).getCustomerId(), "Johna Street", "Mumbai", "Maharastra", "India", 300001L));
        lifoAddresses.add(new Address(null, addedCustomers.get(2).getCustomerId(), "Jam Street", "Bhopal", "Odisha", "India", 400001L));
        lifoAddresses.add(new Address(null, addedCustomers.get(3).getCustomerId(), "josline Street", "New Delhi", "Delhi", "India", 100001L));
        lifoAddresses.add(new Address(null, addedCustomers.get(4).getCustomerId(), "Jim Carney Street", "Bangalore", "Karnataka", "India", 900001L));
        List<Address> addedAddresses = addressRepositoryPort.saveAll(lifoAddresses);
    }
}
