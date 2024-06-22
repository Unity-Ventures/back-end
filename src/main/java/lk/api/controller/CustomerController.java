package lk.api.controller;

import lk.api.dto.CustomerDto;
import lk.api.dto.getdto.CustomerGetDto;
import lk.api.service.CustomerService;
import lk.api.util.JWTTokenGenerator;
import lk.api.util.TokenStatus;
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
    private final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public CustomerController(CustomerService customerService, JWTTokenGenerator jwtTokenGenerator) {
        this.customerService = customerService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }


    @PostMapping
    public ResponseEntity<Object> saveCustomer(@RequestBody CustomerDto customerDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            CustomerDto dto = customerService.saveCustomer(customerDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllCustomer(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<CustomerGetDto> dtos = this.customerService.getAllCustomer();
            return new ResponseEntity<>(dtos, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto customerDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            CustomerGetDto dto = this.customerService.updateCustomer(customerId, customerDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long customerId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            CustomerGetDto dto = this.customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search/{value}")
    public ResponseEntity<Object> searchCustomer(@PathVariable String value, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            CustomerGetDto dto = this.customerService.searchAll(value);
            if (dto == null) {
                return new ResponseEntity<>("Empty Data", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(dto, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> getCustomerAccounts(@PathVariable Long customerId,@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<CustomerGetDto> dtos = this.customerService.getCustomerAccounts(customerId);
            return new ResponseEntity<>(dtos, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
