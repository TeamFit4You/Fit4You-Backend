package Fit4You.Fit4YouBackend.api.application.ports.in;

import Fit4You.Fit4YouBackend.api.dto.request.ConditionCreate;
import Fit4You.Fit4YouBackend.api.dto.request.SignInRequest;
import Fit4You.Fit4YouBackend.api.dto.request.SignUpRequest;

public interface MemberUseCase {

    void signUp(SignUpRequest request);
    Long signIn(SignInRequest request);
}
