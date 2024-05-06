package lk.mydentist.api.controller;

import lk.mydentist.api.dto.CustomerDto;
import lk.mydentist.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<CustomerDto> saveCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto dto = customerService.saveCustomer(customerDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllCustomer(){
        List<CustomerDto> dtos = this.customerService.getAllCustomer();
        return new ResponseEntity<>(dtos,HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto customerDto){
        CustomerDto dto = this.customerService.updateCustomer(customerId,customerDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long customerId){
        CustomerDto dto = this.customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> searchCustomer(@PathVariable Long customerId){
        CustomerDto dto = this.customerService.searchCustomer(customerId);
            if (dto == null){
                return new ResponseEntity<>("Empty Data",HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<>(dto,HttpStatus.OK);
            }
    }
}
