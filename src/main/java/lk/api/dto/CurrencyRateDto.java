package lk.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CurrencyRateDto {
    private Long currencyId;
    private String sentCurrency;
    private String receiveCurrency;
    private double rate;
}
