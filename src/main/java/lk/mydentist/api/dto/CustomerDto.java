package lk.mydentist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String contact;
    private String nic;
    private double amount;
    private String country;
    private long employeeId;
}
