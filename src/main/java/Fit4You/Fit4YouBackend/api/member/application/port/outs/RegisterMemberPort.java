package Fit4You.Fit4YouBackend.api.member.application.port.outs;

import Fit4You.Fit4YouBackend.api.member.domains.Member;

public interface RegisterMemberPort {
    Long registerMember(Member member);
}
