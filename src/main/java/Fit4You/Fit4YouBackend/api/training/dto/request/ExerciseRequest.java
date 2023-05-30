package Fit4You.Fit4YouBackend.api.training.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExerciseRequest {

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 맞지 않습니다")
    @Schema(description = "이메일")
    private String email;

    @Schema(description = "해당 부위; ex) 전체, 목, ...")
    private String part;

    @Builder
    public ExerciseRequest(String email, String part) {
        this.email = email;
        this.part = part;
    }
}
