package Fit4You.Fit4YouBackend.api.application.ports.outs;

import Fit4You.Fit4YouBackend.api.domains.member.Conditions;

public interface ConditionPort {
    Long saveCondition(Conditions conditions);
}
