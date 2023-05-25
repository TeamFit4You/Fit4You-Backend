package Fit4You.Fit4YouBackend.api.adapters.outs.persistence;

import Fit4You.Fit4YouBackend.api.application.ports.outs.DiseasePort;
import Fit4You.Fit4YouBackend.api.application.ports.outs.HistPort;
import Fit4You.Fit4YouBackend.api.domains.Disease;
import Fit4You.Fit4YouBackend.api.domains.member.MedicalHist;
import Fit4You.Fit4YouBackend.exception.type.InvalidDiseaseName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
