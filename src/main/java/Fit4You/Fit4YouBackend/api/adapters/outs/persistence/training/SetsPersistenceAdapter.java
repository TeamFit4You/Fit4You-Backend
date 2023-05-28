package Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.api.application.ports.outs.training.SetsPort;
import Fit4You.Fit4YouBackend.api.domains.training.Sets;
import Fit4You.Fit4YouBackend.api.domains.training.Workout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
