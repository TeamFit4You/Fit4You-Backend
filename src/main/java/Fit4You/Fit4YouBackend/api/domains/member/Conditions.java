package Fit4You.Fit4YouBackend.api.domains.member;

import Fit4You.Fit4YouBackend.api.dto.request.SurveyRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Conditions {
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
    private Float knee; //무릎

    @Builder
    public Conditions(Member member, Float neck, Float shoulder, Float lumbar, Float wrist, Float elbow, Float knee) {
        this.member = member;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = createdAt;
        this.neck = neck;
        this.shoulder = shoulder;
        this.lumbar = lumbar;
        this.wrist = wrist;
        this.elbow = elbow;
        this.knee = knee;
    }

    public void modify(SurveyRequest request){
        this.neck = request.getNeck();
        this.shoulder = request.getShoulder();
        this.lumbar = request.getLumbar();
        this.wrist = request.getWrist();
        this.elbow = request.getElbow();
        this.knee = request.getKnee();
        this.lastModifiedAt = LocalDateTime.now();
    }

    //가중치 변경
    public void change(Float delta, String part) {
        switch (part) {

            case "neck":
                this.neck += delta;
                break;
            case "shoulder":
                this.shoulder += delta;
                break;
            case "lumbar":
                this.lumbar += delta;
                break;
            case "wrist":
                this.wrist += delta;
                break;
            case "elbow":
                this.elbow += delta;
                break;
            case "knee":
                this.knee += delta;
                break;
        }
    }


}
