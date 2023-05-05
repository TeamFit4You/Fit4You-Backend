package Fit4You.Fit4YouBackend.api.domains.member;

import Fit4You.Fit4YouBackend.api.domains.Disease;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "condition_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private Float neck; //목
    private Float shoulder; //어깨
    private Float lumbar; //허리
    private Float wrist; //손목
    private Float elbow; //팔꿈치
    private Float ankle; //무릎

    @Builder
    public Condition(Member member, Float neck, Float shoulder, Float lumbar, Float wrist, Float elbow, Float ankle) {
        this.member = member;
        this.createdAt = LocalDateTime.now();
        this.neck = neck;
        this.shoulder = shoulder;
        this.lumbar = lumbar;
        this.wrist = wrist;
        this.elbow = elbow;
        this.ankle = ankle;
    }

}
