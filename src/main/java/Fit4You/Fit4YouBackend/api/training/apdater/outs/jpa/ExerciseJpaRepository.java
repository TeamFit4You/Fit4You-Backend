package Fit4You.Fit4YouBackend.api.training.apdater.outs.jpa;

import Fit4You.Fit4YouBackend.api.training.domains.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseJpaRepository extends JpaRepository<Exercise, Long> {

}
