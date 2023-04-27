package Fit4You.Fit4YouBackend.member.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.member.domains.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingJpaRepository extends JpaRepository<Training, Long> {

}
