package Fit4You.Fit4YouBackend.member.adapters.outs.persistence;

import Fit4You.Fit4YouBackend.member.domains.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
