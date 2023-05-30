package Fit4You.Fit4YouBackend.api.training.domains;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sets_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    private Integer setNo;

    private Float accuracy;

    @Lob
    private String feedback;

    @Builder
    public Sets(Workout workout, Integer setNo, Float accuracy, String feedback) {
        this.workout = workout;
        this.setNo = setNo;
        this.accuracy = accuracy;
        this.feedback = feedback;
    }
}
