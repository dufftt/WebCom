package com.duft.customer_service.Adapters.RestControllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duft.customer_service.Adapters.WebDTOs.AddressDTO;
import com.duft.customer_service.Adapters.WebDTOs.CustomerDTO;
import com.duft.customer_service.Domain.Entities.Address;
import com.duft.customer_service.Domain.Entities.Customer;
import com.duft.customer_service.Domain.use_cases.AddAddressUseCase;
import com.duft.customer_service.Domain.use_cases.AddCustomerUseCase;
import com.duft.customer_service.Domain.use_cases.DeleteAddressUseCase;
import com.duft.customer_service.Domain.use_cases.UpdateAddressUseCase;
import com.duft.customer_service.Utils.RedisConfig.RedisUtil;

import tools.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/customerservice")
public class CustomerServiceRestController {

    Logger logger
        = LoggerFactory.getLogger(CustomerServiceRestController.class);
    private final AddCustomerUseCase addCustomerUseCase;
    private final AddAddressUseCase addAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;
    private final ObjectMapper mapper = new ObjectMapper();

    public CustomerServiceRestController(AddCustomerUseCase addCustomerUseCase, AddAddressUseCase addAddressUseCase,
            UpdateAddressUseCase updateAddressUseCase, DeleteAddressUseCase deleteAddressUseCase) {
        this.addCustomerUseCase = addCustomerUseCase;
        this.addAddressUseCase = addAddressUseCase;
        this.updateAddressUseCase = updateAddressUseCase;
        this.deleteAddressUseCase = deleteAddressUseCase;
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customer){
            logger.info("Entered api: "+customer);
            //Customer cust = MapperUtils.map(customer, Customer.class);
            Customer custo = mapper.convertValue(customer, Customer.class);
            Customer createdCustomer = addCustomerUseCase.execute(custo);
            return ResponseEntity.ok("Customer Created with ID: "+createdCustomer.getCustomerId());
    }
    @GetMapping("/getCustomer")
    public ResponseEntity<CustomerDTO> getCustomer(@RequestParam Integer id){
        logger.info("Entered GetCustomer: with id: ",id);
        CustomerDTO cachedCustomer = RedisUtil.getCache("Customer-"+id, CustomerDTO.class);
        if(cachedCustomer!=null){
            // CustomerDTO customerDTO = MapperUtils.mapCache(cachedCustomer, CustomerDTO.class);
            return ResponseEntity.ok(cachedCustomer);
        }else{
            Customer customer = addCustomerUseCase.findCustomerById(id);
            // CustomerDTO customerDTO = MapperUtils.map(customer, CustomerDTO.class);
            CustomerDTO customerDTO = mapper.convertValue(customer, CustomerDTO.class);
            RedisUtil.addCache("Customer-"+id, customerDTO);
            return ResponseEntity.ok(customerDTO);
        }
    }

    @PostMapping("/addAddress")
    public ResponseEntity<String> addAddress(@RequestBody AddressDTO address){
            logger.info("Entered Address api: "+address);
            //Address addr = MapperUtils.map(address, Address.class);
            Address adr = mapper.convertValue(address, Address.class);
            Address createdAddress = addAddressUseCase.execute(adr);
            return ResponseEntity.ok("Address Created with ID: "+createdAddress.getAddressId());
    }

