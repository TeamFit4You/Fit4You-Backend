package Fit4You.Fit4YouBackend.api.training.apdater.outs;

import Fit4You.Fit4YouBackend.api.training.apdater.outs.jpa.TrainingJpaRepository;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.TrainingPort;
import Fit4You.Fit4YouBackend.api.training.domains.Training;
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
