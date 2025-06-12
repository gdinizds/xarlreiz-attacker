package com.onzecincocincodoet.xarlreizattacker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Xarlreiz Attacker")
                        .description("API para ataques DDOS UDP/TCP")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Enzo Tulio")
                                .email("enzotulio38@gmail.com")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server")
                ));
    }
}

