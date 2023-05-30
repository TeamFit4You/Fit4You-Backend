package Fit4You.Fit4YouBackend.api.training.apdater.outs.jpa;

import Fit4You.Fit4YouBackend.api.training.domains.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DiseaseJpaRepository extends JpaRepository<Disease, Long> {

    @Query("SELECT d FROM Disease d WHERE d.name = :name")
    Optional<Disease> getByName(@Param("name") String name);
}
