package Fit4You.Fit4YouBackend.api.training.apdater.outs.jpa;

import Fit4You.Fit4YouBackend.api.training.domains.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutJpaRepository extends JpaRepository<Workout, Long> {

}
