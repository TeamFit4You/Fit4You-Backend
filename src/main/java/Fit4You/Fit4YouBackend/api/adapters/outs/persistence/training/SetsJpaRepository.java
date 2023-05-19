package Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.api.domains.training.Sets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetsJpaRepository extends JpaRepository<Sets, Long> {

}
