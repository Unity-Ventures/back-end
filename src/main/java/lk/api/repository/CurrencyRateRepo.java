package lk.api.repository;

import lk.api.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRateRepo extends JpaRepository<CurrencyRate,Long> {
    CurrencyRate findBySentCurrencyAndReceiveCurrency(String sentCurrency, String receiveCurrency);
}
