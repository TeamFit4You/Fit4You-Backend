package Fit4You.Fit4YouBackend.api.application.ports.outs;

import Fit4You.Fit4YouBackend.api.domains.member.Member;

public interface RegisterMemberPort {
    Long registerMember(Member member);
}
