package Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.api.application.ports.outs.training.TrainingPort;
import Fit4You.Fit4YouBackend.api.domains.training.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TrainingPersistenceAdapter implements TrainingPort {

    private final TrainingJpaRepository trainingJpaRepository;

    @Override
    public Long create(Training training) {
        return trainingJpaRepository.save(training).getId();
    }
}
