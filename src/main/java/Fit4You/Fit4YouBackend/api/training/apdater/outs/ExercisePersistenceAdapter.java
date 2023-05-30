package Fit4You.Fit4YouBackend.api.training.apdater.outs;

import Fit4You.Fit4YouBackend.api.training.apdater.outs.jpa.ExerciseJpaRepository;
import Fit4You.Fit4YouBackend.api.training.application.port.outs.ExercisePort;
import Fit4You.Fit4YouBackend.api.training.domains.Exercise;
import Fit4You.Fit4YouBackend.exception.type.ExerciseNotFound;
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

    @Override
    public Exercise getOne(Long exerciseId) {
        return exerciseJpaRepository.findById(exerciseId)
                .orElseThrow(ExerciseNotFound::new);
    }
}
