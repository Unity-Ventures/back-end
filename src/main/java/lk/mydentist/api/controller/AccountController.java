package lk.mydentist.api.controller;

import lk.mydentist.api.dto.AccountDto;
import lk.mydentist.api.service.AccountService;
import lk.mydentist.api.util.JWTTokenGenerator;
import lk.mydentist.api.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final JWTTokenGenerator jwtTokenGenerator;

    public AccountController(AccountService accountService, JWTTokenGenerator jwtTokenGenerator) {
        this.accountService = accountService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping
    public ResponseEntity<Object> saveAccount(@RequestBody AccountDto accountDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            AccountDto dto = this.accountService.saveAccount(accountDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllAccount(@RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            List<AccountDto> allReceivers = this.accountService.getAllAccount();
            return new ResponseEntity<>(allReceivers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Object> updateAccount(@PathVariable Long accountId, @RequestBody AccountDto updateDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            AccountDto dto = this.accountService.updateAccount(accountId, updateDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long accountId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            AccountDto dto = this.accountService.deleteAccount(accountId);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }
}
