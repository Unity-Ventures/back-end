package lk.mydentist.api.service.impl;

import lk.mydentist.api.dto.EmployeeDto;
import lk.mydentist.api.model.Employee;
import lk.mydentist.api.repository.EmployeeRepo;
import lk.mydentist.api.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public EmployeeDto getEmployeeById(String id) {
//        Optional<Employee> byId = employeeRepo.findById(Long.valueOf(id));
//        if (byId.isPresent()){
//            return byId.get();
//        }
        return null;
    }
}
