package lk.api.service;

import lk.api.dto.EmployeeDto;
import lk.api.dto.RunnerDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto getEmployeeById(String id);

    EmployeeDto employeeLogin(EmployeeDto dto);

    EmployeeDto findUserByName(String nic, String userName);

    EmployeeDto registerUser(EmployeeDto dto);

    EmployeeDto updateEmployee(EmployeeDto dto, Long employeeId);

    List<EmployeeDto> getAllEmployee();

    List<EmployeeDto> getAllPartner();

    RunnerDto runnerLogin(EmployeeDto dto);
}
