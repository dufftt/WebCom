package com.duft.customer_service.Adapters.out.GraphQL.GraphQlController;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.federation.EntityMapping;



import com.duft.customer_service.Adapters.WebDTOs.AddressDTO;
import com.duft.customer_service.Adapters.WebDTOs.CustomerDTO;
import com.duft.customer_service.Adapters.out.CustomerFacade;

@Controller
public class CustomerServiceGraphController {

    private final CustomerFacade customerFacade;

    public CustomerServiceGraphController(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @QueryMapping
    public CustomerDTO getCustomer(@Argument Integer id){
        return customerFacade.getCustomer(id);
    }

    @EntityMapping
    public CustomerDTO customerDTO(@Argument Integer customerId) {
        return customerFacade.getCustomer(customerId);
    }

    @EntityMapping
    public AddressDTO addressDTO(@Argument Integer addressId) {
        return customerFacade.getAddress(addressId);
    }

    @MutationMapping
    public String addCustomer(@Argument CustomerDTO customer){
        return customerFacade.addCustomer(customer);
    }

    @MutationMapping
    public String addAddress(@Argument AddressDTO address){
        return customerFacade.addAddress(address);
    }

    @MutationMapping
    public String UpdateAddress(@Argument AddressDTO address){
        return customerFacade.updateAddress(address);
    }
    @QueryMapping
    public AddressDTO getAddress(@Argument Integer id){
        return customerFacade.getAddress(id);
    }
    @QueryMapping
    public List<AddressDTO> getAllAddressessByCustomerId(@Argument Integer id){
        return customerFacade.getAllAddressessByCustomerId(id);
    }

    @MutationMapping
    public String deleteCustomer(@Argument Integer id){
        return customerFacade.deleteCustomer(id);
    }

    @MutationMapping
    public String DeleteAddress(@Argument Integer id){
        return customerFacade.deleteAddress(id);
    }

}
