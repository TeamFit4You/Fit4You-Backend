package Fit4You.Fit4YouBackend;

import Fit4You.Fit4YouBackend.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class Fit4YouBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Fit4YouBackendApplication.class, args);
	}

}
