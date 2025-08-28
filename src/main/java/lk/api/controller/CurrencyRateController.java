package lk.api.controller;

import lk.api.dto.CurrencyRateDto;
import lk.api.service.CurrencyRateService;
import lk.api.util.JWTTokenGenerator;
import lk.api.util.TokenStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/rate")
public class CurrencyRateController {
    private final JWTTokenGenerator jwtTokenGenerator;
    private final CurrencyRateService currencyRateService;

    public CurrencyRateController(JWTTokenGenerator jwtTokenGenerator, CurrencyRateService currencyRateService) {
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.currencyRateService = currencyRateService;
    }

    @PostMapping()
    public ResponseEntity<Object> saveRate(@RequestBody CurrencyRateDto currencyRateDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            CurrencyRateDto dto = currencyRateService.saveRate(currencyRateDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{rateId}")
    public ResponseEntity<Object> updateRate(@PathVariable Long rateId, @RequestBody CurrencyRateDto currencyRateDto, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            CurrencyRateDto dto = currencyRateService.updateRate(rateId, currencyRateDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{rateId}")
    public ResponseEntity<Object> deleteRate(@PathVariable Long rateId, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            CurrencyRateDto dto = currencyRateService.deleteRate(rateId);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchRepo(@RequestParam String sentCurrency, @RequestParam String receiveCurrency, @RequestHeader(name = "Authorization") String authorizationHeader) {
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            CurrencyRateDto dto = currencyRateService.searchRate(sentCurrency, receiveCurrency);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAllRate(@RequestHeader(name = "Authorization") String authorizationHeader){
        if (this.jwtTokenGenerator.validateToken(authorizationHeader)) {
            Set<String> dtoList = currencyRateService.getAllRate();
            return new ResponseEntity<>(dtoList, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(TokenStatus.TOKEN_INVALID, HttpStatus.UNAUTHORIZED);
        }
    }

}
