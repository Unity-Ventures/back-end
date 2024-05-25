package lk.mydentist.api.repository;

import lk.mydentist.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByUserNameAndNic(String nic, String userName);

    List<Employee> findByUserNameAndRole(String userName, String role);

    Employee findAllByEmployeeId(Long employeeId);
}
