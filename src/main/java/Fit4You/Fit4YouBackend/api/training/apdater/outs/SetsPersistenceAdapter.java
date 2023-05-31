package Fit4You.Fit4YouBackend.api.training.apdater.outs;

import Fit4You.Fit4YouBackend.api.training.apdater.outs.jpa.SetsJpaRepository;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.SetsPort;
import Fit4You.Fit4YouBackend.api.training.domains.Sets;
import Fit4You.Fit4YouBackend.api.training.domains.Workout;
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

    @Override
    public List<Sets> getByWorkout(Workout workout) {
        return setsJpaRepository.getByWorkoutId(workout.getId());
    }
}
