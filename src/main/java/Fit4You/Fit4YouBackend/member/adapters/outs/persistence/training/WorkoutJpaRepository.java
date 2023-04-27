package Fit4You.Fit4YouBackend.member.adapters.outs.persistence.training;

import Fit4You.Fit4YouBackend.member.domains.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutJpaRepository extends JpaRepository<Workout, Long> {

}
