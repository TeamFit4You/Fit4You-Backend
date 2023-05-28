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
                .description("<h2>요청 응답 양식은 최하단 Schema 참조</h2>" +
                        "<br>요청 실패 응답은 아래 형태(Schema-ErrorResponse)로 통일되어 있음" +
                        "<br>{" +
                        "<br>&ensp;&ensp;  \"code\" : \"에러코드\"," +
                        "<br>&ensp;&ensp;  \"message\" : \"에러메세지\"," +
                        "<br>&ensp;&ensp;  \"validation\" : {" +
                        "<br>&ensp;&ensp;&ensp;&ensp;    \"에러필드1\" : \"필드에러메세지1\"," +
                        "<br>&ensp;&ensp;&ensp;&ensp;    \"에러필드2\" : \"필드에러메세지2\"," +
                        "<br>&ensp;&ensp;&ensp;&ensp;    \"에러필드3\" : \"필드에러메세지3\"" +
                        "<br>&ensp;&ensp;  }" +
                        "<br>}" +
                        "<br>");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
