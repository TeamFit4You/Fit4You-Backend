package Fit4You.Fit4YouBackend.api.training.application.port.outs;

import Fit4You.Fit4YouBackend.api.training.domains.Sets;

public interface SetsPort {
    void save(Sets sets);

    Integer countByWorkoutId(Long workoutId);
}
