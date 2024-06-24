package lk.api.repository;

import lk.api.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM orders ORDER BY order_id DESC")
    List<Orders> findAllOrders();

    List<Orders> findAllByCustomerCustomerId(Long customerId);

    @Query(nativeQuery = true, value = "select orders.* from orders join accounts on orders.account_id=accounts.account_id where accounts.customer_id=?")
    List<Orders> findAllByReceiverWise(Long customerId);
}
