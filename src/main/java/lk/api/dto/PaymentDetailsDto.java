package lk.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
public class PaymentDetailsDto {
    private Long employeeId;
    private Long runnerId;
    private Long orderId;
    private double runnerAmount;
}
