package Fit4You.Fit4YouBackend.member.domains;

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

    @Column(name = "disease_name")
    private String name;
    private String relatedPart;

    @Builder
    public Disease(String name, String relatedPart) {
        this.name = name;
        this.relatedPart = relatedPart;
    }

}