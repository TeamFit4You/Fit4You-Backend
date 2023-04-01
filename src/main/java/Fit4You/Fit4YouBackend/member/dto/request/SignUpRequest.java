package Fit4You.Fit4YouBackend.member.dto.request;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@AllArgsConstructor
@Builder
public class SignUpRequest {

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 맞지 않습니다")
    String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    String password;
}
