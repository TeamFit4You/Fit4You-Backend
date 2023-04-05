package Fit4You.Fit4YouBackend.member.domains;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private Integer numOfSet;

    private Float accuracy;

    @Lob
    private String feedback;

    @Builder
    public Workout(Training training, Exercise exercise, Integer numOfSet, Float accuracy, String feedback) {
        this.training = training;
        this.exercise = exercise;
        this.numOfSet = numOfSet;
        this.accuracy = accuracy;
        this.feedback = feedback;
    }
}
