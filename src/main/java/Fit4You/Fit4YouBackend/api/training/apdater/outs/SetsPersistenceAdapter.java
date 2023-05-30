package Fit4You.Fit4YouBackend.api.training.apdater.outs;

import Fit4You.Fit4YouBackend.api.training.apdater.outs.jpa.SetsJpaRepository;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.SetsPort;
import Fit4You.Fit4YouBackend.api.training.domains.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SetsPersistenceAdapter implements SetsPort {

    private final SetsJpaRepository setsJpaRepository;

    @Override
    public void save(Sets sets) {
        setsJpaRepository.save(sets);
    }

    @Override
    public Integer countByWorkoutId(Long workoutId) {
        return setsJpaRepository.countByWorkoutId(workoutId);
    }
}
