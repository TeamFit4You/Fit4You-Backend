package Fit4You.Fit4YouBackend.api.training.apdater.outs;

import Fit4You.Fit4YouBackend.api.training.apdater.outs.jpa.WorkoutJpaRepository;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.WorkoutPort;
import Fit4You.Fit4YouBackend.api.training.domains.Workout;
import Fit4You.Fit4YouBackend.exception.type.WorkoutNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class WorkoutPersistenceAdapter implements WorkoutPort {

    private final WorkoutJpaRepository workoutJpaRepository;

    @Override
    public Long create(Workout workout) {
        return workoutJpaRepository.save(workout).getId();
    }

    @Override
    public Workout getOne(Long id) {
        return workoutJpaRepository.findById(id).orElseThrow(WorkoutNotFound::new);
    }
}
