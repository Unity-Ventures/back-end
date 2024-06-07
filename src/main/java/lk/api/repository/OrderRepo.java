package lk.api.repository;

import lk.api.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM orders ORDER BY order_id DESC")
    List<Orders> findAllOrders();
}
