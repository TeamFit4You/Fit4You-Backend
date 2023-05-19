package Fit4You.Fit4YouBackend.api.application.ports.outs.training;

import Fit4You.Fit4YouBackend.api.domains.training.Sets;

public interface SetsPort {
    void save(Sets sets);
}
