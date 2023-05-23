package Fit4You.Fit4YouBackend.api.adapters.outs.persistence;

import Fit4You.Fit4YouBackend.api.domains.member.Conditions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionJpaRepository extends JpaRepository<Conditions, Long> {

}
