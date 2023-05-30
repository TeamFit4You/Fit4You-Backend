package Fit4You.Fit4YouBackend.api.member.apdater.outs;

import Fit4You.Fit4YouBackend.api.member.apdater.outs.jpa.HistJpaRepository;
import Fit4You.Fit4YouBackend.api.member.application.port.outs.HistPort;
import Fit4You.Fit4YouBackend.api.member.domains.MedicalHist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class HistPersistenceAdapter implements HistPort{
    private final HistJpaRepository histJpaRepository;
    @Override
    public List<MedicalHist> getByMemberId(Long memberId) {
        return histJpaRepository.getByMemberId(memberId);
    }

    @Override
    public void save(MedicalHist hist) {
        histJpaRepository.save(hist);
    }

    @Override
    public void deleteById(Long id) {
        histJpaRepository.deleteById(id);
    }
}
