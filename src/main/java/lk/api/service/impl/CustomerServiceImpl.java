package lk.api.service.impl;

import lk.api.dto.AccountDto;
import lk.api.dto.CustomerDto;
import lk.api.dto.getdto.CustomerGetDto;
import lk.api.model.Account;
import lk.api.model.Customer;
import lk.api.repository.AccountRepo;
import lk.api.repository.CustomerRepo;
import lk.api.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final AccountRepo accountRepo;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepo customerRepo, AccountRepo accountRepo, ModelMapper modelMapper) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = this.dtoToEntity(customerDto);
        Customer save = this.customerRepo.save(customer);
        return entityToDto(save);
    }

    @Override
    public List<CustomerGetDto> getAllCustomer() {
        List<Customer> allCustomer = this.customerRepo.findAllCustomer();
        List<CustomerGetDto> list = new ArrayList<>();
        for (Customer customer : allCustomer){
            CustomerGetDto dto = entityToGetDto(customer);
            list.add(dto);
        }
        return list;
    }

    @Override
    public CustomerGetDto updateCustomer(Long customerId, CustomerDto customerDto) {
        Optional<Customer> byId = customerRepo.findById(customerId);
        if (byId.isPresent()){
            customerDto.setCustomerId(byId.get().getCustomerId());
            Customer customer = this.dtoToEntity(customerDto);
            Customer save = customerRepo.save(customer);
            return entityToGetDto(save);
        }else {
            return null;
        }
    }

    @Override
    public CustomerGetDto deleteCustomer(Long customerId) {
        Optional<Customer> byId = customerRepo.findById(customerId);
            if (byId.isPresent()){
                customerRepo.deleteById(customerId);
                return entityToGetDto(byId.get());
            }else {
                return null;
            }
    }

    @Override
    public CustomerGetDto searchAll(String value) {
        Optional<Customer> byId = this.customerRepo.findByNicOrFirstNameOrLastNameOrContactOrCustomerId(value, value, value, value, Long.valueOf(value));
        return byId.map(this::entityToGetDto).orElse(null);
    }

    @Override
    public List<CustomerGetDto> getCustomerAccounts(Long customerId) {
//        List<Account> customerAccounts = this.accountRepo.findCustomerAccounts(customerId);
//        if (customerAccounts != null) {
//            return customerAccounts.stream()
//                    .map(this::entityToDto)
//                    .collect(Collectors.toList());
//        }
        return null;
    }


    private Customer dtoToEntity(CustomerDto dto){
        Customer customer = modelMapper.map(dto, Customer.class);
        customer.setCustomerId(dto.getCustomerId());
        return customer;
    }

    private CustomerGetDto entityToGetDto(Customer customer){
        CustomerGetDto map = modelMapper.map(customer, CustomerGetDto.class);
        map.setAccounts(entityToAccount(customer.getAccounts()));
        return map;
    }

    private List<AccountDto> entityToAccount(List<Account> accounts) {
        List<AccountDto> allAccount = new ArrayList<>();
        for (Account account: accounts){
            allAccount.add(modelMapper.map(account, AccountDto.class));
        }
        return allAccount;
    }

    private CustomerDto entityToDto(Customer customer) {
        return (customer == null) ? null : modelMapper.map(customer, CustomerDto.class);
    }
}
