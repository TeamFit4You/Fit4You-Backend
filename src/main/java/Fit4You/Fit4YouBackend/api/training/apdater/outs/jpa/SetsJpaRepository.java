package Fit4You.Fit4YouBackend.api.training.apdater.outs.jpa;

import Fit4You.Fit4YouBackend.api.training.domains.Sets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SetsJpaRepository extends JpaRepository<Sets, Long> {



    @Query("SELECT COUNT(s) FROM Sets s WHERE s.workout.id = :workoutId")
    Integer countByWorkoutId(Long workoutId);
}
