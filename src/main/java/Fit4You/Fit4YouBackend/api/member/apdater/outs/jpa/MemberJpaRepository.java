package Fit4You.Fit4YouBackend.api.member.apdater.outs.jpa;

import Fit4You.Fit4YouBackend.api.member.domains.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m FROM Member m WHERE m.email = :email")
    Optional<Member> findByEmail(@Param("email") String email);
//    Optional<Member> findByEmail(String email);
}
