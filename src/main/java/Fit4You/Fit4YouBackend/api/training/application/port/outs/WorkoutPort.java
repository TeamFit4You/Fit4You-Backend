package Fit4You.Fit4YouBackend.api.training.application.port.outs;

import Fit4You.Fit4YouBackend.api.training.domains.Workout;

public interface WorkoutPort {
    Long create(Workout workout);

    Workout getOne(Long id);
}
