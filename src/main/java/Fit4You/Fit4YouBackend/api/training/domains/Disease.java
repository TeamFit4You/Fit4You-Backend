package Fit4You.Fit4YouBackend.api.training.domains;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Long id;

    private String name;
    private String relatedPart;

    private String opposite;
    @Builder
    public Disease(String name, String relatedPart, String opposite) {
        this.name = name;
        this.relatedPart = relatedPart;
        this.opposite = opposite;
    }

}
