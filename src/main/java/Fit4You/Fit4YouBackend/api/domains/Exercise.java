package Fit4You.Fit4YouBackend.api.domains;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_id")
    private Disease disease;

    private String name;
    private String videoLink;
    private Integer setEa;

    @Lob
    private String detail;

    @Builder
    public Exercise(Disease disease, String name, String videoLink, Integer setEa, String detail) {
        this.disease = disease;
        this.name = name;
        this.videoLink = videoLink;
        this.setEa = setEa;
        this.detail = detail;
    }

    /*주의! TEST용*/
    public void setIdOnlyForTest(Long id){
        this.id = id;
    }

}
