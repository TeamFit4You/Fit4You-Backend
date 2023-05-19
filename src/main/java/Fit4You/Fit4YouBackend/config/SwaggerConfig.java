package Fit4You.Fit4YouBackend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Fit-4-You API Document")
                .version("v0.0.1")
                .description("<h3>Fit-4-You API 명세서</h3>+" +
                        "<br>요청 실패 응답은" +
                        "<br>{" +
                        "<br>  \"code\": \"에러코드\"," +
                        "<br>  \"message\": \"에러메세지\"," +
                        "<br>  \"validation\": {" +
                        "<br>    \"에러필드1\": \"필드에러메세지1\"," +
                        "<br>    \"에러필드2\": \"필드에러메세지2\"," +
                        "<br>    \"에러필드3\": \"필드에러메세지3\"" +
                        "<br>  }" +
                        "<br>}" +
                        "<br>형태로 통일되어 있음");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
