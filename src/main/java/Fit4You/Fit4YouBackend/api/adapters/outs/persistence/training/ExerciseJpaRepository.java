package Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.api.domains.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseJpaRepository extends JpaRepository<Exercise, Long> {

}
