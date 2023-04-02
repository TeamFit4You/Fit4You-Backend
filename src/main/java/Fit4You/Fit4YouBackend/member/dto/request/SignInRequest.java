package Fit4You.Fit4YouBackend.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
public class SignInRequest {

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 맞지 않습니다")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @Builder
    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
