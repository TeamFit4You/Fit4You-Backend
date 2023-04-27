package Fit4You.Fit4YouBackend.api.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.api.application.ports.outs.training.ExercisePort;
import Fit4You.Fit4YouBackend.api.domains.Exercise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ExercisePersistenceAdapter implements ExercisePort {

    private final ExerciseJpaRepository exerciseJpaRepository;
    @Override
    public List<Exercise> getAll() {
        return exerciseJpaRepository.findAll();
    }
}
