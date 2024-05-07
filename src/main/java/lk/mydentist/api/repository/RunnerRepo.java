package lk.mydentist.api.repository;

import lk.mydentist.api.model.Runner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RunnerRepo extends JpaRepository<Runner,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Runner ORDER BY runner_id DESC")
    List<Runner> findAllRunners();
}
