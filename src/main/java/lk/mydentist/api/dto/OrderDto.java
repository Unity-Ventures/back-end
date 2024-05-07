package lk.mydentist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDto {
    private Long orderId;
    private int status;
    private LocalDate date;
    private double amount;
}
