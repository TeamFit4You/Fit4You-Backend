package Fit4You.Fit4YouBackend.api.application.services;

import Fit4You.Fit4YouBackend.exception.type.InvalidSignInInfo;
import Fit4You.Fit4YouBackend.api.application.ports.in.MemberUseCase;
import Fit4You.Fit4YouBackend.api.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.RegisterMemberPort;
import Fit4You.Fit4YouBackend.api.crypto.PasswordEncoder;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import Fit4You.Fit4YouBackend.api.dto.request.SignInRequest;
import Fit4You.Fit4YouBackend.api.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {
    private final RegisterMemberPort registerMemberPort;
    private final LoadMemberPort loadMemberPort;

    @Override
    @Transactional
    public void signUp(SignUpRequest request) {
        String encodedPw= PasswordEncoder.encode(request.getPassword());

        Member member = Member.builder()
                .email(request.getEmail())
                .password(encodedPw)
                .build();

        registerMemberPort.registerMember(member);
    }

    @Override
    @Transactional
    public Long signIn(SignInRequest request) {
        Member member = loadMemberPort.loadMember(request.getEmail());

        if (!PasswordEncoder.matches(request.getPassword(),member.getPassword())) {
            throw new InvalidSignInInfo();
        }

        return member.getId();
    }
}