    @PostMapping("/updateAddress")
    public ResponseEntity<String> UpdateAddress(@RequestBody AddressDTO address){
            logger.info("Entered Address api: "+address);
            //Address addr = MapperUtils.map(address, Address.class);
            Address adr = mapper.convertValue(address, Address.class);
            Address updatedAddress = updateAddressUseCase.execute(adr);
            if(updatedAddress!=null){
                RedisUtil.addCache("Address-"+updatedAddress.getAddressId(), updatedAddress);
              List<Integer> addresses = RedisUtil.getCache("Customer-Address-link-"+updatedAddress.getCustomerId(), List.class);
             logger.info("customer - address link: "+addresses);
              if(addresses==null){
                addresses = new ArrayList<>();
              }
              addresses.add(updatedAddress.getAddressId());
                RedisUtil.addCache("Customer-Address-link-"+updatedAddress.getCustomerId(), addresses);
              return ResponseEntity.ok("Address Updated with ID: "+updatedAddress.getAddressId());
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address Not Found");
            }
    }
    @GetMapping("/getAddress/")
    public ResponseEntity<AddressDTO> getAddress(@RequestParam Integer id){
            logger.info("Entered GetAddress: with id: ",id);
            AddressDTO cachedAddress = RedisUtil.getCache("Address-"+id, AddressDTO.class);
            if(cachedAddress!=null){
                // AddressDTO addressDTO = MapperUtils.mapCache(cachedAddress, AddressDTO.class);
                return ResponseEntity.ok(cachedAddress);
            }else{
                Address address = updateAddressUseCase.getAddress(id);
                
            if(address==null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            else{
                RedisUtil.addCache("Address-"+id, address);
               // AddressDTO addressDTO = MapperUtils.map(address, AddressDTO.class);
                AddressDTO addressDT = mapper.convertValue(address, AddressDTO.class);
                return ResponseEntity.ok(addressDT);
            }
            }
            
        }
        @GetMapping("/getAllAddressessByCustomerId/")
        public ResponseEntity<List<AddressDTO>> getAllAddressessByCustomerId(@RequestParam Integer id){
            logger.info("Entered GetAddress: with id: ",id);
            List<Integer> cachedAddress = RedisUtil.getCache("Customer-Address-link-"+id, List.class);
            logger.info("customer - address link: "+cachedAddress);
            List<AddressDTO> addressDTOs = new ArrayList<>();
            if(cachedAddress!=null){
                for(Integer addressId : cachedAddress){
                    AddressDTO addressDTO = RedisUtil.getCache("Address-"+addressId, AddressDTO.class);
                    addressDTOs.add(addressDTO);
                }
                return ResponseEntity.ok(addressDTOs);
            }else{
                List<Address> addresses = updateAddressUseCase.getAllAddressessByCustomerId(id);
                List<Integer> addressesId = new ArrayList<>();
                if(addresses!=null){
                for(Address address : addresses){
                    AddressDTO addressDTO = mapper.convertValue(address, AddressDTO.class);
                    addressDTOs.add(addressDTO);
                    addressesId.add(address.getAddressId());
                }
                RedisUtil.addCache("Customer-Address-link-"+id, addressesId);
                return ResponseEntity.ok(addressDTOs);
                }else{
                   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
                }
            
        }

    @DeleteMapping("/deleteCustomer/")
    public ResponseEntity<String> deleteCustomer(@RequestParam Integer id){
        logger.info("Entered Delete Customerapi: "+id);
        Customer customer = addCustomerUseCase.findCustomerById(id);
        if(customer!=null){
            List<Address> addresses = updateAddressUseCase.getAllAddressessByCustomerId(id);
            if(addresses!=null){
                for(Address address : addresses){
                    deleteAddressUseCase.execute(address.getAddressId());
                    RedisUtil.deleteCache("Address-"+address.getAddressId());
                    
                }
            }
            RedisUtil.deleteCache("Customer-"+id);
            RedisUtil.deleteCache("Customer-Address-link-"+id);
            return ResponseEntity.ok("Customer Deleted with ID: "+id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Not Found");
    }

    @DeleteMapping("/deleteAddress/")
    public ResponseEntity<String> DeleteAddress(@RequestParam Integer id){
            logger.info("Entered Delete Addressapi: "+id);
                Boolean status = deleteAddressUseCase.execute(id);
            if(status){
                RedisUtil.deleteCache("Address-"+id);
                List<Integer> addresses = RedisUtil.getCache("Customer-Address-link-"+id,List.class);
                if(addresses!=null){
                    addresses.remove(id);
                    RedisUtil.addCache("Customer-Address-link-"+id, addresses);
                }
                return ResponseEntity.ok("Address Deleted with ID: "+id+" with Status: "+status);}
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address Not Found");
            }
    }

}
