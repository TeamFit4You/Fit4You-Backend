package Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.api.application.ports.outs.training.WorkoutPort;
import Fit4You.Fit4YouBackend.api.domains.training.Workout;
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
