package Fit4You.Fit4YouBackend.websocket;

import Fit4You.Fit4YouBackend.config.AppConfig;
import Fit4You.Fit4YouBackend.exception.Unauthorized;
import Fit4You.Fit4YouBackend.member.application.ports.outs.training.TrainingPort;
import Fit4You.Fit4YouBackend.member.application.ports.outs.training.WorkoutPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MySocketHandler extends BinaryWebSocketHandler {

    private final TrainingPort trainingPort;
    private final WorkoutPort workoutPort;

    private final AppConfig appConfig;

    public MySocketHandler(TrainingPort trainingPort, WorkoutPort workoutPort, AppConfig appConfig) {
        this.trainingPort = trainingPort;
        this.workoutPort = workoutPort;
        this.appConfig = appConfig;
    }
    ObjectMapper mapper = new ObjectMapper();
    List<String> dataList = new ArrayList<>();




    //웹소켓 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

//        if (SocketAuthCheck(session)) return;

        log.info(">>>소켓연결 시작 : {}",session.getId());
        //json형태의 요청 양식을 클라이언트에게 전달
        SocketResponse response = SocketResponse.builder()
                .type(SocketDataType.START)
                .data("인증성공. 데이터 전송 요구")
                .build();
        session.sendMessage(new TextMessage(mapper.writeValueAsString(response)));
        dataList.clear();//데이터리스트 클리어
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        try {
            log.info(">>>message: {}", message);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

//
//    //양방향 데이터 통신
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        try {
//            SocketRequest socketRequest = mapper.readValue(message.asBytes(), SocketRequest.class);//josn형식을 객체로 변환
//            log.info(">>>message: {} {}",message.getPayload(),socketRequest.getType());
//            //TODO socketRequest.getData() -> json파일 객체 or 리스트에 매핑
//
//            if (socketRequest.getType() == SocketDataType.END) {
//                log.info("소켓 타입이 END 인경우 최종 피드백을 생성하여 사용자에게 전달한 뒤 연결을 종료한다");
//                session.close();
//            }else {
//                dataList.add(socketRequest.getData());
//                //TODO 정확도 모델에 데이터를 넣고, 반환받은 정확도를 사용자에게 전송
//            }
//        }catch (Exception exception){
//            handleTransportError(session,exception,"json형식 오류");
//            return;
//        }
//    }
    //소켓 연결 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if(status == CloseStatus.PROTOCOL_ERROR){
            log.info(">>>소켓연결 종료 by ERROR: {}",session.getId());
            return;
        }
        //정상 종료처리
        log.info(">>>소켓연결 정상 종료 : {}",session.getId());
        //TODO 저장된 정확도, 피드백 등의 데이터 DB에 넣기

    }

    //소켓 통신 에러
    public void handleTransportError(WebSocketSession session, Throwable exception, String message) throws Exception {
        log.error(">>>SOCKET ERRORHANDLER : {}", exception.getStackTrace());
        SocketResponse response = SocketResponse.builder()
                .type(SocketDataType.ERROR)
                .data(message)
                .build();
        session.sendMessage(new TextMessage(mapper.writeValueAsString(response)));
        session.close(CloseStatus.PROTOCOL_ERROR);
    }

    private boolean SocketAuthCheck(WebSocketSession session) throws Exception {
        String requestURI = session.getUri().toString();
        log.info(">>>소켓 인증 체크 : {}",requestURI);

        String jws = session.getHandshakeHeaders().getOrDefault("Authorization", Arrays.asList("")).get(0);
        if (jws == null || jws.equals("")) {
            Unauthorized exception = new Unauthorized();
            handleTransportError(session,exception,exception.getMessage());
            return true;
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
            Unauthorized exception = new Unauthorized();
            handleTransportError(session,exception,exception.getMessage());
            return true;
        }
        return false;
    }
}
