package lk.mydentist.api.repository;

import lk.mydentist.api.model.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceiverRepo extends JpaRepository<Receiver,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM receiver ORDER BY receiver_id DESC")
    List<Receiver> findAllReceiver();
}
