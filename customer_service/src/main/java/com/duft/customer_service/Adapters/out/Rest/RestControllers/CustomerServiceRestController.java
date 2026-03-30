package com.duft.customer_service.Adapters.out.Rest.RestControllers;

import java.util.List;
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
import com.duft.customer_service.Adapters.out.CustomerFacade;
import com.duft.customer_service.Utils.RedisConfig.RedisUtil;


@RestController
public class CustomerServiceRestController {

    private final CustomerFacade customerFacade;

    public CustomerServiceRestController(CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customer){
        String id = customerFacade.addCustomer(customer);
        return ResponseEntity.ok("Customer Created with ID: "+id);
    }
    @GetMapping("/getCustomer")
    public ResponseEntity<CustomerDTO> getCustomer(@RequestParam Integer id){
        CustomerDTO customerDTO = customerFacade.getCustomer(id);
        return ResponseEntity.ok(customerDTO);
    }

    @PostMapping("/addAddress")
    public ResponseEntity<String> addAddress(@RequestBody AddressDTO address){
        String id = customerFacade.addAddress(address);
        return ResponseEntity.ok("Address Created with ID: "+id);
    }

    @PostMapping("/updateAddress")
    public ResponseEntity<String> UpdateAddress(@RequestBody AddressDTO address){
        String id = customerFacade.updateAddress(address);
        return ResponseEntity.ok("Address Updated with ID: "+id);
    }
    @GetMapping("/getAddress/")
    public ResponseEntity<AddressDTO> getAddress(@RequestParam Integer id){
        AddressDTO addressDT = customerFacade.getAddress(id);
        return ResponseEntity.ok(addressDT);
    }
    @GetMapping("/getAllAddressessByCustomerId/")
    public ResponseEntity<List<AddressDTO>> getAllAddressessByCustomerId(@RequestParam Integer id){
        List<AddressDTO> addressDTOs = customerFacade.getAllAddressessByCustomerId(id);
        return ResponseEntity.ok(addressDTOs);
    }

    @DeleteMapping("/deleteCustomer/")
    public ResponseEntity<String> deleteCustomer(@RequestParam Integer id){
        String deletedId = customerFacade.deleteCustomer(id);
        return ResponseEntity.ok("Customer Deleted with ID: "+deletedId);
    }

    @DeleteMapping("/deleteAddress/")
    public ResponseEntity<String> DeleteAddress(@RequestParam Integer id){
        String deletedId = customerFacade.deleteAddress(id);
        return ResponseEntity.ok("Address Deleted with ID: "+deletedId+" with Status: true");
    }

    @GetMapping("clearRedisDB")
    public ResponseEntity<String> CleanRedisDB(){
        RedisUtil.clearCache();
        return ResponseEntity.ok("Redis DB Cleared");
    }

}
