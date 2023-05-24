package Fit4You.Fit4YouBackend.api.application.ports.outs;

import Fit4You.Fit4YouBackend.api.domains.Disease;

public interface DiseasePort {
    Disease getByName(String name);
}
