package Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.api.domains.training.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutJpaRepository extends JpaRepository<Workout, Long> {

}
