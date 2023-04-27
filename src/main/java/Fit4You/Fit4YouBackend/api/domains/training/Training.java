package Fit4You.Fit4YouBackend.api.domains.training;

import Fit4You.Fit4YouBackend.api.domains.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime createdAt;
    private Integer workoutEa;

    @Builder
    public Training(Member member, Integer workoutEa) {
        this.member = member;
        this.workoutEa = workoutEa;
        this.createdAt = LocalDateTime.now();
    }
}
