package Fit4You.Fit4YouBackend.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Getter
@ConfigurationProperties(prefix = "app-config")
public class AppConfig {
    private byte[] jwtKey;
    public AppConfig(String jwtKey) {
        this.jwtKey = Base64.getDecoder().decode(jwtKey);
    }
}
