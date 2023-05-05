package Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.domains.training.Workout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WorkoutPersistenceAdapter implements WorkoutPort {

    private final WorkoutJpaRepository workoutJpaRepository;

    @Override
    public void create(Workout workout) {
        workoutJpaRepository.save(workout);
    }
}
