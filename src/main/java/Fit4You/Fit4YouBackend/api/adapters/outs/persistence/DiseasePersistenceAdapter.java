package Fit4You.Fit4YouBackend.api.adapters.outs.persistence;

import Fit4You.Fit4YouBackend.api.application.ports.outs.DiseasePort;
import Fit4You.Fit4YouBackend.api.domains.Disease;
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
