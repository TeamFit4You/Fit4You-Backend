package Fit4You.Fit4YouBackend.api.adapters.outs.persistence;

import Fit4You.Fit4YouBackend.api.application.ports.outs.ConditionPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.RegisterMemberPort;
import Fit4You.Fit4YouBackend.api.domains.member.Condition;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import Fit4You.Fit4YouBackend.exception.type.EmailConflicted;
import Fit4You.Fit4YouBackend.exception.type.MemberNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ConditionPersistenceAdapter implements ConditionPort {

    private final ConditionJpaRepository conditionJpaRepository;

    @Override
    public Long saveCondition(Condition condition) {
        return conditionJpaRepository.save(condition).getId();
    }

}
