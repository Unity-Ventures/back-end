package lk.api.service;

import lk.api.dto.getDto.AccountGetDto;
import lk.api.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto saveAccount(AccountDto dto);

    List<AccountGetDto> getAllAccount();

    AccountGetDto updateAccount(Long accountId, AccountDto updateDto);

    AccountGetDto deleteAccount(Long id);
}
