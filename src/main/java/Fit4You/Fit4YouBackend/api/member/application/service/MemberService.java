package Fit4You.Fit4YouBackend.api.member.application.service;

import Fit4You.Fit4YouBackend.exception.type.InvalidSignInInfo;
import Fit4You.Fit4YouBackend.api.member.application.port.in.MemberUseCase;
import Fit4You.Fit4YouBackend.api.member.application.port.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.member.application.port.outs.RegisterMemberPort;
import Fit4You.Fit4YouBackend.api.crypto.PasswordEncoder;
import Fit4You.Fit4YouBackend.api.member.domains.Member;
import Fit4You.Fit4YouBackend.api.member.dto.request.SignInRequest;
import Fit4You.Fit4YouBackend.api.member.dto.request.SignUpRequest;
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
