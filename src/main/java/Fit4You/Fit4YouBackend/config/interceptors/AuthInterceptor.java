package Fit4You.Fit4YouBackend.config.interceptors;

import Fit4You.Fit4YouBackend.config.AppConfig;
import Fit4You.Fit4YouBackend.exception.Unauthorized;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AppConfig appConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(">>> prehandle");
        //@Auth가 붙어있는 경우만 인증체크
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(handlerMethod.getMethodAnnotation(Auth.class)==null){
            return true;
        }

        String requestURI = request.getRequestURI();
        log.info(">>> 인증 체크 : {}",requestURI);

        String jws = request.getHeader("Authorization");//accessToken

        if (jws == null || jws.equals("")) {
            throw new Unauthorized();
        }

        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(appConfig.getJwtKey())
                    .build()
                    .parseClaimsJws(jws);
            log.info("claims >>> {}",claims);
            //OK, we can trust this JWT
        } catch (JwtException e) {
            //don't trust the JWT!
            log.error("error",e);
            throw new Unauthorized("유효하지 않은 토큰");
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info(">>>작업완료");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info(">>>요청 종료");
    }



}
