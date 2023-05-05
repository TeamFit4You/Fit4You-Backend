package Fit4You.Fit4YouBackend.api.application.ports.outs.training;

import Fit4You.Fit4YouBackend.api.domains.Exercise;

import java.util.List;

public interface ExercisePort {
    List<Exercise> getAll();
}
