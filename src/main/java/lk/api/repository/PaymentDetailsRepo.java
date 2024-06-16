package lk.api.repository;

import lk.api.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PaymentDetailsRepo extends JpaRepository<PaymentDetails,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM payment_details ORDER BY payment_id DESC")
    List<PaymentDetails> findAllPaymentDetails();

}
