package lk.api.dto.getdto;

import lk.api.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderGetDto {
    private Long orderId;
    private LocalDate date;
    private String sentCurrency;
    private String receiveCurrency;
    private double rate;
    private double sentAmount;
    private double receiveAmount;
    private double serviceCharge;
    private String description;
    private int referenceNo;
    private String status;
    private CustomerDto customer;
    private AccountGetDto account;
}
