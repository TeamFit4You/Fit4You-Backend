package Fit4You.Fit4YouBackend.member.application.services;

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

    private static PasswordEncoder encoder = new PasswordEncoder();
    @Override
    @Transactional
    public void signUp(SignUpRequest request) {
        String encodedPw= encoder.encode(request.getPassword());
        if(!encoder.matches(request.getPassword(), encodedPw)){
            throw new RuntimeException();//TODO 예외생성
        };

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
        if (!encoder.matches(request.getPassword(),member.getPassword())) {
            throw new Unauthorized();
        }

        return member.getId();
    }
}
