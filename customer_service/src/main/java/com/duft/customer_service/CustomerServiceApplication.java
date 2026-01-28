package com.duft.customer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.duft.customer_service.Adapters.RestControllers.CustomerServiceRestController;
import com.duft.customer_service.Adapters.out.persistence.Address.AddressRepositoryAdapter;
import com.duft.customer_service.Adapters.out.persistence.Customer.CustomerRepositoryAdapter;
import com.duft.customer_service.Domain.use_cases.AddAddressUseCase;
import com.duft.customer_service.Domain.use_cases.AddCustomerUseCase;
import com.duft.customer_service.Domain.use_cases.UpdateAddressUseCase;
import com.duft.customer_service.Domain.use_cases.DeleteAddressUseCase;
import com.duft.customer_service.port.out.AddressRepositoryPort;
import com.duft.customer_service.port.out.CustomerRepositoryPort;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	
	@Bean	
	public CustomerRepositoryPort customerRepositoryPort(CustomerRepositoryAdapter customerRepositoryAdapter){
		return customerRepositoryAdapter;
	}
	
	@Bean
	public AddCustomerUseCase addCustomerUseCase(CustomerRepositoryPort customerRepositoryPort){
		return new AddCustomerUseCase(customerRepositoryPort);
	}

	@Bean	
	public AddressRepositoryPort addressRepositoryPort(AddressRepositoryAdapter addressRepositoryAdapter){
		return addressRepositoryAdapter;
	}
	
	@Bean
	public AddAddressUseCase addAddressUseCase(AddressRepositoryPort addressRepositoryPort){
		return new AddAddressUseCase(addressRepositoryPort);
	}

	@Bean
	public UpdateAddressUseCase updateAddressUseCase(AddressRepositoryPort addressRepositoryPort){
		return new UpdateAddressUseCase(addressRepositoryPort);
	}

	@Bean
	public DeleteAddressUseCase deleteAddressUseCase(AddressRepositoryPort addressRepositoryPort){
		return new DeleteAddressUseCase(addressRepositoryPort);
	}
	
	@Bean
	public CustomerServiceRestController customerServiceRestController(AddCustomerUseCase addCustomerUseCase, AddAddressUseCase addAddressUseCase, UpdateAddressUseCase updateAddressUseCase, DeleteAddressUseCase deleteAddressUseCase){
		return new CustomerServiceRestController(addCustomerUseCase, addAddressUseCase, updateAddressUseCase, deleteAddressUseCase);
	}

}
