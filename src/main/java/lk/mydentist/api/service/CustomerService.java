package lk.mydentist.api.service;

import lk.mydentist.api.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomer();

    CustomerDto updateCustomer(Long customerId, CustomerDto customerDto);

    CustomerDto deleteCustomer(Long customerId);

    CustomerDto searchAll(String value);
}
