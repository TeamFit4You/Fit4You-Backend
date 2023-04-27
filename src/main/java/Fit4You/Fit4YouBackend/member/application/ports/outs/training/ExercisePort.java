package Fit4You.Fit4YouBackend.member.application.ports.outs.training;

import Fit4You.Fit4YouBackend.member.domains.Exercise;

import java.util.List;

public interface ExercisePort {
    List<Exercise> getAll();
}
