package Fit4You.Fit4YouBackend.api.training.application.port.outs;

import Fit4You.Fit4YouBackend.api.training.domains.Training;

public interface TrainingPort {
    Long create(Training training);
}
