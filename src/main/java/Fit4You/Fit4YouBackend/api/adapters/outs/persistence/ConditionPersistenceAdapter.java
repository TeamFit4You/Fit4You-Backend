package Fit4You.Fit4YouBackend.api.adapters.outs.persistence;

import Fit4You.Fit4YouBackend.api.application.ports.outs.ConditionPort;
import Fit4You.Fit4YouBackend.api.domains.member.Conditions;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ConditionPersistenceAdapter implements ConditionPort {

    private final ConditionJpaRepository conditionJpaRepository;

    @Override
    public Long saveCondition(Conditions conditions) {
        return conditionJpaRepository.save(conditions).getId();
    }

    @Override
    public Optional<Conditions> getByMember(Long memberId) {
        return conditionJpaRepository.getByMember(memberId);
    }
}
