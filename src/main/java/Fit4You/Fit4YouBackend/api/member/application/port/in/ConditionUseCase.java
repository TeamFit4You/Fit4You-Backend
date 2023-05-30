package Fit4You.Fit4YouBackend.api.member.application.port.in;

import Fit4You.Fit4YouBackend.api.member.dto.request.SurveyRequest;

public interface ConditionUseCase {

    void recordSurvey(SurveyRequest request);
}
