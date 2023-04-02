package Fit4You.Fit4YouBackend.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 맞지 않습니다")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "비밀번호는 8자 이상의 알파벳과 숫자 조합으로 이루어져야 합니다")
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @Builder
    public SignUpRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}