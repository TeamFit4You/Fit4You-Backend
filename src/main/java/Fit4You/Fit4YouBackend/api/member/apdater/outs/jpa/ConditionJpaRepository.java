package Fit4You.Fit4YouBackend.api.member.apdater.outs.jpa;

import Fit4You.Fit4YouBackend.api.member.domains.Conditions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConditionJpaRepository extends JpaRepository<Conditions, Long> {

    @Query("SELECT c FROM Conditions c WHERE c.member.id = :memberId")
    Optional<Conditions> getByMember(@Param("memberId") Long memberId);
}
