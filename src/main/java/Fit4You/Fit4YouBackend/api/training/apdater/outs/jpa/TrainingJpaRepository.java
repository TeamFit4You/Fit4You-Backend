package Fit4You.Fit4YouBackend.api.training.apdater.outs.jpa;

import Fit4You.Fit4YouBackend.api.training.domains.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingJpaRepository extends JpaRepository<Training, Long> {

}
