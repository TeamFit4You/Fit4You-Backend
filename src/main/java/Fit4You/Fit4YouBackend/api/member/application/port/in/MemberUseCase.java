package Fit4You.Fit4YouBackend.api.member.application.port.in;

import Fit4You.Fit4YouBackend.api.member.dto.request.SignInRequest;
import Fit4You.Fit4YouBackend.api.member.dto.request.SignUpRequest;

public interface MemberUseCase {

    void signUp(SignUpRequest request);
    Long signIn(SignInRequest request);
}
