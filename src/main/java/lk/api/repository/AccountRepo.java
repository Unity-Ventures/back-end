package lk.api.repository;

import lk.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account,Long> {
    @Query(value = "SELECT * FROM accounts INNER JOIN customers ON accounts.customer_id = customers.customer_id WHERE customers.customer_id = :id ORDER BY account_id DESC", nativeQuery = true)
    List<Account> findCustomerAccounts(@Param("id") Long id);
}
