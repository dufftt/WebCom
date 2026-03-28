package com.duft.customer_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duft.customer_service.Domain.Entities.Customer;
import com.duft.customer_service.Domain.use_cases.AddCustomerUseCase;
import com.duft.customer_service.port.out.CustomerRepositoryPort;

@ExtendWith(MockitoExtension.class)
public class AddCustomerUseCaseTest {

    @Mock
    private CustomerRepositoryPort customerRepositoryPort;

    @InjectMocks
    private AddCustomerUseCase addCustomerUseCase;

    @Test
    public void execute_shouldSaveAndReturnCustomer() {
        Customer customer = new Customer(null, "Test User", 1234567890L, "test@test.com");
        Customer savedCustomer = new Customer(1, "Test User", 1234567890L, "test@test.com");
        
        when(customerRepositoryPort.save(any(Customer.class))).thenReturn(savedCustomer);
        
        Customer result = addCustomerUseCase.execute(customer);
        
        assertNotNull(result);
        assertEquals(1, result.getCustomerId());
        assertEquals("Test User", result.getName());
        verify(customerRepositoryPort, times(1)).save(customer);
    }

}