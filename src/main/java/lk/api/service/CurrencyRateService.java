package lk.api.service;

import lk.api.dto.CurrencyRateDto;

import java.util.Set;

public interface CurrencyRateService {
    CurrencyRateDto saveRate(CurrencyRateDto currencyRateDto);

    CurrencyRateDto updateRate(Long rateId, CurrencyRateDto currencyRateDto);

    CurrencyRateDto deleteRate(Long rateId);

    CurrencyRateDto searchRate(String sentCurrency, String receiveCurrency);

    Set<String> getAllRate();
}
