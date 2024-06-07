package lk.api.service.impl;

import lk.api.dto.getDto.AccountGetDto;
import lk.api.dto.AccountDto;
import lk.api.model.Account;
import lk.api.repository.AccountRepo;
import lk.api.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepo accountRepo, ModelMapper modelMapper) {
        this.accountRepo = accountRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountDto saveAccount(AccountDto dto) {
        Account account = dtoToEntity(dto);
        Account save = accountRepo.save(account);
        return entityToDto(save);
    }

    @Override
    public List<AccountGetDto> getAllAccount() {
        List<Account> all = accountRepo.findAll();
        ArrayList<AccountGetDto> allAccount = new ArrayList<>();
        for (Account account: all){
            allAccount.add(entityToGetDto(account));
        }
        return allAccount;
    }

    @Override
    public AccountGetDto updateAccount(Long accountId, AccountDto updateDto) {
        Optional<Account> byId = accountRepo.findById(accountId);
        if (byId.isPresent()){
            updateDto.setAccountId(byId.get().getAccountId());
            Account account = this.dtoToEntity(updateDto);
            Account save = accountRepo.save(account);
            return entityToGetDto(save);
        }else {
            return null;
        }
    }

    @Override
    public AccountGetDto deleteAccount(Long id) {
        Optional<Account> byId = accountRepo.findById(id);
        if (byId.isPresent()){
            accountRepo.deleteById(id);
            return entityToGetDto(byId.get());
        }else {
            return null;
        }
    }

    private Account dtoToEntity(AccountDto dto){
        Account map = modelMapper.map(dto, Account.class);
        map.setAccountId(dto.getAccountId());
        return map;
    }
    private AccountDto entityToDto(Account account){
        return (account == null) ? null : modelMapper.map(account, AccountDto.class);
    }

    private AccountGetDto entityToGetDto(Account account){
        return account != null ? modelMapper.map(account, AccountGetDto.class) : null;
    }
}
