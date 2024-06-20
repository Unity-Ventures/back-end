package lk.api.service.impl;

import lk.api.service.EmployeeService;
import lk.api.util.ModelMapperConfig;
import lk.api.dto.EmployeeDto;
import lk.api.model.Employee;
import lk.api.repository.EmployeeRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final ModelMapperConfig modelMapperConfig;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, ModelMapperConfig modelMapperConfig) {
        this.employeeRepo = employeeRepo;
        this.modelMapperConfig = modelMapperConfig;
    }

    @Override
    public EmployeeDto getEmployeeById(String id) {
        Optional<Employee> byId = employeeRepo.findById(Long.valueOf(id));
        return byId.map(this::entityToDto).orElse(null);
    }

    @Override
    public EmployeeDto employeeLogin(EmployeeDto dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<Employee> userNames = employeeRepo.findByUserNameAndRole(dto.getUserName(), dto.getRole());
        for (Employee name : userNames) {
            boolean isPasswordMatches = passwordEncoder.matches(dto.getPassword(), name.getPassword());
            if (isPasswordMatches) {
                return entityToDto(name);
            }
        }
        return null;
    }

    @Override
    public EmployeeDto findUserByName(String nic, String userName) {
        Employee employee = employeeRepo.findByUserNameAndNic(nic, userName);
        return entityToDto(employee);
    }

    @Override
    public EmployeeDto registerUser(EmployeeDto dto) {
        Employee employee = this.dtoToEntity(dto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee save = this.employeeRepo.save(employee);
        return entityToDto(save);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto dto, Long employeeId) {
        Employee byId = employeeRepo.findAllByEmployeeId(employeeId);
        if (byId == null) {
            return null;
        } else {
            dto.setEmployeeId(byId.getEmployeeId());
            Employee employee = dtoToEntity(dto);
            Employee save = employeeRepo.save(employee);
            return entityToDto(save);
        }
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> all = employeeRepo.findAll();
        List<EmployeeDto> addData = new ArrayList<>();
        for (Employee employee : all) {
            addData.add(entityToDto(employee));
        }
        return addData;
    }

    @Override
    public List<EmployeeDto> getAllPartner() {
        List<Employee> all = employeeRepo.findByRole("partner");
        List<EmployeeDto> addData = new ArrayList<>();
        for (Employee employee : all) {
            addData.add(entityToDto(employee));
        }
        return addData;
    }

    private Employee dtoToEntity(EmployeeDto dto) {
        return modelMapperConfig.modelMapper().map(dto, Employee.class);
    }

    private EmployeeDto entityToDto(Employee employee) {
        return employee == null ? null : modelMapperConfig.modelMapper().map(employee, EmployeeDto.class);
    }
}
