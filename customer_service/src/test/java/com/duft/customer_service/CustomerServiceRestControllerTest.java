package com.duft.customer_service;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.duft.customer_service.Adapters.WebDTOs.CustomerDTO;
import com.duft.customer_service.Adapters.out.CustomerFacade;
import com.duft.customer_service.Adapters.out.Rest.RestControllers.CustomerServiceRestController;
import com.duft.customer_service.Domain.exceptions.CustomerException.CustomerNotFoundException;

@WebMvcTest(CustomerServiceRestController.class)
public class CustomerServiceRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerFacade customerFacade;

    @Test
    public void getCustomer_validId_shouldReturnCustomer() throws Exception {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(1);
        dto.setName("John Doe");
        dto.setEmail("john@doe.com");
        dto.setMobNumber(1234567890L);

        when(customerFacade.getCustomer(1)).thenReturn(dto);

        mockMvc.perform(get("/customerservice/getCustomer")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@doe.com"));
    }

    @Test
    public void getCustomer_invalidId_shouldReturnError() throws Exception {
        // The exception handler will catch this and format a 404 response
        when(customerFacade.getCustomer(99))
            .thenThrow(new CustomerNotFoundException("Customer id: 99 not found."));

        mockMvc.perform(get("/customerservice/getCustomer")
                .param("id", "99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").exists()); // Adjust based on your RestExceptionHandler mapping
    }

}