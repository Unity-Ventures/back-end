package lk.mydentist.api.service.impl;

import lk.mydentist.api.dto.AccountDto;
import lk.mydentist.api.model.Account;
import lk.mydentist.api.repository.AccountRepo;
import lk.mydentist.api.service.AccountService;
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
    public List<AccountDto> getAllAccount() {
        List<Account> all = accountRepo.findAll();
        ArrayList<AccountDto> allAccount = new ArrayList<>();
        for (Account account: all){
            allAccount.add(entityToDto(account));
        }
        return allAccount;
    }

    @Override
    public AccountDto updateAccount(Long accountId, AccountDto updateDto) {
        Optional<Account> byId = accountRepo.findById(accountId);
        if (byId.isPresent()){
            updateDto.setAccountId(byId.get().getAccountId());
            Account account = this.dtoToEntity(updateDto);
            Account save = accountRepo.save(account);
            return entityToDto(save);
        }else {
            return null;
        }
    }

    @Override
    public AccountDto deleteAccount(Long id) {
        Optional<Account> byId = accountRepo.findById(id);
        if (byId.isPresent()){
            accountRepo.deleteById(id);
            return entityToDto(byId.get());
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
}
