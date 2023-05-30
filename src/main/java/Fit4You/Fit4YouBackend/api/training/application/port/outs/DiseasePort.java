package Fit4You.Fit4YouBackend.api.training.application.port.outs;

import Fit4You.Fit4YouBackend.api.training.domains.Disease;

public interface DiseasePort {
    Disease getByName(String name);
}
