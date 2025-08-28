package lk.api.repository;

import lk.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByUserNameAndNic(String nic, String userName);

    List<Employee> findByUserNameAndRole(String userName, String role);

    Employee findAllByEmployeeId(Long employeeId);

    List<Employee> findByRole(String partner);
}
