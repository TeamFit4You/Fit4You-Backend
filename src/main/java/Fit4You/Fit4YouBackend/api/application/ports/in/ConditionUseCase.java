package Fit4You.Fit4YouBackend.api.application.ports.in;

import Fit4You.Fit4YouBackend.api.dto.request.ConditionCreate;

public interface ConditionUseCase {

    void createCondition(ConditionCreate conditionCreate);
}
