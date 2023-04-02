package Fit4You.Fit4YouBackend.member.application.ports.in;

import Fit4You.Fit4YouBackend.member.dto.request.SignInRequest;
import Fit4You.Fit4YouBackend.member.dto.request.SignUpRequest;

public interface MemberUseCase {

    void signUp(SignUpRequest request);
    Long signIn(SignInRequest request);
}
