package Fit4You.Fit4YouBackend.api.adapters.outs.persistence;

import Fit4You.Fit4YouBackend.exception.EmailConflicted;
import Fit4You.Fit4YouBackend.exception.MemberNotFound;
import Fit4You.Fit4YouBackend.api.application.ports.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.RegisterMemberPort;
import Fit4You.Fit4YouBackend.api.domains.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberPersistenceAdapter implements RegisterMemberPort, LoadMemberPort {

    private final MemberJpaRepository memberRepository;

    @Override
    public Long registerMember(Member member) {
       try{
           return memberRepository.save(member).getId();
       }catch (Exception e){
           throw new EmailConflicted();
       }

    }

    @Override
    public Member loadMember(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFound::new);
        return member;
    }
}
