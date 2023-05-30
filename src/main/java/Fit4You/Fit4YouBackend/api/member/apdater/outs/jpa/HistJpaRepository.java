package Fit4You.Fit4YouBackend.api.member.apdater.outs.jpa;

import Fit4You.Fit4YouBackend.api.member.domains.MedicalHist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistJpaRepository extends JpaRepository<MedicalHist, Long> {

    @Query("SELECT h FROM MedicalHist h WHERE h.member.id = :memberId")
    List<MedicalHist> getByMemberId(@Param("memberId") Long memberId);

}
