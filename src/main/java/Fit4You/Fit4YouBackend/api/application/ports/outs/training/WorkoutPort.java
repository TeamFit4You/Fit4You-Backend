package Fit4You.Fit4YouBackend.api.application.ports.outs.training;

import Fit4You.Fit4YouBackend.api.domains.training.Workout;

public interface WorkoutPort {
    Long create(Workout workout);

    Workout getOne(Long id);
}
