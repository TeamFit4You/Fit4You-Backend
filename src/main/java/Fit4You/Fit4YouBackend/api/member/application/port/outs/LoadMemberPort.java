package Fit4You.Fit4YouBackend.api.member.application.port.outs;

import Fit4You.Fit4YouBackend.api.member.domains.Member;

public interface LoadMemberPort {
    Member loadMember(String email);
}
