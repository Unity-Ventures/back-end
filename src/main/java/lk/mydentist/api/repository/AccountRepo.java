package lk.mydentist.api.repository;

import lk.mydentist.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account,Long> {
}
