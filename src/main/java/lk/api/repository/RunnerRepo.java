package lk.api.repository;

import lk.api.model.Runner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RunnerRepo extends JpaRepository<Runner,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Runners ORDER BY runner_id DESC")
    List<Runner> findAllRunners();

    List<Runner> findByUserName(String userName);

    List<Runner> findAllByEmployeeEmployeeId(Long employeeId);
}
