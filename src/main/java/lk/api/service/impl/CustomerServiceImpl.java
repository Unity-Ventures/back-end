package lk.api.service.impl;

import lk.api.dto.CustomerDto;
import lk.api.model.Customer;
import lk.api.repository.CustomerRepo;
import lk.api.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepo customerRepo, ModelMapper modelMapper) {
        this.customerRepo = customerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = this.dtoToEntity(customerDto);
        Customer save = this.customerRepo.save(customer);
        return entityToDto(save);
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> allCustomer = this.customerRepo.findAllCustomer();
        List<CustomerDto> list = new ArrayList<>();
        for (Customer customer : allCustomer){
            CustomerDto dto = entityToDto(customer);
            list.add(dto);
        }
        return list;
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto customerDto) {
        Optional<Customer> byId = customerRepo.findById(customerId);
        if (byId.isPresent()){
            customerDto.setCustomerId(byId.get().getCustomerId());
            Customer customer = this.dtoToEntity(customerDto);
            Customer save = customerRepo.save(customer);
            return entityToDto(save);
        }else {
            return null;
        }
    }

    @Override
    public CustomerDto deleteCustomer(Long customerId) {
        Optional<Customer> byId = customerRepo.findById(customerId);
            if (byId.isPresent()){
                customerRepo.deleteById(customerId);
                return entityToDto(byId.get());
            }else {
                return null;
            }
    }

    @Override
    public CustomerDto searchAll(String value) {
        Optional<Customer> byId = this.customerRepo.findByNicOrFirstNameOrLastNameOrContactOrCustomerId(value, value, value, value, Long.valueOf(value));
        return byId.map(this::entityToDto).orElse(null);
    }

    private Customer dtoToEntity(CustomerDto dto){
        Customer customer = modelMapper.map(dto, Customer.class);
        customer.setCustomerId(dto.getCustomerId());
        return customer;
    }

    private CustomerDto entityToDto(Customer customer){
        return (customer == null) ? null : modelMapper.map(customer, CustomerDto.class);
    }
}
