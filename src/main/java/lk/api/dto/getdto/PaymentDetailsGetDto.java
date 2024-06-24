package lk.api.dto.getdto;

import lk.api.dto.EmployeeDto;
import lk.api.dto.OrderDto;
import lk.api.dto.RunnerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
public class PaymentDetailsGetDto {
    private Long paymentId;
    private EmployeeDto employee;
    private RunnerDto runner;
    private OrderDto order;
    private double runnerAmount;
    private String image;
}
