package Fit4You.Fit4YouBackend.member.application.ports.outs;

import Fit4You.Fit4YouBackend.member.domains.Member;

public interface RegisterMemberPort {
    Long registerMember(Member member);
}
