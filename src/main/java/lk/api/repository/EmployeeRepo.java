package lk.api.repository;

import lk.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByUserNameAndNic(String nic, String userName);

    List<Employee> findByUserNameAndRole(String userName, String role);

    Employee findAllByEmployeeId(Long employeeId);

    @Query(nativeQuery = true, value = "SELECT * FROM employees WHERE role = 'Admin'")
    List<Employee> findByRoleWiseEmployee();

}
