package Fit4You.Fit4YouBackend.api.application.ports.outs.training;

import Fit4You.Fit4YouBackend.api.domains.training.Training;

public interface TrainingPort {
    Long create(Training training);
}
