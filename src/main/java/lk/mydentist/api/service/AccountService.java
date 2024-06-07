package lk.mydentist.api.service;

import lk.mydentist.api.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto saveAccount(AccountDto dto);

    List<AccountDto> getAllAccount();

    AccountDto updateAccount(Long accountId, AccountDto updateDto);

    AccountDto deleteAccount(Long id);
}
