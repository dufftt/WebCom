package com.duft.customer_service.Adapters.out;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duft.customer_service.Adapters.WebDTOs.AddressDTO;
import com.duft.customer_service.Adapters.WebDTOs.CustomerDTO;
import com.duft.customer_service.Domain.Entities.Address;
import com.duft.customer_service.Domain.Entities.Customer;
import com.duft.customer_service.Domain.exceptions.AddressException.AddressNotFoundException;
import com.duft.customer_service.Domain.exceptions.CustomerException.CustomerNotFoundException;
import com.duft.customer_service.Domain.use_cases.AddAddressUseCase;
import com.duft.customer_service.Domain.use_cases.AddCustomerUseCase;
import com.duft.customer_service.Domain.use_cases.DeleteAddressUseCase;
import com.duft.customer_service.Domain.use_cases.DeleteCustomerUseCase;
import com.duft.customer_service.Domain.use_cases.UpdateAddressUseCase;
import com.duft.customer_service.Utils.RedisConfig.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerFacade {
    private final Logger logger = LoggerFactory.getLogger(CustomerFacade.class);
    private final AddCustomerUseCase addCustomerUseCase;
    private final AddAddressUseCase addAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final ObjectMapper mapper = new ObjectMapper();

    public CustomerFacade(AddCustomerUseCase addCustomerUseCase, AddAddressUseCase addAddressUseCase,
            UpdateAddressUseCase updateAddressUseCase, DeleteAddressUseCase deleteAddressUseCase, 
            DeleteCustomerUseCase deleteCustomerUseCase) {
        this.addCustomerUseCase = addCustomerUseCase;
        this.addAddressUseCase = addAddressUseCase;
        this.updateAddressUseCase = updateAddressUseCase;
        this.deleteAddressUseCase = deleteAddressUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
    }

    public String addCustomer(CustomerDTO customer) {
        logger.info("Entered api: " + customer);
        Customer custo = mapper.convertValue(customer, Customer.class);
        Customer createdCustomer = addCustomerUseCase.execute(custo);
        return createdCustomer.getCustomerId().toString();
    }

    public CustomerDTO getCustomer(Integer id) {
        logger.info("Entered GetCustomer: with id: {}", id);
        CustomerDTO cachedCustomer = RedisUtil.getCache("Customer-" + id, CustomerDTO.class);
        if (cachedCustomer != null) {
            return cachedCustomer;
        } else {
            Customer customer = addCustomerUseCase.findCustomerById(id);
            if (customer == null) {
                throw new CustomerNotFoundException("Customer id: " + id + " not found.");
            }
            CustomerDTO customerDTO = mapper.convertValue(customer, CustomerDTO.class);
            RedisUtil.addCache("Customer-" + id, customerDTO);
            return customerDTO;
        }
    }

    public String addAddress(AddressDTO address) {
        logger.info("Entered Address api: " + address);
        Address adr = mapper.convertValue(address, Address.class);
        Address createdAddress = addAddressUseCase.execute(adr);
        return createdAddress.getAddressId().toString();
    }

    public String updateAddress(AddressDTO address) {
        logger.info("Entered Address api: " + address);
        Address adr = mapper.convertValue(address, Address.class);
        Address updatedAddress = updateAddressUseCase.execute(adr);
        if (updatedAddress != null) {
            RedisUtil.addCache("Address-" + updatedAddress.getAddressId(), updatedAddress);
            List<Integer> addresses = RedisUtil.getCache("Customer-Address-link-" + updatedAddress.getCustomerId(), List.class);
            logger.info("customer - address link: " + addresses);
            if (addresses == null) {
                addresses = new ArrayList<>();
            }
            addresses.add(updatedAddress.getAddressId());
            RedisUtil.addCache("Customer-Address-link-" + updatedAddress.getCustomerId(), addresses);
            return updatedAddress.getAddressId().toString();
        } else {
            throw new AddressNotFoundException("Address id: " + address.getAddressId() + " Not Found");
        }
    }

    public AddressDTO getAddress(Integer id) {
        logger.info("Entered GetAddress: with id: {}", id);
        AddressDTO cachedAddress = RedisUtil.getCache("Address-" + id, AddressDTO.class);
        if (cachedAddress != null) {
            return cachedAddress;
        } else {
            Address address = updateAddressUseCase.getAddress(id);
            if (address == null) {
                throw new AddressNotFoundException("Address id: " + id + " Not Found");
            } else {
                RedisUtil.addCache("Address-" + id, address);
                AddressDTO addressDT = mapper.convertValue(address, AddressDTO.class);
                return addressDT;
            }
        }
    }

    public List<AddressDTO> getAllAddressessByCustomerId(Integer id) {
        logger.info("Entered GetAddress: with id: {}", id);
        List<Integer> cachedAddress = RedisUtil.getCache("Customer-Address-link-" + id, List.class);
        logger.info("customer - address link: " + cachedAddress);
        List<AddressDTO> addressDTOs = new ArrayList<>();
        if (cachedAddress != null) {
            for (Integer addressId : cachedAddress) {
                AddressDTO addressDTO = RedisUtil.getCache("Address-" + addressId, AddressDTO.class);
                addressDTOs.add(addressDTO);
            }
            return addressDTOs;
        } else {
            List<Address> addresses = updateAddressUseCase.getAllAddressessByCustomerId(id);
            List<Integer> addressesId = new ArrayList<>();
            if (addresses != null) {
                for (Address address : addresses) {
                    AddressDTO addressDTO = mapper.convertValue(address, AddressDTO.class);
                    addressDTOs.add(addressDTO);
                    addressesId.add(address.getAddressId());
                }
                RedisUtil.addCache("Customer-Address-link-" + id, addressesId);
                return addressDTOs;
            } else {
                throw new AddressNotFoundException("Address with customer id: " + id + " Not Found");
            }
        }
    }

    public String deleteCustomer(Integer id) {
        logger.info("Entered Delete Customer api: " + id);
        Customer customer = addCustomerUseCase.findCustomerById(id);
        if (customer != null) {
            List<Address> addresses = updateAddressUseCase.getAllAddressessByCustomerId(id);
            if (addresses != null) {
                for (Address address : addresses) {
                    deleteAddressUseCase.execute(address.getAddressId());
                    RedisUtil.deleteCache("Address-" + address.getAddressId());
                }
            }
            RedisUtil.deleteCache("Customer-" + id);
            RedisUtil.deleteCache("Customer-Address-link-" + id);
            deleteCustomerUseCase.execute(id);
            return id.toString();
        }
        throw new CustomerNotFoundException("Customer id: " + id + " Not Found");
    }

    public String deleteAddress(Integer id) {
        logger.info("Entered Delete Addressapi: " + id);
        Boolean status = deleteAddressUseCase.execute(id);
        if (status) {
            RedisUtil.deleteCache("Address-" + id);
            List<Integer> addresses = RedisUtil.getCache("Customer-Address-link-" + id, List.class);
            if (addresses != null) {
                addresses.remove(id);
                RedisUtil.addCache("Customer-Address-link-" + id, addresses);
            }
            return id.toString();
        } else {
            throw new AddressNotFoundException("Address id: " + id + "Not Found");
        }
    }
}