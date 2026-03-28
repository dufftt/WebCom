package com.duft.customer_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duft.customer_service.Adapters.WebDTOs.CustomerDTO;
import com.duft.customer_service.Adapters.out.CustomerFacade;
import com.duft.customer_service.Domain.Entities.Customer;
import com.duft.customer_service.Domain.exceptions.CustomerException.CustomerNotFoundException;
import com.duft.customer_service.Domain.use_cases.AddAddressUseCase;
import com.duft.customer_service.Domain.use_cases.AddCustomerUseCase;
import com.duft.customer_service.Domain.use_cases.DeleteAddressUseCase;
import com.duft.customer_service.Domain.use_cases.DeleteCustomerUseCase;
import com.duft.customer_service.Domain.use_cases.UpdateAddressUseCase;
import com.duft.customer_service.Utils.RedisConfig.RedisUtil;

@ExtendWith(MockitoExtension.class)
public class CustomerFacadeTest {

    @Mock private AddCustomerUseCase addCustomerUseCase;
    @Mock private AddAddressUseCase addAddressUseCase;
    @Mock private UpdateAddressUseCase updateAddressUseCase;
    @Mock private DeleteAddressUseCase deleteAddressUseCase;
    @Mock private DeleteCustomerUseCase deleteCustomerUseCase;

    @InjectMocks
    private CustomerFacade customerFacade;

    @Test
    public void getCustomer_cacheHit_shouldReturnFromRedis() {
        CustomerDTO cachedCustomer = new CustomerDTO();
        cachedCustomer.setCustomerId(1);
        cachedCustomer.setName("Cached User");

        try (MockedStatic<RedisUtil> mockedRedis = mockStatic(RedisUtil.class)) {
            mockedRedis.when(() -> RedisUtil.getCache("Customer-1", CustomerDTO.class))
                       .thenReturn(cachedCustomer);

            CustomerDTO result = customerFacade.getCustomer(1);

            assertNotNull(result);
            assertEquals("Cached User", result.getName());
            verify(addCustomerUseCase, never()).findCustomerById(any());
        }
    }

    @Test
    public void getCustomer_cacheMiss_shouldFetchFromDbAndCache() {
        Customer dbCustomer = new Customer(2, "DB User", 9876543210L, "db@user.com");
        
        try (MockedStatic<RedisUtil> mockedRedis = mockStatic(RedisUtil.class)) {
            mockedRedis.when(() -> RedisUtil.getCache("Customer-2", CustomerDTO.class)).thenReturn(null);
            when(addCustomerUseCase.findCustomerById(2)).thenReturn(dbCustomer);

            CustomerDTO result = customerFacade.getCustomer(2);

            assertNotNull(result);
            assertEquals("DB User", result.getName());
            mockedRedis.verify(() -> RedisUtil.addCache(eq("Customer-2"), any(CustomerDTO.class)), times(1));
        }
    }

    @Test
    public void getCustomer_notFound_shouldThrowException() {
        try (MockedStatic<RedisUtil> mockedRedis = mockStatic(RedisUtil.class)) {
            mockedRedis.when(() -> RedisUtil.getCache("Customer-99", CustomerDTO.class)).thenReturn(null);
            when(addCustomerUseCase.findCustomerById(99)).thenReturn(null);
            assertThrows(CustomerNotFoundException.class, () -> customerFacade.getCustomer(99));
        }
    }
}