package lk.mydentist.api.service;

import lk.mydentist.api.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto getEmployeeById(String id);

    EmployeeDto employeeLogin(EmployeeDto dto);

    EmployeeDto findUserByName(String nic, String userName);

    EmployeeDto registerUser(EmployeeDto dto);

    EmployeeDto updateEmployee(EmployeeDto dto, Long employeeId);

    List<EmployeeDto> getAllEmployee();
}
