package lk.mydentist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AccountDto {
    private Long accountId;
    private String name;
    private String accountNo;
    private String bank;
    private String branch;
    private String country;
    private Long customerId;
}
