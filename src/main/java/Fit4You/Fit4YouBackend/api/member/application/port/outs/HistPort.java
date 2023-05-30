package Fit4You.Fit4YouBackend.api.member.application.port.outs;

import Fit4You.Fit4YouBackend.api.member.domains.MedicalHist;

import java.util.List;

public interface HistPort {

    List<MedicalHist> getByMemberId(Long memberId);

    void save(MedicalHist hist);

    void deleteById(Long id);
}
