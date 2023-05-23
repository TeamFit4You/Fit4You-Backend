package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.api.application.ports.in.ConditionUseCase;
import Fit4You.Fit4YouBackend.api.dto.request.SurveyRequest;
import Fit4You.Fit4YouBackend.config.AppConfig;
import Fit4You.Fit4YouBackend.config.interceptors.Auth;
import Fit4You.Fit4YouBackend.api.application.ports.in.MemberUseCase;
import Fit4You.Fit4YouBackend.api.dto.request.SignInRequest;
import Fit4You.Fit4YouBackend.api.dto.request.SignUpRequest;
import Fit4You.Fit4YouBackend.api.dto.response.SessionResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Member", description = "Member API Document")
public class MemberController {

    private final MemberUseCase memberUseCase;
    private final ConditionUseCase conditionUseCase;
    private final AppConfig appConfig;

    @PostMapping("/members/sign-up")
    @Operation(summary = "회원가입", description = "회원가입 요청시 <br>이메일은 xxx@xxx.xx 양식을 갖춰야 한다.<br>비밀번호는 영문자와 숫자로 구성된 8자리 이상이어야한다.")
    @ApiResponse(responseCode = "200",description = "성공")
    @ApiResponse(responseCode = "400",description = "실패 - 유효하지 않은 이메일 혹은 비밀번호 양식")
    @ApiResponse(responseCode = "409",description = "실패 - 이미 존재하는 이메일")
    public void signUp(@RequestBody @Valid SignUpRequest request){
        memberUseCase.signUp(request);
    }


    @PostMapping("/members/sign-in")
    @Operation(summary = "로그인", description = "로그인 요청")
    public SessionResponse signIn(@RequestBody @Valid SignInRequest request){

        log.info("로그인 요청 - {}", request.getEmail());
        Long memberId = memberUseCase.signIn(request);

        Date iat = new Date();
        SecretKey secretKey = Keys.hmacShaKeyFor(appConfig.getJwtKey());
        String jws = Jwts.builder()
                .setSubject(memberId.toString())
                .setIssuedAt(iat)
                .setExpiration(new Date(iat.getTime()+1000*60*60))//만료시간 - ms단위;1000=1초
                .signWith(secretKey)
                .compact();
      
        return new SessionResponse(memberId,jws);
    }

    @PostMapping("/members/survey")
    @Operation(summary = "설문기록", description = "설문결과 기록 요청")
    public void survey(@RequestBody SurveyRequest request){
        conditionUseCase.recordSurvey(request);
    }


    @GetMapping("/")
    public String defaultTemp(){
        return "기본요청시 보여주는 임시페이지";
    }
    
    
//    @Auth
//    @GetMapping("/members/auth/test")
//    public void test(){
//        log.info(">>> 인증 테스트 요청 성공");
//    }

}
