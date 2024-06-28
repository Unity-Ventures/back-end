package lk.api.repository;

import lk.api.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PaymentDetailsRepo extends JpaRepository<PaymentDetails,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM payment_details ORDER BY payment_id DESC")
    List<PaymentDetails> findAllPaymentDetails();

    List<PaymentDetails> findAllByEmployeeEmployeeId(Long employeeId);
    List<PaymentDetails> findAllByRunner_RunnerId(Long runnerId);
    @Query(nativeQuery = true, value = "SELECT * FROM payment_details where order_id=?")
    List<PaymentDetails> findAllByOrder(Long orderId);
}
