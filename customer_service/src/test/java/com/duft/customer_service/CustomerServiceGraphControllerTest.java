package com.duft.customer_service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import com.duft.customer_service.Adapters.WebDTOs.CustomerDTO;
import com.duft.customer_service.Adapters.out.CustomerFacade;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceGraphControllerTest {

    @Autowired
    private HttpGraphQlTester graphQlTester;

    @MockitoBean
    private CustomerFacade customerFacade;

    @Test
    public void getCustomer_shouldReturnCustomerDTO() {
        // Arrange
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(1);
        dto.setName("GraphQL User");
        dto.setEmail("gql@example.com");
        dto.setMobNumber(9876543210L);

        when(customerFacade.getCustomer(1)).thenReturn(dto);

        // Act & Assert
        String document = """
            query {
                getCustomer(id: 1) {
                    customerId
                    name
                    email
                }
            }
        """;

        graphQlTester.document(document)
                .execute()
                .path("getCustomer.customerId").entity(Integer.class).isEqualTo(1)
                .path("getCustomer.name").entity(String.class).isEqualTo("GraphQL User")
                .path("getCustomer.email").entity(String.class).isEqualTo("gql@example.com");
    }

}