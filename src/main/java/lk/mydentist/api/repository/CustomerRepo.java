package lk.mydentist.api.repository;

import lk.mydentist.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM customers ORDER BY customer_id DESC")
    List<Customer> findAllCustomer();
}
