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
    private String status;
    private LocalDate date;
    private double amount;
    private CustomerDto customer;
    private AccountGetDto account;
}
