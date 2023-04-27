package Fit4You.Fit4YouBackend.member.application.ports.outs.training;

import Fit4You.Fit4YouBackend.member.domains.Workout;

public interface WorkoutPort {
    void create(Workout workout);
}
