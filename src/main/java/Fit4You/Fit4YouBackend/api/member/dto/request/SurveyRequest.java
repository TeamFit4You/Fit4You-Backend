package Fit4You.Fit4YouBackend.api.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SurveyRequest {

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 맞지 않습니다")
    @Schema(description = "이메일")
    private String email;

    //나이 성별 키 무게 - 기록

    //VAS
    @NotBlank
    @Schema(description = "목")
    private Float neck; //목
    @NotBlank
    @Schema(description = "어깨")
    private Float shoulder; //어깨
    @NotBlank
    @Schema(description = "허리")
    private Float lumbar; //허리
    @NotBlank
    @Schema(description = "손목")
    private Float wrist; //손목
    @NotBlank
    @Schema(description = "팔꿈치")
    private Float elbow; //팔꿈치
    @NotBlank
    @Schema(description = "무릎")
    private Float knee; //무릎


    @Schema(description = "과거에 겪은적이 있는 질환:String으로 이루어진 List ex) [\"허리디스크\",\"거북목\"]")
    private List<String> hist = new ArrayList<>();

    @Builder
    public SurveyRequest(String email, Float neck, Float shoulder, Float lumbar, Float wrist, Float elbow, Float knee, List<String> hist) {
        this.email = email;
        this.neck = neck;
        this.shoulder = shoulder;
        this.lumbar = lumbar;
        this.wrist = wrist;
        this.elbow = elbow;
        this.knee = knee;
        this.hist = hist;
    }
    /**
     * 목 - 거북목
     * 어꺠 - 회전근개 파열
     * 허리 - 허리디스크
     * 허리 - 척추관 협착증
     * 손목 - 손목터널 증후군
     *
     {
     목:
     어꺠:
     ...
     질환여부 : "거북목, 허리디스크"
     }
     {
     목:
     어꺠:
     ...
     질환여부 : ""
     }
     */
}
