package Fit4You.Fit4YouBackend.api.application.ports.in;

import Fit4You.Fit4YouBackend.api.dto.request.ConditionCreate;
import Fit4You.Fit4YouBackend.api.dto.request.SurveyRequest;

public interface ConditionUseCase {

    void recordSurvey(SurveyRequest request);
}
