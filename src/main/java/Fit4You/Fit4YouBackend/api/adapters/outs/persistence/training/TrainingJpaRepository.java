package Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.api.domains.training.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingJpaRepository extends JpaRepository<Training, Long> {

}
