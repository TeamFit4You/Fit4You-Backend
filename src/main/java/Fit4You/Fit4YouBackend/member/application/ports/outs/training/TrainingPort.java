package Fit4You.Fit4YouBackend.member.application.ports.outs.training;

import Fit4You.Fit4YouBackend.member.domains.Training;

public interface TrainingPort {
    Long create(Training training);
}
