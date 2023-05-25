package Fit4You.Fit4YouBackend.api.application.ports.outs;

import Fit4You.Fit4YouBackend.api.domains.member.Conditions;
import Fit4You.Fit4YouBackend.api.domains.member.Member;

import java.util.Optional;

public interface ConditionPort {
    Long saveCondition(Conditions conditions);

    Optional<Conditions> getByMember(Long memberId);
}
