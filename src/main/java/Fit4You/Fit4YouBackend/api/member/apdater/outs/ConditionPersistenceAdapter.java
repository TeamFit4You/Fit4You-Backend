package Fit4You.Fit4YouBackend.api.member.apdater.outs;

import Fit4You.Fit4YouBackend.api.member.apdater.outs.jpa.ConditionJpaRepository;
import Fit4You.Fit4YouBackend.api.member.application.port.outs.ConditionPort;
import Fit4You.Fit4YouBackend.api.member.domains.Conditions;
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
