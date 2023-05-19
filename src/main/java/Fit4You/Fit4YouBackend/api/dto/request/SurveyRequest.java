package Fit4You.Fit4YouBackend.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SurveyRequest {

    @NotBlank
    private String email;

    //나이 성별 키 무게 - 기록

    //VAS
    private Integer neck; //목
    private Integer shoulder; //어깨
    private Integer lumbar; //허리
    private Integer wrist; //손목
    private Integer elbow; //팔꿈치
    private Integer ankle; //무릎

    /**
     * 목 - 거북목
     * 어꺠 - 회전근개 파열
     * 허리 - 허리디스크
     * 허리 - 척추관 협착증
     * 손목 - 손목터널 증후군
     */
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
    //질환여부 T/F
    private Boolean neck; //목
    private Boolean shoulder; //어깨
    private Boolean lumbar; //허리
    private Boolean wrist; //손목
    private Boolean elbow; //팔꿈치
    private Boolean ankle; //무릎
}
