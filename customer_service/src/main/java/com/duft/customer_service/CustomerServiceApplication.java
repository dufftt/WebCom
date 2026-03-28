package com.duft.customer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.graphql.autoconfigure.GraphQlSourceBuilderCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.data.federation.FederationSchemaFactory;

import com.duft.customer_service.Adapters.out.CustomerFacade;
import com.duft.customer_service.Adapters.out.GraphQL.GraphQlController.CustomerServiceGraphController;
import com.duft.customer_service.Adapters.out.Rest.RestControllers.CustomerServiceRestController;
import com.duft.customer_service.Adapters.out.persistence.Address.AddressRepositoryAdapter;
import com.duft.customer_service.Adapters.out.persistence.Customer.CustomerRepositoryAdapter;
import com.duft.customer_service.Domain.use_cases.AddAddressUseCase;
import com.duft.customer_service.Domain.use_cases.AddCustomerUseCase;
import com.duft.customer_service.Domain.use_cases.DeleteAddressUseCase;
import com.duft.customer_service.Domain.use_cases.DeleteCustomerUseCase;
import com.duft.customer_service.Domain.use_cases.UpdateAddressUseCase;
import com.duft.customer_service.port.out.AddressRepositoryPort;
import com.duft.customer_service.port.out.CustomerRepositoryPort;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
//setup docker - redis: docker run -d --name redis-server -p 6379:6379 redis
	
	
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
	public DeleteCustomerUseCase deleteCustomerUseCase(CustomerRepositoryPort customerRepositoryPort){
		return new DeleteCustomerUseCase(customerRepositoryPort);
	}

	
	@Bean
	public CustomerFacade customerFacade(AddCustomerUseCase addCustomerUseCase, AddAddressUseCase addAddressUseCase, UpdateAddressUseCase updateAddressUseCase, DeleteAddressUseCase deleteAddressUseCase, DeleteCustomerUseCase deleteCustomerUseCase) {
		return new CustomerFacade(addCustomerUseCase, addAddressUseCase, updateAddressUseCase, deleteAddressUseCase, deleteCustomerUseCase);
	}

	@Bean
	public CustomerServiceRestController customerServiceRestController(CustomerFacade customerFacade){
		return new CustomerServiceRestController(customerFacade);
	}
	@Bean
	public CustomerServiceGraphController customerServiceGraphController(CustomerFacade customerFacade){
		return new CustomerServiceGraphController(customerFacade);
	}


	//graphql - apollo federation configuration
	@Bean
	public GraphQlSourceBuilderCustomizer customizer(FederationSchemaFactory factory) {
		return builder -> builder.schemaFactory(factory::createGraphQLSchema);
	}

	@Bean
	public FederationSchemaFactory schemaFactory() {
		return new FederationSchemaFactory();
	}

}
