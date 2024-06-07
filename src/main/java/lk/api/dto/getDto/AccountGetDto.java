package lk.api.dto.getDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AccountGetDto {
    private Long accountId;
    private String name;
    private String accountNo;
    private String bank;
    private String branch;
    private String country;
    private CustomerDto customer;
}
