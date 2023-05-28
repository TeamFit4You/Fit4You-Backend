package Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.api.domains.training.Sets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SetsJpaRepository extends JpaRepository<Sets, Long> {



    @Query("SELECT COUNT(s) FROM Sets s WHERE s.workout.id = :workoutId")
    Integer countByWorkoutId(Long workoutId);
}
