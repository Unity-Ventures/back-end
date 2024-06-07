package lk.api.dto.getdto;

import lk.api.dto.AccountDto;
import lk.api.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerGetDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String contact;
    private String nic;
    private double amount;
    private String country;
    private EmployeeDto employee;
    private List<AccountDto> accounts;
}
