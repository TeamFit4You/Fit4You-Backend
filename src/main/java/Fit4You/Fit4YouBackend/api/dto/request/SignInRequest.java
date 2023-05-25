package Fit4You.Fit4YouBackend.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
public class SignInRequest {

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 맞지 않습니다")
    @Schema(description = "이메일 - 형식:test@email.com")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Schema(description = "비밀번호")
    private String password;

    @Builder
    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
