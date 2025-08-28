package lk.api.service;

import lk.api.dto.CustomerDto;
import lk.api.dto.getdto.CustomerGetDto;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customerDto);

    List<CustomerGetDto> getAllCustomer();

    CustomerGetDto updateCustomer(Long customerId, CustomerDto customerDto);

    CustomerGetDto deleteCustomer(Long customerId);

    CustomerGetDto searchAll(String value);
}
