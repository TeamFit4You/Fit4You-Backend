package Fit4You.Fit4YouBackend.api.adapters.outs.persistence;

import Fit4You.Fit4YouBackend.api.domains.member.Condition;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConditionJpaRepository extends JpaRepository<Condition, Long> {

}
