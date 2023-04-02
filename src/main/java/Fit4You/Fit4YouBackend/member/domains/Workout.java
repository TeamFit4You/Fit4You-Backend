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
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private Integer setNum;

    private Float accuracy;

    @Lob
    private String feedback;

    @Builder
    public Workout(Program program, Exercise exercise, Integer setNum, Float accuracy, String feedback) {
        this.program = program;
        this.exercise = exercise;
        this.setNum = setNum;
        this.accuracy = accuracy;
        this.feedback = feedback;
    }
}
