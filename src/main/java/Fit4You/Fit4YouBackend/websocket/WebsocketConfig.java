package Fit4You.Fit4YouBackend.websocket;

import Fit4You.Fit4YouBackend.config.AppConfig;
import Fit4You.Fit4YouBackend.member.application.ports.outs.training.TrainingPort;
import Fit4You.Fit4YouBackend.member.application.ports.outs.training.WorkoutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    private final TrainingPort trainingPort;
    private final WorkoutPort workoutPort;
    private final AppConfig appConfig;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*");
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.setMessageSizeLimit(160 * 64 * 1024); // default : 64 * 1024
        registry.setSendTimeLimit(100 * 10000); // default : 10 * 10000
        registry.setSendBufferSizeLimit(3* 512 * 1024); // default : 512 * 1024
    }

//    @Override
//    @Auth
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(signalingSocketHandler(), "/socket")
//                .setAllowedOrigins("*")
//                .addInterceptors(new HttpSessionHandshakeInterceptor());
//
//    }
//
//    @Bean
//    public WebSocketHandler signalingSocketHandler() {
//        return new MySocketHandler(trainingPort,workoutPort, appConfig);
//    }



}
