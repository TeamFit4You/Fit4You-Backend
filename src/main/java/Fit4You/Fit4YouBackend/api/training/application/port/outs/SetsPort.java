package Fit4You.Fit4YouBackend.api.training.application.port.outs;

import Fit4You.Fit4YouBackend.api.training.domains.Sets;
import Fit4You.Fit4YouBackend.api.training.domains.Workout;

import java.util.List;

public interface SetsPort {
    void save(Sets sets);

    Integer countByWorkoutId(Long workoutId);

    List<Sets> getByWorkout(Workout workout);
}
