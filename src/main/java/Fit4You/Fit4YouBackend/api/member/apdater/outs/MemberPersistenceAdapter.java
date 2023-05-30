package Fit4You.Fit4YouBackend.api.member.apdater.outs;

import Fit4You.Fit4YouBackend.api.member.apdater.outs.jpa.MemberJpaRepository;
import Fit4You.Fit4YouBackend.exception.type.EmailConflicted;
import Fit4You.Fit4YouBackend.exception.type.MemberNotFound;
import Fit4You.Fit4YouBackend.api.member.application.port.outs.LoadMemberPort;
import Fit4You.Fit4YouBackend.api.member.application.port.outs.RegisterMemberPort;
import Fit4You.Fit4YouBackend.api.member.domains.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
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
