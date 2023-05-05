package Fit4You.Fit4YouBackend.api.adapters.in.web;

import Fit4You.Fit4YouBackend.config.AppConfig;
import Fit4You.Fit4YouBackend.config.interceptors.Auth;
import Fit4You.Fit4YouBackend.api.application.ports.in.MemberUseCase;
import Fit4You.Fit4YouBackend.api.dto.request.SignInRequest;
import Fit4You.Fit4YouBackend.api.dto.request.SignUpRequest;
import Fit4You.Fit4YouBackend.api.dto.response.SessionResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
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
public class MemberController {

    private final MemberUseCase memberUseCase;
    private final AppConfig appConfig;

    @PostMapping("/members/sign-up")
    public void signUp(@RequestBody @Valid SignUpRequest request){
        memberUseCase.signUp(request);
    }


    @PostMapping("/members/sign-in")
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
        return new SessionResponse(jws);
    }

    @Auth
    @GetMapping("/members/auth/test")
    public void test(){
        log.info(">>> 인증 테스트 요청 성공");
    }
}
