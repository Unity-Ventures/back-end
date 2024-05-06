package lk.mydentist.api.service;

import lk.mydentist.api.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto getEmployeeById(String id);
}
