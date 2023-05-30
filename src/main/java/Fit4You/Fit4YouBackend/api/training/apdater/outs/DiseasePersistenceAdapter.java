package Fit4You.Fit4YouBackend.api.training.apdater.outs;

import Fit4You.Fit4YouBackend.api.training.apdater.outs.jpa.DiseaseJpaRepository;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.DiseasePort;
import Fit4You.Fit4YouBackend.api.training.domains.Disease;
import Fit4You.Fit4YouBackend.exception.type.InvalidDiseaseName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DiseasePersistenceAdapter implements DiseasePort {

    private final DiseaseJpaRepository diseaseJpaRepository;

    @Override
    public Disease getByName(String name) {
        return diseaseJpaRepository.getByName(name)
                .orElseThrow(InvalidDiseaseName::new);
    }
}
