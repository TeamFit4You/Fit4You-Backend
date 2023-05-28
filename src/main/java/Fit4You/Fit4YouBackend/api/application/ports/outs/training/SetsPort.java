package Fit4You.Fit4YouBackend.api.application.ports.outs.training;

import Fit4You.Fit4YouBackend.api.domains.training.Sets;

import java.util.List;

public interface SetsPort {
    void save(Sets sets);

    Integer countByWorkoutId(Long workoutId);
}
