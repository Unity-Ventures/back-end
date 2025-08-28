package lk.api.service.impl;

import lk.api.dto.EmployeeDto;
import lk.api.dto.RunnerDto;
import lk.api.model.Employee;
import lk.api.model.Runner;
import lk.api.repository.EmployeeRepo;
import lk.api.repository.RunnerRepo;
import lk.api.service.EmployeeService;
import lk.api.util.ModelMapperConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final RunnerRepo runnerRepo;

    private final ModelMapperConfig modelMapperConfig;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo, RunnerRepo runnerRepo, ModelMapperConfig modelMapperConfig) {
        this.employeeRepo = employeeRepo;
        this.runnerRepo = runnerRepo;
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
    public RunnerDto runnerLogin(EmployeeDto dto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<Runner> byUserNameAndRole = runnerRepo.findByUserName(dto.getUserName());
        for (Runner name : byUserNameAndRole) {
            boolean isPasswordMatches = passwordEncoder.matches(dto.getPassword(), name.getPassword());
            if (isPasswordMatches) {
                return entityToRunnerDto(name);
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
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
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

    private RunnerDto entityToRunnerDto(Runner runner) {
        return runner == null ? null : modelMapperConfig.modelMapper().map(runner, RunnerDto.class);
    }
}
