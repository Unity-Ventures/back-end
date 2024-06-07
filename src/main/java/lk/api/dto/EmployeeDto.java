package lk.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class EmployeeDto {
    private Long employeeId;
    private String fistName;
    private String lastName;
    private String address;
    private String contact;
    private String nic;
    private String country;
    private String role;
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
