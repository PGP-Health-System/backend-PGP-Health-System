package br.com.HEALTHTRACK.API.HEALTHTRACK.Swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("HealthTrack").version("0.0.1").description("Documentação HealthTrack API"));
    }
}
