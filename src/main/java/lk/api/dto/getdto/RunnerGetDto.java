package lk.api.dto.getdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lk.api.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RunnerGetDto {
    private Long runnerId;
    private String name;
    private String address;
    private String contact;
    private String nic;
    private String country;
    private String userName;
    private EmployeeDto employee;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
