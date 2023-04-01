package Fit4You.Fit4YouBackend.member.application.services;

import Fit4You.Fit4YouBackend.exception.InvalidSignInInfo;
import Fit4You.Fit4YouBackend.exception.Unauthorized;
import Fit4You.Fit4YouBackend.member.application.ports.in.MemberUseCase;
import Fit4You.Fit4YouBackend.member.application.ports.out.LoadMemberPort;
import Fit4You.Fit4YouBackend.member.application.ports.out.RegisterMemberPort;
import Fit4You.Fit4YouBackend.member.crypto.PasswordEncoder;
import Fit4You.Fit4YouBackend.member.domains.Member;
import Fit4You.Fit4YouBackend.member.dto.request.SignInRequest;
import Fit4You.Fit4YouBackend.member.dto.request.SignUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
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
