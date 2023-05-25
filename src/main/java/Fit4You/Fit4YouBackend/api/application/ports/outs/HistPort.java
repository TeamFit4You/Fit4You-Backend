package Fit4You.Fit4YouBackend.api.application.ports.outs;

import Fit4You.Fit4YouBackend.api.domains.member.MedicalHist;

import java.util.List;

public interface HistPort {

    List<MedicalHist> getByMemberId(Long memberId);

    void save(MedicalHist hist);

    void deleteById(Long id);
}
