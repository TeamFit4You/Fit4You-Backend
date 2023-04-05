package Fit4You.Fit4YouBackend.websocket;

import Fit4You.Fit4YouBackend.config.AppConfig;
import Fit4You.Fit4YouBackend.config.interceptors.Auth;
import Fit4You.Fit4YouBackend.member.application.ports.out.program.TrainingPort;
import Fit4You.Fit4YouBackend.member.application.ports.out.program.WorkoutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebsocketConfig implements WebSocketConfigurer {

    private final TrainingPort trainingPort;
    private final WorkoutPort workoutPort;
    private final AppConfig appConfig;
    @Override
    @Auth
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(signalingSocketHandler(), "/socket")
                .setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor());

    }

    @Bean
    public WebSocketHandler signalingSocketHandler() {
        return new MySocketHandler(trainingPort,workoutPort, appConfig);
    }
}
