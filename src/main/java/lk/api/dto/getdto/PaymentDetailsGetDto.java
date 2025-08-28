package lk.api.dto.getdto;

import lk.api.dto.EmployeeDto;
import lk.api.dto.RunnerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
public class PaymentDetailsGetDto {
    private Long paymentId;
    private EmployeeDto employee;
    private RunnerDto runner;
    private OrderGetDto order;
    private double runnerAmount;
    private String image;
    private Date completedDate;
}
