package lk.api.repository;

import lk.api.model.Account;
import lk.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM customers ORDER BY customer_id DESC")
    List<Customer> findAllCustomer();

    Optional<Customer> findByNicOrFirstNameOrLastNameOrContactOrCustomerId(String nic, String fName, String lName, String contact, Long id);



}
