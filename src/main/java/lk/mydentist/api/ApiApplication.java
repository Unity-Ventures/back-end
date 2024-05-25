package lk.mydentist.api;

import jakarta.annotation.PostConstruct;
import lk.mydentist.api.model.Employee;
import lk.mydentist.api.repository.EmployeeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiApplication {

    private static final Logger logger = LoggerFactory.getLogger(ApiApplication.class);
    private final EmployeeRepo employeeRepo;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ApiApplication(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @PostConstruct
    public void initUsers() {
        try {
            String encodePassword = passwordEncoder.encode("1234");
            employeeRepo.save(new Employee(1L, "piyumal", "nipuna", "Galle", "0754585756", "199907502281", "Sri Lanka", "Admin", "piyumal", encodePassword));

        } catch (Exception e) {
            logger.error("An error occurred during user initialization.", e);
        }
    }
}
