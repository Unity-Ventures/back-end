package lk.mydentist.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReceiverDto {

    private Long receiverId;
    private String name;
    private String address;
    private String contact;
    private String nic;
    private String accountNo;
    private String bank;
    private String branch;
    private String country;
}
