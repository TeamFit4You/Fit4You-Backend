package Fit4You.Fit4YouBackend.api.member.application.port.outs;

import Fit4You.Fit4YouBackend.api.member.domains.Conditions;

import java.util.Optional;

public interface ConditionPort {
    Long saveCondition(Conditions conditions);

    Optional<Conditions> getByMember(Long memberId);
}
