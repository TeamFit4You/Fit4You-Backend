package Fit4You.Fit4YouBackend.api.training.application.port.outs;

import Fit4You.Fit4YouBackend.api.training.domains.Exercise;

import java.util.List;

public interface ExercisePort {
    List<Exercise> getAll();

    Exercise getOne(Long exerciseId);
}
